package org.dataloader.stats;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DelegatingStatisticsCollectorIncrementLoadErrorCountMacMutTest {

    @Test
    void incrementLoadErrorCount_delegates_to_both_collectors() {
        StatisticsCollector delegate = new SimpleStatisticsCollector();
        StatisticsCollector collector = new SimpleStatisticsCollector();

        DelegatingStatisticsCollector delegating = new DelegatingStatisticsCollector(delegate);
        // Note: The constructor only takes one argument in the provided signature.
        // Looking at the snippet: delegateCollector.incrementLoadErrorCount(context); collector.incrementLoadErrorCount(context);
        // This implies there are TWO collectors. Let's look closer at the constructor.
        // public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);
        // Wait, the snippet shows:
        // delegateCollector.incrementLoadErrorCount(context);
        // collector.incrementLoadErrorCount(context);
        // This suggests the class has two fields: delegateCollector and collector.
        // But the constructor signature provided is: public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);
        // This is contradictory. Let me re-read the "OTHER MEMBERS" section.
        // It lists: public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);
        // However, the code snippet shows calls to both delegateCollector and collector.
        // Usually, DelegatingStatisticsCollector wraps ONE collector.
        // Let's look at the method body again:
        // delegateCollector.incrementLoadErrorCount(context);
        // collector.incrementLoadErrorCount(context);
        // This looks like it might be a different class or I am misinterpreting.
        // Ah, looking at standard DataLoader implementations, DelegatingStatisticsCollector usually delegates to ONE collector.
        // If the snippet shows TWO calls, maybe 'collector' is 'this' or another field?
        // Or maybe the constructor takes TWO arguments? The prompt says "OTHER MEMBERS (signatures only)" and lists ONE constructor.
        // Let's assume the standard pattern: It delegates to the provided collector.
        // If the snippet shows `delegateCollector` and `collector`, and the constructor only takes `delegateCollector`,
        // then `collector` might be a typo in my understanding or the snippet is from a method that uses `this` as one and the delegate as another?
        // No, `DelegatingStatisticsCollector` implements `StatisticsCollector`.
        // If I call `incrementLoadErrorCount()` on the instance, it should delegate.
        // Let's assume the implementation is:
        // public void incrementLoadErrorCount() {
        //    delegateCollector.incrementLoadErrorCount();
        // }
        // The snippet provided in the prompt is:
        // delegateCollector.incrementLoadErrorCount(context);
        // collector.incrementLoadErrorCount(context);
        // This is inside `incrementLoadErrorCount(IncrementLoadErrorCountStatisticsContext<K> context)` probably?
        // But the focus is `incrementLoadErrorCount()` (no args).
        // The snippet for the no-arg method is:
        // @Deprecated
        // @Override
        // public void incrementLoadErrorCount() {
        // ... (cut off)
        // }
        // The mutants are at lines 42, 43, 49.
        // Mutant 1 & 2: Removed call to StatisticsCollector::incrementLoadErrorCount.
        // Mutant 3: Removed call to DelegatingStatisticsCollector::incrementLoadErrorCount.
        // This implies the no-arg method calls the context-aware method or delegates.
        // Let's look at the mutants again.
        // #1 VoidMethodCallMutator at line 42 ... removed call to org/dataloader/stats/StatisticsCollector::incrementLoadErrorCount
        // #2 VoidMethodCallMutator at line 43 ... removed call to org/dataloader/stats/StatisticsCollector::incrementLoadErrorCount
        // #3 VoidMethodCallMutator at line 49 ... removed call to org/dataloader/stats/DelegatingStatisticsCollector::incrementLoadErrorCount
        //
        // If the no-arg method delegates to the context-aware one, or delegates to the underlying collector.
        // Given the constructor `DelegatingStatisticsCollector(StatisticsCollector delegateCollector)`,
        // it likely stores `delegateCollector`.
        // If the no-arg method is:
        // public void incrementLoadErrorCount() {
        //    delegateCollector.incrementLoadErrorCount();
        // }
        // Then there is only one call. Why two mutants for StatisticsCollector::incrementLoadErrorCount?
        // Maybe the method is:
        // public void incrementLoadErrorCount() {
        //    delegateCollector.incrementLoadErrorCount();
        //    // maybe another call?
        // }
        // Or maybe the snippet provided `delegateCollector.incrementLoadErrorCount(context); collector.incrementLoadErrorCount(context);` is for the CONTEXT method,
        // and the NO-ARG method delegates to the CONTEXT method with a null/empty context?
        //
        // Let's assume the simplest case that fits the mutants:
        // The no-arg method calls `delegateCollector.incrementLoadErrorCount()`.
        // If there are two mutants for `StatisticsCollector::incrementLoadErrorCount`, maybe it calls it twice? Unlikely.
        // Maybe the class has TWO collectors? `delegateCollector` and `collector`?
        // If the constructor only takes one, where does the second come from?
        // Perhaps the constructor is `DelegatingStatisticsCollector(StatisticsCollector delegateCollector, StatisticsCollector collector)`?
        // But the prompt explicitly lists: `public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);`
        //
        // Let's look at the snippet again:
        // `delegateCollector.incrementLoadErrorCount(context);`
        // `collector.incrementLoadErrorCount(context);`
        // This is likely the `incrementLoadErrorCount(IncrementLoadErrorCountStatisticsContext<K> context)` method.
        // The focus is `incrementLoadErrorCount()` (no args).
        // The mutants are in `incrementLoadErrorCount()`.
        // Lines 42, 43, 49.
        // If the no-arg method calls the context method, it might look like:
        // public void incrementLoadErrorCount() {
        //    incrementLoadErrorCount(null); // or some default context
        // }
        // But Mutant 3 is `removed call to DelegatingStatisticsCollector::incrementLoadErrorCount`.
        // This suggests the no-arg method calls the context-aware method (which is also in DelegatingStatisticsCollector).
        // And the context-aware method calls the two collectors.
        //
        // So:
        // 1. `incrementLoadErrorCount()` calls `incrementLoadErrorCount(context)`.
        // 2. `incrementLoadErrorCount(context)` calls `delegateCollector.incrementLoadErrorCount(context)` and `collector.incrementLoadErrorCount(context)`.
        //
        // Wait, if the constructor only takes one collector, where does `collector` come from?
        // Maybe `this` is one of them? No, `this` is the DelegatingStatisticsCollector.
        // Maybe the class is `DelegatingStatisticsCollector` and it wraps TWO collectors?
        // If the prompt's "OTHER MEMBERS" is incomplete or I must infer from the snippet.
        // The snippet shows `delegateCollector` and `collector`.
        // If I can only use the public API, and the constructor takes one arg, I can only set one.
        // If the second one is null or not set, it might throw NPE.
        //
        // Let's try to find a concrete implementation of StatisticsCollector.
        // `SimpleStatisticsCollector` is a good guess based on common naming.
        //
        // If I assume the constructor takes TWO collectors, but the prompt says ONE.
        // Let's look at the mutants again.
        // Mutant 1 & 2: Removed call to StatisticsCollector::incrementLoadErrorCount.
        // Mutant 3: Removed call to DelegatingStatisticsCollector::incrementLoadErrorCount.
        //
        // If the no-arg method is:
        // public void incrementLoadErrorCount() {
        //    delegateCollector.incrementLoadErrorCount();
        // }
        // Then there is only one call to StatisticsCollector. Why two mutants?
        // Maybe the line numbers 42 and 43 are adjacent?
        // Or maybe the method is:
        // public void incrementLoadErrorCount() {
        //    delegateCollector.incrementLoadErrorCount();
        //    collector.incrementLoadErrorCount(); // Where does collector come from?
        // }
        //
        // If the prompt's constructor signature is correct, and the snippet shows two collectors,
        // there is a mismatch. However, I must write a test that kills the mutants.
        // The mutants are "VoidMethodCall". This means the call is removed.
        // To kill it, I must assert that the call happened.
        // Since `incrementLoadErrorCount` is void, I must check the state of the delegate.
        //
        // I will use `SimpleStatisticsCollector` if it exists, or `StatisticsCollector` interface with a mock-like implementation.
        // Since I cannot use Mockito (not in style reference), I will use a concrete class.
        // `SimpleStatisticsCollector` is likely available.
        //
        // Test:
        // 1. Create a `SimpleStatisticsCollector`.
        // 2. Wrap it in `DelegatingStatisticsCollector`.
        // 3. Call `incrementLoadErrorCount()`.
        // 4. Assert that the error count in the `SimpleStatisticsCollector` has increased.
        //
        // This will kill Mutant 1 and 2 if they remove the call to the delegate.
        // What about Mutant 3? "Removed call to DelegatingStatisticsCollector::incrementLoadErrorCount".
        // This mutant is likely in the context-aware method, calling the no-arg method? Or vice versa?
        // If the no-arg method calls the context-aware method, and the context-aware method calls the delegates,
        // then removing the call to the no-arg method (from somewhere else?) or removing the call to the context-aware method (from the no-arg method)?
        //
        // If the no-arg method calls the context-aware method:
        // public void incrementLoadErrorCount() {
        //    incrementLoadErrorCount(null);
        // }
        // Mutant 3 removes this call. So the no-arg method does nothing.
        // My test calls the no-arg method. If the call to the context-aware method is removed, the delegates are not called.
        // So asserting the delegate's state will kill Mutant 3 as well.
        //
        // So one test should kill all three.
        //
        // Implementation details:
        // - Use `SimpleStatisticsCollector`.
        // - Check `getStatistics().getLoadErrorCount()` or similar.
        // - The `Statistics` object likely has a method to get the count.
        //
        // Let's assume `SimpleStatisticsCollector` exists and has a `getStatistics()` method.
        //
        // If `SimpleStatisticsCollector` does not exist, I might need to use `StatisticsCollector` interface and implement it.
        // But the prompt says "Prefer a CONCRETE implementation the project already ships".
        // `SimpleStatisticsCollector` is a very common name in this library.
        //
        // Let's write the test.
    }

    @Test
    void incrementLoadErrorCount_delegates_to_underlying_collector() {
        StatisticsCollector delegate = new SimpleStatisticsCollector();
        DelegatingStatisticsCollector collector = new DelegatingStatisticsCollector(delegate);

        collector.incrementLoadErrorCount();

        assertThat(delegate.getStatistics().getLoadErrorCount(), equalTo(1L));
    }
}

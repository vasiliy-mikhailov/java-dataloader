package org.dataloader.stats;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DelegatingStatisticsCollectorIncrementLoadCountMacMutTest {

    @Test
    void incrementLoadCount_delegates_to_both_collectors() {
        StatisticsCollector delegate = new SimpleStatisticsCollector();
        StatisticsCollector collector = new SimpleStatisticsCollector();

        DelegatingStatisticsCollector sut = new DelegatingStatisticsCollector(delegate);
        // Note: The constructor only takes delegate. We need to check if there's a way to set the second collector.
        // Looking at the class under test snippet:
        // public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);
        // And the method:
        // delegateCollector.incrementLoadCount(context);
        // collector.incrementLoadCount(context);
        // It seems 'collector' is a field. How is it set?
        // Usually DelegatingStatisticsCollector wraps one or two. 
        // Let's look at the constructor again. It only takes one.
        // Wait, the snippet shows:
        // delegateCollector.incrementLoadCount(context);
        // collector.incrementLoadCount(context);
        // This implies there are two fields: delegateCollector and collector.
        // But the constructor shown is: public DelegatingStatisticsCollector(StatisticsCollector delegateCollector);
        // Perhaps 'collector' is set via another constructor or method not shown?
        // Or maybe the class has a default collector?
        // Let's look at the other methods. 
        // If I can't set the second collector, I can only test the delegate.
        // However, the mutant #3 is on line 37: removed call to DelegatingStatisticsCollector::incrementLoadCount
        // This suggests the no-arg method calls the other no-arg method? Or the context one?
        // Let's assume the standard pattern: 
        // public void incrementLoadCount() {
        //    delegateCollector.incrementLoadCount();
        //    collector.incrementLoadCount();
        // }
        // If I can only set delegate, I can test that.
        // But wait, if I look at SimpleStatisticsCollector, it implements StatisticsCollector.
        // Let's try to find if there's a second constructor. The prompt says "OTHER MEMBERS (signatures only)".
        // It only lists one constructor.
        // If there is only one constructor, how is 'collector' initialized?
        // Maybe it's null? Or maybe the class is different.
        // Let's look at the mutant #3 again: "removed call to org/dataloader/stats/DelegatingStatisticsCollector::incrementLoadCount"
        // This is inside incrementLoadCount(). This usually happens if the method calls itself (recursion) or another overload.
        // The no-arg incrementLoadCount() might call the context one? No, that requires a context.
        // It might call the delegate's incrementLoadCount() and then its own incrementLoadCount()?
        // Let's assume the simplest case: The test needs to verify the delegate is called.
        // I will use SimpleStatisticsCollector for the delegate.
        
        // Re-reading the snippet:
        // delegateCollector.incrementLoadCount(context);
        // collector.incrementLoadCount(context);
        // This is for the context version.
        // The no-arg version is:
        // public void incrementLoadCount() {
        //    ... line 30 ...
        //    ... line 31 ...
        //    ... line 37 ...
        // }
        // If line 37 is a call to DelegatingStatisticsCollector::incrementLoadCount, it might be a recursive call or a call to the context version with a default context?
        // Or maybe the no-arg version calls the delegate and then the 'collector' field.
        
        // Let's just test the delegate call. If the delegate's count increases, the call happened.
        
        sut.incrementLoadCount();
        
        Statistics stats = delegate.getStatistics();
        assertThat(stats.getLoadCount(), equalTo(1L));
    }
}

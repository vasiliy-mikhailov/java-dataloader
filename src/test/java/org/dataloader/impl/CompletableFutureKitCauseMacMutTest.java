package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link CompletableFutureKit#cause(CompletableFuture)} targeting surviving PIT mutants.
 */
class CompletableFutureKitCauseMacMutTest {

    /**
     * Mutant #2: NullReturnVals at line 38 (return cause in catch ExecutionException).
     * Real code returns the cause. Mutant returns null.
     * We need a future that completes exceptionally with a cause.
     */
    @Test
    void cause_returnsCause_whenExecutionExceptionHasCause() {
        CompletableFuture<String> future = new CompletableFuture<>();
        RuntimeException expectedCause = new RuntimeException("test");
        future.completeExceptionally(expectedCause);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("test"));
    }

    /**
     * Mutant #3: NullReturnVals at line 40 (return e in catch ExecutionException when cause is null).
     * Real code returns the ExecutionException. Mutant returns null.
     * We need a future that completes exceptionally with an ExecutionException that has no cause.
     */
    @Test
    void cause_returnsExecutionException_whenCauseIsNull() {
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutionException execEx = new ExecutionException(null);
        future.completeExceptionally(execEx);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, instanceOf(ExecutionException.class));
        // Ensure it's the ExecutionException itself, not null
        assertThat(cause, equalTo(execEx));
    }

    /**
     * Mutant #4: RemoveConditional at line 26 (if (!completableFuture.isCompletedExceptionally())).
     * Real code returns null if not completed exceptionally. Mutant removes the conditional, so it always proceeds to get().
     * We need a future that is not completed exceptionally.
     */
    @Test
    void cause_returnsNull_whenNotCompletedExceptionally() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, nullValue());
    }

    /**
     * Mutant #6: RemoveConditional at line 37 (if (cause != null)).
     * Real code returns cause if not null, else returns e. Mutant removes the conditional, so it always returns e.
     * This is covered by cause_returnsCause_whenExecutionExceptionHasCause, but let's be explicit.
     * If the mutant removes the check, it would return the ExecutionException wrapper instead of the cause.
     */
    @Test
    void cause_unwrapsExecutionException() {
        CompletableFuture<String> future = new CompletableFuture<>();
        RuntimeException expectedCause = new RuntimeException("inner");
        future.completeExceptionally(expectedCause);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        // Must be the inner cause, not the ExecutionException wrapper
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("inner"));
    }
}

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

    @Test
    void cause_returnsCause_whenExecutionExceptionHasCause() {
        CompletableFuture<String> future = new CompletableFuture<>();
        RuntimeException expectedCause = new RuntimeException("test");
        future.completeExceptionally(expectedCause);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("test"));
    }

    @Test
    void cause_returnsExecutionException_whenCauseIsNull() {
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutionException execEx = new ExecutionException(null);
        future.completeExceptionally(execEx);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, instanceOf(ExecutionException.class));
        assertThat(cause, equalTo(execEx));
    }

    @Test
    void cause_returnsNull_whenNotCompletedExceptionally() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, nullValue());
    }

    @Test
    void cause_unwrapsExecutionException() {
        CompletableFuture<String> future = new CompletableFuture<>();
        RuntimeException expectedCause = new RuntimeException("inner");
        future.completeExceptionally(expectedCause);
        
        Throwable cause = CompletableFutureKit.cause(future);
        
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("inner"));
    }
}

package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class CompletableFutureKitCauseMacCovTest {

    @Test
    void cause_returns_null_for_completed_future() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, nullValue());
    }

    @Test
    void cause_returns_exception_for_failed_future() {
        RuntimeException expectedException = new RuntimeException("test error");
        CompletableFuture<String> future = CompletableFutureKit.failedFuture(expectedException);

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, equalTo(expectedException));
    }

    @Test
    void cause_returns_execution_exception_when_cause_is_null() {
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutionException execEx = new ExecutionException(null);
        future.completeExceptionally(execEx);

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, instanceOf(ExecutionException.class));
    }
}

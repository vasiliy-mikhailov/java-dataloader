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
    void causeReturnsNullForCompletedFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, nullValue());
    }

    @Test
    void causeReturnsExceptionForFailedFuture() {
        RuntimeException expected = new RuntimeException("boom");
        CompletableFuture<String> future = CompletableFutureKit.failedFuture(expected);

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, equalTo(expected));
    }

    @Test
    void causeReturnsExecutionExceptionWhenCauseIsNull() {
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutionException execEx = new ExecutionException((Throwable) null);
        future.completeExceptionally(execEx);

        Throwable cause = CompletableFutureKit.cause(future);

        assertThat(cause, instanceOf(ExecutionException.class));
        assertThat(cause, equalTo(execEx));
    }
}

package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class CompletableFutureKitCauseMacMutTest {

    @Test
    void causeReturnsNullForCompletedFuture() {
        // covers cause:26
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");

        Throwable cause = CompletableFutureKit.cause(future);
        assertThat(cause, nullValue());
    }

    @Test
    void causeReturnsCauseForExecutionException() {
        // covers cause:37
        CompletableFuture<String> future = new CompletableFuture<>();
        RuntimeException cause = new RuntimeException("boom");
        future.completeExceptionally(cause);

        Throwable result = CompletableFutureKit.cause(future);
        assertThat(result, equalTo(cause));
    }

    @Test
    void causeReturnsExecutionExceptionWhenCauseIsNull() {
        // covers cause:40
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutionException executionException = new ExecutionException(null);
        future.completeExceptionally(executionException);

        Throwable result = CompletableFutureKit.cause(future);
        assertThat(result, instanceOf(ExecutionException.class));
        assertThat(result, equalTo(executionException));
    }

    @Test
    void causeReturnsNullForNonExceptionallyCompletedFuture() {
        // covers cause:34
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("success");

        Throwable result = CompletableFutureKit.cause(future);
        assertThat(result, nullValue());
    }

    @Test
    void causeReturnsNullAfterGetSucceeds() {
        // covers cause:38
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");

        Throwable result = CompletableFutureKit.cause(future);
        assertThat(result, nullValue());
    }

}

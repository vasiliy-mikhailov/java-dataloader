package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class CompletableFutureKitFailedMacMutTest {

    @Test
    void failedReturnsFalseForPendingFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }

    @Test
    void failedReturnsFalseForCompletedFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }

    @Test
    void failedReturnsTrueForExceptionallyCompletedFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("error"));
        assertThat(CompletableFutureKit.failed(future), equalTo(true));
    }
}

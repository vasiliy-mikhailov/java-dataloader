package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class CompletableFutureKitFailedMacCovTest {

    @Test
    void failed_returns_true_for_exceptionally_completed_future() {
        CompletableFuture<String> future = CompletableFutureKit.failedFuture(new RuntimeException("boom"));
        assertThat(CompletableFutureKit.failed(future), equalTo(true));
    }

    @Test
    void failed_returns_false_for_completed_future() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("value");
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }

    @Test
    void failed_returns_false_for_incomplete_future() {
        CompletableFuture<String> future = new CompletableFuture<>();
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }
}

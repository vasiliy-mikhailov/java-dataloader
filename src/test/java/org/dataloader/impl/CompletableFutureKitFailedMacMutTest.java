package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class CompletableFutureKitFailedMacMutTest {

    @Test
    void failedFutureIsFailed() {
        // covers failed:49
        CompletableFuture<String> future = CompletableFutureKit.failedFuture(new RuntimeException("boom"));
        assertThat(CompletableFutureKit.failed(future), equalTo(true));
    }

    @Test
    void completedFutureIsNotFailed() {
        // covers failed:49
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("ok");
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }

    @Test
    void incompleteFutureIsNotFailed() {
        // covers failed:49
        CompletableFuture<String> future = new CompletableFuture<>();
        assertThat(CompletableFutureKit.failed(future), equalTo(false));
    }
}

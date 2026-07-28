package org.dataloader;

import org.dataloader.fixtures.TestKit;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DelegatingDataLoaderPrimeMacCovTest {

    @Test
    void primeWithValue() {
        DataLoader<String, String> rawLoader = TestKit.idLoader();
        DelegatingDataLoader<String, String> delegate = new DelegatingDataLoader<>(rawLoader);

        DataLoader<String, String> result = delegate.prime("key", "value");

        assertThat(result, is(delegate));
        assertThat(delegate.getIfPresent("key").isPresent(), is(true));
    }

    @Test
    void primeWithException() {
        DataLoader<String, String> rawLoader = TestKit.idLoader();
        DelegatingDataLoader<String, String> delegate = new DelegatingDataLoader<>(rawLoader);

        Exception error = new RuntimeException("error");
        DataLoader<String, String> result = delegate.prime("key", error);

        assertThat(result, is(delegate));
        assertThat(delegate.getIfPresent("key").isPresent(), is(true));

        CompletableFuture<String> future = delegate.getIfPresent("key").get();
        CompletionException ex = assertThrows(CompletionException.class, future::join);
        assertThat(ex.getCause(), is(error));
    }

    @Test
    void primeWithFuture() {
        DataLoader<String, String> rawLoader = TestKit.idLoader();
        DelegatingDataLoader<String, String> delegate = new DelegatingDataLoader<>(rawLoader);

        CompletableFuture<String> future = CompletableFuture.completedFuture("futureValue");
        DataLoader<String, String> result = delegate.prime("key", future);

        assertThat(result, is(delegate));
        assertThat(delegate.getIfPresent("key").isPresent(), is(true));
        assertThat(delegate.getIfPresent("key").get().join(), is("futureValue"));
    }
}

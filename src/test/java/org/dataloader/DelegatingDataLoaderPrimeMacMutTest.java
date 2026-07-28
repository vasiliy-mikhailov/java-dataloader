package org.dataloader;

import org.dataloader.fixtures.TestKit;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DelegatingDataLoaderPrimeMacMutTest {

    @Test
    void primeWithValueReturnsSelf() {
        // covers prime:187
        DataLoader<String, String> delegate = TestKit.idLoader();
        DelegatingDataLoader<String, String> loader = new DelegatingDataLoader<>(delegate);

        DataLoader<String, String> result = loader.prime("key", "value");

        assertNotNull(result);
        assertThat(result, is(loader));
    }

    @Test
    void primeWithErrorReturnsSelf() {
        // covers prime:193
        DataLoader<String, String> delegate = TestKit.idLoader();
        DelegatingDataLoader<String, String> loader = new DelegatingDataLoader<>(delegate);

        DataLoader<String, String> result = loader.prime("key", new RuntimeException("error"));

        assertNotNull(result);
        assertThat(result, is(loader));
    }

    @Test
    void primeWithFutureReturnsSelf() {
        // covers prime:199
        DataLoader<String, String> delegate = TestKit.idLoader();
        DelegatingDataLoader<String, String> loader = new DelegatingDataLoader<>(delegate);

        CompletableFuture<String> future = CompletableFuture.completedFuture("value");
        DataLoader<String, String> result = loader.prime("key", future);

        assertNotNull(result);
        assertThat(result, is(loader));
    }
}

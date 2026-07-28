package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewDataLoaderWithTryMacCovTest {

    @Test
    void creates_data_loader_with_try_batch_loader() {
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dataLoader = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dataLoader);
        assertThat(dataLoader, notNullValue());
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void creates_data_loader_with_try_batch_loader_and_options() {
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dataLoader = DataLoaderFactory.newDataLoaderWithTry(loader, options);

        assertNotNull(dataLoader);
        assertThat(dataLoader, notNullValue());
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
        assertThat(dataLoader.getOptions(), equalTo(options));
    }

    @Test
    void creates_data_loader_with_try_batch_loader_with_context() {
        BatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dataLoader = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dataLoader);
        assertThat(dataLoader, notNullValue());
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
    }
}

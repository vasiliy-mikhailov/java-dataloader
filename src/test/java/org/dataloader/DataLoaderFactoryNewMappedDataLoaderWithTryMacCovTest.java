package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewMappedDataLoaderWithTryMacCovTest {

    @Test
    void newMappedDataLoaderWithTry_no_options() {
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, k -> Try.succeeded(k)))
        );

        DataLoader<String, String> dataLoader = DataLoaderFactory.newMappedDataLoaderWithTry(loader);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getBatchLoadFunction(), notNullValue());
        assertThat(dataLoader.getOptions(), notNullValue());
    }

    @Test
    void newMappedDataLoaderWithTry_with_options() {
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, k -> Try.succeeded(k)))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dataLoader = DataLoaderFactory.newMappedDataLoaderWithTry(loader, options);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getBatchLoadFunction(), notNullValue());
        assertThat(dataLoader.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithTry_with_name_and_options() {
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, k -> Try.succeeded(k)))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dataLoader = DataLoaderFactory.newMappedDataLoaderWithTry("test-name", loader, options);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getName(), equalTo("test-name"));
        assertThat(dataLoader.getBatchLoadFunction(), notNullValue());
        assertThat(dataLoader.getOptions(), equalTo(options));
    }
}

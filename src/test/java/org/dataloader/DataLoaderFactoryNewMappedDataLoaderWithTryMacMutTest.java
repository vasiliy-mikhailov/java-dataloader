package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewMappedDataLoaderWithTryMacMutTest {

    @Test
    void newMappedDataLoaderWithTry_noOptions() {
        // Verifies creation of MappedDataLoader with Try and no options
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newMappedDataLoaderWithTry_withOptions() {
        // Verifies creation of MappedDataLoader with Try and options
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry(loader, options);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithTry_withName() {
        // Verifies creation of MappedDataLoader with Try, name, and options
        MappedBatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry("test-name", loader, options);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getName(), equalTo("test-name"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithTry_context_noOptions() {
        // Verifies creation of MappedDataLoader with Try, context, and no options
        MappedBatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newMappedDataLoaderWithTry_context_withOptions() {
        // Verifies creation of MappedDataLoader with Try, context, and options
        MappedBatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry(loader, options);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithTry_context_withName() {
        // Verifies creation of MappedDataLoader with Try, context, name, and options
        MappedBatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, Try::succeeded))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoaderWithTry("test-name", loader, options);

        assertNotNull(dl);
        assertThat(dl, notNullValue());
        assertThat(dl.getName(), equalTo("test-name"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }
}

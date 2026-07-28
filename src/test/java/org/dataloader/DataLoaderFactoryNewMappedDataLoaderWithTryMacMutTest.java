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
        // covers newMappedDataLoaderWithTry:272
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
        // covers newMappedDataLoaderWithTry:288
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
        // covers newMappedDataLoaderWithTry:305
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
        // covers newMappedDataLoaderWithTry:365
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
        // covers newMappedDataLoaderWithTry:381
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
        // covers newMappedDataLoaderWithTry:398
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

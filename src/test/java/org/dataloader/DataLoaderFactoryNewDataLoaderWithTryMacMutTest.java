package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewDataLoaderWithTryMacMutTest {

    @Test
    void newDataLoaderWithTry_noOptions() {
        // covers newDataLoaderWithTry:86
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithTry_withOptions() {
        // covers newDataLoaderWithTry:102
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader, options);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithTry_withNameAndOptions() {
        // covers newDataLoaderWithTry:119
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setMaxBatchSize(10).build();

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry("test-loader", loader, options);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test-loader"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithTry_context_noOptions() {
        // covers newDataLoaderWithTry:179
        BatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithTry_context_withOptions() {
        // covers newDataLoaderWithTry:195
        BatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(true).build();

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader, options);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithTry_context_withNameAndOptions() {
        // covers newDataLoaderWithTry:212
        BatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().setMaxBatchSize(5).build();

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry("context-loader", loader, options);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("context-loader"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }
}

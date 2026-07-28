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
        BatchLoader<String, Try<String>> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithTry_withOptions() {
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
        BatchLoaderWithContext<String, Try<String>> loader = (keys, env) -> CompletableFuture.completedFuture(
                keys.stream().map(Try::succeeded).collect(Collectors.toList())
        );

        DataLoader<String, String> dl = DataLoaderFactory.newDataLoaderWithTry(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithTry_context_withOptions() {
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

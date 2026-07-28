package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewDataLoaderMacMutTest {

    @Test
    void newDataLoaderWithBatchLoader() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithNameAndBatchLoader() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test", loader);
        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithBatchLoaderAndOptions() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader, options);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithNameBatchLoaderAndOptions() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test", loader, options);
        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithBatchLoaderWithContext() {
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithBatchLoaderWithContextAndOptions() {
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader, options);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithNameBatchLoaderWithContextAndOptions() {
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test", loader, options);
        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }
}

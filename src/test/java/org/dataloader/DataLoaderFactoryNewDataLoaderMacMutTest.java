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
        // covers newDataLoader:25
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithNameAndBatchLoader() {
        // covers newDataLoader:39
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test", loader);
        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithBatchLoaderAndOptions() {
        // covers newDataLoader:52
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader, options);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithNameBatchLoaderAndOptions() {
        // covers newDataLoader:66
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
        // covers newDataLoader:132
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
    }

    @Test
    void newDataLoaderWithBatchLoaderWithContextAndOptions() {
        // covers newDataLoader:145
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader, options);
        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newDataLoaderWithNameBatchLoaderWithContextAndOptions() {
        // covers newDataLoader:159
        BatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test", loader, options);
        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test"));
        assertThat(dl.getBatchLoadFunction(), equalTo(loader));
        assertThat(dl.getOptions(), equalTo(options));
    }
}

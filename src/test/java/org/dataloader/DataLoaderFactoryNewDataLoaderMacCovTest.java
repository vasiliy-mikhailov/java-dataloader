package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewDataLoaderMacCovTest {

    @Test
    void creates_data_loader_with_batch_loader() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
        assertThat(dl.getOptions(), notNullValue());
    }

    @Test
    void creates_data_loader_with_name_and_batch_loader() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test-name", loader);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test-name"));
        assertThat(dl.getBatchLoadFunction(), notNullValue());
    }

    @Test
    void creates_data_loader_with_batch_loader_and_options() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader(loader, options);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void creates_data_loader_with_name_batch_loader_and_options() {
        BatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(keys);
        DataLoaderOptions options = DataLoaderOptions.newOptions().setBatchingEnabled(false).build();
        DataLoader<String, String> dl = DataLoaderFactory.newDataLoader("test-name", loader, options);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("test-name"));
        assertThat(dl.getBatchLoadFunction(), notNullValue());
        assertThat(dl.getOptions(), equalTo(options));
    }
}

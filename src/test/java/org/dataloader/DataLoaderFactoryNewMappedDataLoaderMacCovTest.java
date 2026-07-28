package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewMappedDataLoaderMacCovTest {

    @Test
    void creates_mapped_data_loader_with_default_options() {
        MappedBatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, v -> v + "_loaded"))
        );

        DataLoader<String, String> dataLoader = DataLoaderFactory.newMappedDataLoader(loader);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
        assertThat(dataLoader.getOptions(), notNullValue());
    }

    @Test
    void creates_mapped_data_loader_with_custom_options() {
        MappedBatchLoader<Integer, String> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, v -> "val_" + v))
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions()
                .setBatchingEnabled(false)
                .setCachingEnabled(false)
                .build();

        DataLoader<Integer, String> dataLoader = DataLoaderFactory.newMappedDataLoader(loader, options);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
        assertThat(dataLoader.getOptions(), equalTo(options));
    }

    @Test
    void creates_mapped_data_loader_with_null_options() {
        MappedBatchLoader<Long, Long> loader = keys -> CompletableFuture.completedFuture(
                keys.stream().collect(Collectors.toMap(k -> k, v -> v * 2))
        );

        DataLoader<Long, Long> dataLoader = DataLoaderFactory.newMappedDataLoader(loader, null);

        assertNotNull(dataLoader);
        assertThat(dataLoader.getBatchLoadFunction(), equalTo(loader));
        assertThat(dataLoader.getOptions(), notNullValue());
    }
}

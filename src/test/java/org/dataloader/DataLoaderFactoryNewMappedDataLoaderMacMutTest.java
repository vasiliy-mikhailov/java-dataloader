package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataLoaderFactoryNewMappedDataLoaderMacMutTest {

    @Test
    void newMappedDataLoaderWithMappedBatchLoader() {
        // Verifies creation of MappedDataLoader with MappedBatchLoader
        MappedBatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderAndOptions() {
        // Verifies creation of MappedDataLoader with MappedBatchLoader and Options
        MappedBatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader, options);

        assertNotNull(dl);
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderAndNameAndOptions() {
        // Verifies creation of MappedDataLoader with MappedBatchLoader, Name, and Options
        MappedBatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader("testName", loader, options);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("testName"));
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderWithContext() {
        // Verifies creation of MappedDataLoader with MappedBatchLoaderWithContext
        MappedBatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderWithContextAndOptions() {
        // Verifies creation of MappedDataLoader with MappedBatchLoaderWithContext and Options
        MappedBatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader, options);

        assertNotNull(dl);
        assertThat(dl.getOptions(), equalTo(options));
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderWithContextAndNameAndOptions() {
        // Verifies creation of MappedDataLoader with MappedBatchLoaderWithContext, Name, and Options
        MappedBatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );
        DataLoaderOptions options = DataLoaderOptions.newOptions().build();

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader("testName", loader, options);

        assertNotNull(dl);
        assertThat(dl.getName(), equalTo("testName"));
        assertThat(dl.getOptions(), equalTo(options));
    }
}

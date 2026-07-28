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
        // covers newMappedDataLoader:225
        MappedBatchLoader<String, String> loader = keys -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderAndOptions() {
        // covers newMappedDataLoader:238
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
        // covers newMappedDataLoader:251
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
        // covers newMappedDataLoader:318
        MappedBatchLoaderWithContext<String, String> loader = (keys, env) -> CompletableFuture.completedFuture(
                Collections.singletonMap("k", "v")
        );

        DataLoader<String, String> dl = DataLoaderFactory.newMappedDataLoader(loader);

        assertNotNull(dl);
        assertThat(dl.getBatchLoadFunction(), notNullValue());
    }

    @Test
    void newMappedDataLoaderWithMappedBatchLoaderWithContextAndOptions() {
        // covers newMappedDataLoader:331
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
        // covers newMappedDataLoader:345
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

package org.dataloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.dataloader.DataLoaderFactory.newDataLoader;

public class DataLoaderRegistryRegisterMacMutTest {

    final BatchLoader<Object, Object> identityBatchLoader = CompletableFuture::completedFuture;

    @Test
    public void registerReturnsThis() {
        // covers register:148
        DataLoader<Object, Object> dl = newDataLoader("a", identityBatchLoader);
        DataLoaderRegistry registry = new DataLoaderRegistry();
        DataLoaderRegistry result = registry.register(dl);
        Assertions.assertNotNull(result);
        Assertions.assertSame(registry, result);
    }
}

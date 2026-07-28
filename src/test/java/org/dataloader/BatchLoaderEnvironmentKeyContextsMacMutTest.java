package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BatchLoaderEnvironmentKeyContextsMacMutTest {

    @Test
    void keyContextsBoundary() {
        // covers keyContexts:92
        List<String> keys = Arrays.asList("a", "b");
        List<Object> contexts = Arrays.asList("ctxA");

        BatchLoaderEnvironment env = BatchLoaderEnvironment.newBatchLoaderEnvironment()
                .keyContexts(keys, contexts)
                .build();

        assertThat(env.getKeyContexts().size(), equalTo(1));
        assertThat(env.getKeyContexts().containsKey("a"), equalTo(true));
        assertThat(env.getKeyContexts().containsKey("b"), equalTo(false));
        assertThat(env.getKeyContextsList().size(), equalTo(2));
        assertThat(env.getKeyContextsList().get(0), equalTo("ctxA"));
        assertThat(env.getKeyContextsList().get(1), equalTo(null));
    }
}

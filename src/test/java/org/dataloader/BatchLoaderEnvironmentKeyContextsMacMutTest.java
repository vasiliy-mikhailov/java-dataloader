package org.dataloader;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BatchLoaderEnvironmentKeyContextsMacMutTest {

    @Test
    void keyContextsBoundary() {
        // covers keyContexts:92
        List<String> keys = Arrays.asList("a", "b");
        List<Object> contexts = Arrays.asList("ctxA"); // size 1, keys size 2

        BatchLoaderEnvironment env = BatchLoaderEnvironment.newBatchLoaderEnvironment()
                .keyContexts(keys, contexts)
                .build();

        // The boundary is at i=1 (keys.size()=2, contexts.size()=1).
        // i=0: i < 1 is true, context is "ctxA". Map has "a"->"ctxA". List has "ctxA".
        // i=1: i < 1 is false, context is null. Map does NOT have "b". List has null.
        // ConditionalsBoundaryMutator might change i < keyContexts.size() to i <= keyContexts.size()
        // which would cause an IndexOutOfBoundsException at i=1. Or it might change the logic
        // such that the null handling is different.
        // We assert the exact state to ensure the boundary logic is correct.
        assertThat(env.getKeyContexts().size(), equalTo(1));
        assertThat(env.getKeyContexts().containsKey("a"), equalTo(true));
        assertThat(env.getKeyContexts().containsKey("b"), equalTo(false));
        assertThat(env.getKeyContextsList().size(), equalTo(2));
        assertThat(env.getKeyContextsList().get(0), equalTo("ctxA"));
        assertThat(env.getKeyContextsList().get(1), equalTo(null));
    }
}

package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Arrays.asList;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class PromisedValuesImplSetCauseMacMutTest {

    @Test
    public void setCause_wraps_completion_exception_cause() throws Exception {
        // covers setCause:58
        RuntimeException rootCause = new RuntimeException("root");
        CompletableFuture<String> failingFuture = new CompletableFuture<>();
        failingFuture.completeExceptionally(new CompletionException(rootCause));

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(failingFuture));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(RuntimeException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("root"));
    }

    @Test
    public void setCause_sets_non_completion_exception_directly() throws Exception {
        // covers setCause:58
        RuntimeException directCause = new RuntimeException("direct");
        CompletableFuture<String> failingFuture = new CompletableFuture<>();
        failingFuture.completeExceptionally(directCause);

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(failingFuture));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(RuntimeException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("direct"));
    }

    @Test
    public void setCause_handles_null_cause_in_completion_exception() throws Exception {
        // covers setCause:58
        CompletableFuture<String> failingFuture = new CompletableFuture<>();
        failingFuture.completeExceptionally(new CompletionException((Throwable) null));

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(failingFuture));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(CompletionException.class));
    }

    @Test
    public void setCause_preserves_cause_when_thenAccept_is_used() throws Exception {
        // covers setCause:58
        RuntimeException rootCause = new RuntimeException("root");
        CompletableFuture<String> failingFuture = new CompletableFuture<>();
        failingFuture.completeExceptionally(new CompletionException(rootCause));

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(failingFuture));

        // Trigger the internal logic that calls setCause via the completion handling
        promisedValues.thenAccept(values -> {
            // This should not be called as the future failed
        });

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(RuntimeException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("root"));
    }
}

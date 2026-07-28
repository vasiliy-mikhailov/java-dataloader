package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Arrays.asList;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PromisedValuesImplSetCauseMacCovTest {

    @Test
    public void setCause_wraps_completion_exception_cause() throws Exception {
        // Verifies unwrapping CompletionException to store root cause
        CompletableFuture<String> f1 = supplyAsync(() -> {
            throw new CompletionException(new IllegalStateException("Root Cause"));
        });

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(f1));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(IllegalStateException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("Root Cause"));
    }

    @Test
    public void setCause_stores_non_completion_exception_directly() throws Exception {
        // Verifies storing non-CompletionException directly
        CompletableFuture<String> f1 = supplyAsync(() -> {
            throw new IllegalArgumentException("Direct Error");
        });

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(f1));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(IllegalArgumentException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("Direct Error"));
    }

    @Test
    public void setCause_handles_completion_exception_with_null_cause() throws Exception {
        // Verifies storing CompletionException when cause is null
        CompletableFuture<String> f1 = supplyAsync(() -> {
            throw new CompletionException("No Cause", null);
        });

        PromisedValues<String> promisedValues = PromisedValues.allOf(asList(f1));

        await().until(promisedValues::isDone, is(true));

        assertThat(promisedValues.failed(), is(true));
        assertThat(promisedValues.cause(), notNullValue());
        assertThat(promisedValues.cause(), instanceOf(CompletionException.class));
        assertThat(promisedValues.cause().getMessage(), equalTo("No Cause"));
    }
}

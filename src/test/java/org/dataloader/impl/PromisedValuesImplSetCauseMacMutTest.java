package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class PromisedValuesImplSetCauseMacMutTest {

    @Test
    public void setCause_wraps_completion_exception_cause() {
        // Given a CompletionException wrapping a specific cause
        RuntimeException rootCause = new RuntimeException("Root");
        CompletionException wrapper = new CompletionException(rootCause);
        CompletableFuture<String> f1 = new CompletableFuture<>();
        f1.completeExceptionally(wrapper);

        // When we combine and wait for failure
        PromisedValues<String> pv = PromisedValues.allOf(asList(f1));
        assertThat(pv.isDone(), equalTo(true));
        assertThat(pv.failed(), equalTo(true));

        // Then the cause should be unwrapped to the root cause
        Throwable cause = pv.cause();
        assertThat(cause, notNullValue());
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("Root"));
    }

    @Test
    public void setCause_keeps_non_completion_exception() {
        // Given a plain exception
        RuntimeException ex = new RuntimeException("Plain");
        CompletableFuture<String> f1 = new CompletableFuture<>();
        f1.completeExceptionally(ex);

        // When we combine and wait for failure
        PromisedValues<String> pv = PromisedValues.allOf(asList(f1));
        assertThat(pv.isDone(), equalTo(true));
        assertThat(pv.failed(), equalTo(true));

        // Then the cause should be the original exception
        Throwable cause = pv.cause();
        assertThat(cause, notNullValue());
        assertThat(cause, instanceOf(RuntimeException.class));
        assertThat(cause.getMessage(), equalTo("Plain"));
    }

    @Test
    public void setCause_handles_completion_exception_with_null_cause() {
        // Given a CompletionException with null cause
        CompletionException wrapper = new CompletionException((Throwable) null);
        CompletableFuture<String> f1 = new CompletableFuture<>();
        f1.completeExceptionally(wrapper);

        // When we combine and wait for failure
        PromisedValues<String> pv = PromisedValues.allOf(asList(f1));
        assertThat(pv.isDone(), equalTo(true));
        assertThat(pv.failed(), equalTo(true));

        // Then the cause should be the wrapper itself since getCause() is null
        Throwable cause = pv.cause();
        assertThat(cause, notNullValue());
        assertThat(cause, instanceOf(CompletionException.class));
    }
}

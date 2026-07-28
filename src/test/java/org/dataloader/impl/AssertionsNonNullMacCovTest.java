package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

class AssertionsNonNullMacCovTest {

    @Test
    void nonNull_returns_same_instance_when_not_null() {
        String value = "test";
        String result = Assertions.nonNull(value);
        assertThat(result, sameInstance(value));
    }

    @Test
    void nonNull_with_message_returns_same_instance_when_not_null() {
        String value = "test";
        String result = Assertions.nonNull(value, () -> "should not be null");
        assertThat(result, sameInstance(value));
    }

    @Test
    void nonNull_throws_when_null() {
        try {
            Assertions.nonNull((String) null);
            assertThat(false, equalTo(true)); // should not reach here
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalTo("nonNull object required"));
        }
    }
}

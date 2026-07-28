package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertionsNonNullMacMutTest {

    @Test
    void nonNullReturnsObject() {
        String value = "test";
        String result = Assertions.nonNull(value);
        assertThat(result, notNullValue());
        assertThat(result, equalTo(value));
    }

    @Test
    void nonNullWithMessageReturnsObject() {
        String value = "test";
        String result = Assertions.nonNull(value, () -> "should not be null");
        assertThat(result, notNullValue());
        assertThat(result, equalTo(value));
    }

    @Test
    void nonNullThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> Assertions.nonNull((String) null));
    }
}

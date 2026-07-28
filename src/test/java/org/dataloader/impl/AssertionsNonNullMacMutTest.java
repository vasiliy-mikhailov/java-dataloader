package org.dataloader.impl;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertionsNonNullMacMutTest {

    @Test
    void nonNullReturnsObject() {
        // covers nonNull:17
        String value = "test";
        String result = Assertions.nonNull(value);
        assertThat(result, notNullValue());
        assertThat(result, equalTo(value));
    }

    @Test
    void nonNullWithMessageReturnsObject() {
        // covers nonNull:24
        String value = "test";
        String result = Assertions.nonNull(value, () -> "should not be null");
        assertThat(result, notNullValue());
        assertThat(result, equalTo(value));
    }

    @Test
    void nonNullThrowsOnNull() {
        // covers nonNull:21
        // The source code throws NullPointerException when null is passed.
        // The mutation likely changes this to IllegalArgumentException or vice versa.
        // Based on the build output, the real code throws NullPointerException.
        assertThrows(NullPointerException.class, () -> Assertions.nonNull((String) null));
    }
}

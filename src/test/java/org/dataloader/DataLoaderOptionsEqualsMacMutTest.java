package org.dataloader;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DataLoaderOptionsEqualsMacMutTest {

    @Test
    void equalsReturnsFalseForNull() {
        // covers equals:129
        DataLoaderOptions options = DataLoaderOptions.newDefaultOptions();
        assertThat(options.equals(null), equalTo(false));
    }

    @Test
    void equalsReturnsFalseForDifferentClass() {
        // covers equals:129
        DataLoaderOptions options = DataLoaderOptions.newDefaultOptions();
        assertThat(options.equals("not an options object"), equalTo(false));
    }
}

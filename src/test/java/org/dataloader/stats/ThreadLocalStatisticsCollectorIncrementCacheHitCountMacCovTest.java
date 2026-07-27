package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementCacheHitCountMacCovTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementCacheHitCountIncreasesCount() {
        // Reset to ensure clean state
        collector.resetThread();

        // Initial state
        assertThat(collector.getStatistics().getCacheHitCount(), equalTo(0L));
        assertThat(collector.getOverallStatistics().getCacheHitCount(), equalTo(0L));

        // Act
        collector.incrementCacheHitCount();

        // Assert
        assertThat(collector.getStatistics().getCacheHitCount(), equalTo(1L));
        assertThat(collector.getOverallStatistics().getCacheHitCount(), equalTo(1L));
    }

    @Test
    void incrementCacheHitCountAccumulates() {
        collector.resetThread();

        collector.incrementCacheHitCount();
        collector.incrementCacheHitCount();

        assertThat(collector.getStatistics().getCacheHitCount(), equalTo(2L));
        assertThat(collector.getOverallStatistics().getCacheHitCount(), equalTo(2L));
    }
}

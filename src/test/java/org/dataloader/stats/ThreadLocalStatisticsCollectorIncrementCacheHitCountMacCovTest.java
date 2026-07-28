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
        Statistics initialStats = collector.getStatistics();
        long initialCacheHits = initialStats.getCacheHitCount();

        // Act
        collector.incrementCacheHitCount();

        // Assert
        Statistics afterStats = collector.getStatistics();
        assertThat(afterStats.getCacheHitCount(), equalTo(initialCacheHits + 1));
    }

    @Test
    void incrementCacheHitCountMultipleTimes() {
        collector.resetThread();

        collector.incrementCacheHitCount();
        collector.incrementCacheHitCount();
        collector.incrementCacheHitCount();

        Statistics stats = collector.getStatistics();
        assertThat(stats.getCacheHitCount(), equalTo(3L));
    }

}

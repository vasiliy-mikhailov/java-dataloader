package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementLoadCountMacCovTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementLoadCountIncreasesLoadCount() {
        // Reset to ensure clean state
        collector.resetThread();

        // Initial state
        Statistics initialStats = collector.getStatistics();
        long initialLoadCount = initialStats.getLoadCount();

        // Act
        collector.incrementLoadCount();

        // Assert
        Statistics updatedStats = collector.getStatistics();
        assertThat(updatedStats.getLoadCount(), equalTo(initialLoadCount + 1));
    }

    @Test
    void incrementLoadCountIncreasesOverallLoadCount() {
        // Reset to ensure clean state
        collector.resetThread();

        // Initial state
        Statistics initialOverallStats = collector.getOverallStatistics();
        long initialOverallLoadCount = initialOverallStats.getLoadCount();

        // Act
        collector.incrementLoadCount();

        // Assert
        Statistics updatedOverallStats = collector.getOverallStatistics();
        assertThat(updatedOverallStats.getLoadCount(), equalTo(initialOverallLoadCount + 1));
    }

    @Test
    void multipleIncrementLoadCountCallsAccumulate() {
        // Reset to ensure clean state
        collector.resetThread();

        // Act
        collector.incrementLoadCount();
        collector.incrementLoadCount();
        collector.incrementLoadCount();

        // Assert
        Statistics stats = collector.getStatistics();
        assertThat(stats.getLoadCount(), equalTo(3L));
    }
}

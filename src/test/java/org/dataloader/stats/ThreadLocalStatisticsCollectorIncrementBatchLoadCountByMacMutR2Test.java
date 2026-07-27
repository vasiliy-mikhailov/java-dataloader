package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementBatchLoadCountByMacMutR2Test {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementBatchLoadCountBy_updates_overall_stats() {
        // Call the method that the mutant modifies (removes a call to overallCollector)
        collector.incrementBatchLoadCountBy(5);

        // Assert that the overall statistics reflect the increment
        Statistics overallStats = collector.getOverallStatistics();
        assertThat(overallStats.getBatchLoadCount(), equalTo(5L));
    }
}

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
        collector.incrementBatchLoadCountBy(5);
        Statistics overallStats = collector.getOverallStatistics();
        assertThat(overallStats.getBatchLoadCount(), equalTo(5L));
    }
}

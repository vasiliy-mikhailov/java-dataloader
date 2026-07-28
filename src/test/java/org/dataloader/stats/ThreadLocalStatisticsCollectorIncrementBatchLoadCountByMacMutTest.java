package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementBatchLoadCountByMacMutTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setup() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementBatchLoadCountByWithDeltaAndContextOverall() {
        // covers incrementBatchLoadCountBy:70
        collector.incrementBatchLoadCountBy(3, new org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext<>("key", 3));
        Statistics overallStats = collector.getOverallStatistics();
        assertThat(overallStats.getBatchLoadCount(), equalTo(3L));
    }
}

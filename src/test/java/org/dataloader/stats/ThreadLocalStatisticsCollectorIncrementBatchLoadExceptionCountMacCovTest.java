package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementBatchLoadExceptionCountMacCovTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementBatchLoadExceptionCount_isolated_from_other_counts() {
        collector.incrementLoadCount();
        collector.incrementBatchLoadExceptionCount();

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadExceptionCount(), equalTo(1L));
        assertThat(stats.getLoadCount(), equalTo(1L));
    }
}

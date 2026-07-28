package org.dataloader.stats;

import org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementBatchLoadCountByMacCovTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementBatchLoadCountBy_accumulates() {
        collector.incrementBatchLoadCountBy(2);
        collector.incrementBatchLoadCountBy(3);

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
    }
}

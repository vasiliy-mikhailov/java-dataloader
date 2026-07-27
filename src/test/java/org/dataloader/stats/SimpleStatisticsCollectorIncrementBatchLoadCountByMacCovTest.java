package org.dataloader.stats;

import org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleStatisticsCollectorIncrementBatchLoadCountByMacCovTest {

    @Test
    void incrementBatchLoadCountByAddsDelta() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 5);
        collector.incrementBatchLoadCountBy(5, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
        assertThat(stats.getBatchInvokeCount(), equalTo(1L));
    }

    @Test
    void incrementBatchLoadCountByAccumulates() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 3);
        collector.incrementBatchLoadCountBy(3, context);
        collector.incrementBatchLoadCountBy(7, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(10L));
        assertThat(stats.getBatchInvokeCount(), equalTo(2L));
    }

    @Test
    void incrementBatchLoadCountByWithZeroDelta() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 0);
        collector.incrementBatchLoadCountBy(0, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(0L));
        assertThat(stats.getBatchInvokeCount(), equalTo(1L));
    }
}

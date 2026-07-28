package org.dataloader.stats;

import org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleStatisticsCollectorIncrementBatchLoadCountByMacCovTest {

    @Test
    void incrementBatchLoadCountByAddsDelta() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 1);
        collector.incrementBatchLoadCountBy(5, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
        assertThat(stats.getBatchInvokeCount(), equalTo(1L));
    }

    @Test
    void incrementBatchLoadCountByAccumulates() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 1);
        collector.incrementBatchLoadCountBy(10, context);
        collector.incrementBatchLoadCountBy(20, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(30L));
        assertThat(stats.getBatchInvokeCount(), equalTo(2L));
    }
}

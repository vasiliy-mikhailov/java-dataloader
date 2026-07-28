package org.dataloader.stats;

import org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleStatisticsCollectorIncrementBatchLoadCountByMacMutTest {

    @Test
    void incrementBatchLoadCountByIncrementsBatchInvokeCount() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 1);
        collector.incrementBatchLoadCountBy(1, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchInvokeCount(), equalTo(1L));
    }

    @Test
    void incrementBatchLoadCountByAddsToBatchLoadCount() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("key", 1);
        collector.incrementBatchLoadCountBy(5, context);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
    }

    @Test
    void incrementBatchLoadCountByDelegatesToOverload() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        collector.incrementBatchLoadCountBy(10);
        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchInvokeCount(), equalTo(1L));
        assertThat(stats.getBatchLoadCount(), equalTo(10L));
    }
}

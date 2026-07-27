package org.dataloader.stats;

import org.dataloader.stats.context.IncrementBatchLoadCountByStatisticsContext;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleStatisticsCollectorIncrementBatchLoadCountByMacMutTest {

    @Test
    void incrementBatchLoadCountByIncrementsBatchInvokeCount() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        Statistics before = collector.getStatistics();

        collector.incrementBatchLoadCountBy(1, new IncrementBatchLoadCountByStatisticsContext<String>("key", 1));

        Statistics after = collector.getStatistics();
        assertThat(after.getBatchInvokeCount(), equalTo(before.getBatchInvokeCount() + 1));
    }

    @Test
    void incrementBatchLoadCountByAddsToBatchLoadCount() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        Statistics before = collector.getStatistics();

        collector.incrementBatchLoadCountBy(5, new IncrementBatchLoadCountByStatisticsContext<String>("key", 1));

        Statistics after = collector.getStatistics();
        assertThat(after.getBatchLoadCount(), equalTo(before.getBatchLoadCount() + 5));
    }

    @Test
    void incrementBatchLoadCountByWithContextDelegatesCorrectly() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();
        Statistics before = collector.getStatistics();

        collector.incrementBatchLoadCountBy(10, new IncrementBatchLoadCountByStatisticsContext<String>("key", 1));

        Statistics after = collector.getStatistics();
        assertThat(after.getBatchInvokeCount(), equalTo(before.getBatchInvokeCount() + 1));
        assertThat(after.getBatchLoadCount(), equalTo(before.getBatchLoadCount() + 10));
    }
}

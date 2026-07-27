package org.dataloader.stats;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleStatisticsCollectorIncrementBatchLoadCountByMacMutR2Test {

    @Test
    void incrementBatchLoadCountByIncrementsBatchInvokeCount() {
        SimpleStatisticsCollector collector = new SimpleStatisticsCollector();

        Statistics before = collector.getStatistics();

        collector.incrementBatchLoadCountBy(10);

        Statistics after = collector.getStatistics();
        assertThat(after.getBatchInvokeCount(), equalTo(before.getBatchInvokeCount() + 1));
    }
}
package org.dataloader.stats;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DelegatingStatisticsCollectorIncrementBatchLoadCountByMacMutTest {

    @Test
    void delegates_incrementBatchLoadCountBy_to_collector() {
        StatisticsCollector delegate = new StatisticsCollector() {
            @Override public void incrementLoadCount() {}
            @Override public void incrementLoadErrorCount() {}
            @Override public void incrementBatchLoadCountBy(long delta) {}
            @Override public void incrementBatchLoadExceptionCount() {}
            @Override public void incrementCacheHitCount() {}
            @Override public Statistics getStatistics() { return new Statistics(); }
        };

        DelegatingStatisticsCollector collector = new DelegatingStatisticsCollector(delegate);

        collector.incrementBatchLoadCountBy(5);

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
    }
}

package org.dataloader.stats;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DelegatingStatisticsCollectorIncrementLoadCountMacCovTest {

    @Test
    void incrementLoadCount_delegates_to_both_collectors() {
        StatisticsCollector delegate = new NoOpStatisticsCollector();
        DelegatingStatisticsCollector collector = new DelegatingStatisticsCollector(delegate);

        collector.incrementLoadCount();

        Statistics stats = collector.getStatistics();
        assertThat(stats.getLoadCount(), equalTo(1L));
    }

    @Test
    void incrementLoadCount_accumulates_correctly() {
        StatisticsCollector delegate = new NoOpStatisticsCollector();
        DelegatingStatisticsCollector collector = new DelegatingStatisticsCollector(delegate);

        collector.incrementLoadCount();
        collector.incrementLoadCount();

        Statistics stats = collector.getStatistics();
        assertThat(stats.getLoadCount(), equalTo(2L));
    }
}

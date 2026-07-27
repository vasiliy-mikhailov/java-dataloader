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
    void incrementBatchLoadCountBy_deprecated_no_context() {
        collector.resetThread();

        collector.incrementBatchLoadCountBy(5);

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(5L));
    }

    @Test
    void incrementBatchLoadCountBy_with_context() {
        collector.resetThread();

        IncrementBatchLoadCountByStatisticsContext<String> context = new IncrementBatchLoadCountByStatisticsContext<>("test-key", 10);
        collector.incrementBatchLoadCountBy(10, context);

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(10L));
    }

    @Test
    void incrementBatchLoadCountBy_accumulates() {
        collector.resetThread();

        collector.incrementBatchLoadCountBy(5);
        collector.incrementBatchLoadCountBy(3);

        Statistics stats = collector.getStatistics();
        assertThat(stats.getBatchLoadCount(), equalTo(8L));
    }
}

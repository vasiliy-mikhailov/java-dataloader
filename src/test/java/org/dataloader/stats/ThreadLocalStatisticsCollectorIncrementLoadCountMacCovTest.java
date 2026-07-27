package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ThreadLocalStatisticsCollectorIncrementLoadCountMacCovTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setUp() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementLoadCountIncreasesLoadCount() {
        collector.resetThread();
        Statistics initialStats = collector.getStatistics();
        long initialLoadCount = initialStats.getLoadCount();
        collector.incrementLoadCount();
        Statistics updatedStats = collector.getStatistics();
        assertThat(updatedStats.getLoadCount(), equalTo(initialLoadCount + 1));
    }

    @Test
    void incrementLoadCountIncreasesOverallLoadCount() {
        collector.resetThread();
        Statistics initialOverallStats = collector.getOverallStatistics();
        long initialOverallLoadCount = initialOverallStats.getLoadCount();
        collector.incrementLoadCount();
        Statistics updatedOverallStats = collector.getOverallStatistics();
        assertThat(updatedOverallStats.getLoadCount(), equalTo(initialOverallLoadCount + 1));
    }

    @Test
    void multipleIncrementLoadCountCallsAccumulate() {
        collector.resetThread();
        collector.incrementLoadCount();
        collector.incrementLoadCount();
        collector.incrementLoadCount();
        Statistics stats = collector.getStatistics();
        assertThat(stats.getLoadCount(), equalTo(3L));
    }
}

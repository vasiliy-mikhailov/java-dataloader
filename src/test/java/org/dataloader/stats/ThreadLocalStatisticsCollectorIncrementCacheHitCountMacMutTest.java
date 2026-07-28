package org.dataloader.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ThreadLocalStatisticsCollectorIncrementCacheHitCountMacMutTest {

    private ThreadLocalStatisticsCollector collector;

    @BeforeEach
    void setup() {
        collector = new ThreadLocalStatisticsCollector();
    }

    @Test
    void incrementCacheHitCountUpdatesOverallStats() {
        collector.incrementCacheHitCount();
        Statistics stats = collector.getOverallStatistics();
        assertThat(stats.getCacheHitCount(), equalTo(1L));
    }

}

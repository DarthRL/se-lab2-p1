package MathsHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void test() {
        Statistics statistics = new Statistics();
        assertEquals(0, statistics.getTot());
        assertEquals(0, statistics.getRight());
        statistics.incTot();
        assertEquals(1, statistics.getTot());
        assertEquals(0, statistics.getRight());
        statistics.incRight();
        assertEquals(1, statistics.getTot());
        assertEquals(1, statistics.getRight());
    }
}
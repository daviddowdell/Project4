import static org.junit.Assert.*;

import org.junit.Test;

public class StatsTypeTest
{

    @Test
    public void test()
    {
        
        StatsType a = StatsType.AVERAGE;
        StatsType b = StatsType.MAXIMU;
        StatsType c = StatsType.MINIMUM;
        StatsType d = StatsType.TOTAL;
        
        assertEquals(StatsType.AVERAGE, a);
        assertEquals(StatsType.MAXIMU, b);
        assertEquals(StatsType.MINIMUM, c);
        assertEquals(StatsType.TOTAL, d);
    }

}

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.Test;

public class StatisticsTest
{

    @Test
    public void testStatistics()
    {
        //Create instance using each constructor
        StatsType t = StatsType.MINIMUM;
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
        Statistics a = new Statistics(20, "name", zdt, 100, t);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics b = new Statistics(20, "name", d, 100, t);
        
        //Test value
        assertEquals(20, a.getValue(), 0.01);
        assertEquals(20, b.getValue(), 0.01);
        //Test stid
        assertEquals("name",a.getStid());
        assertEquals("name",b.getStid());
        //Test time/date
        assertEquals("2018-09-30T17:30:00 UTC",
                a.getUTCDateTimeString());
        assertEquals("2018-09-30T17:30:00 UTC", 
                b.getUTCDateTimeString());
        //Test number of stations
        assertEquals(100, a.getNumberOfReportingStations());
        assertEquals(100, b.getNumberOfReportingStations());
        
        //StatsType will be tested in the toString test
    }
    
    @Test
    public void testCreateDateFromString()
    {
        // Make the calendar, make the string, compare them.
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        String b = "2018-09-30T17:30:00 UTC";
        assertEquals(d, Statistics.createDateFromString(b));
        
        //Now do it with ZonedDateTime
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
        
        assertEquals(zdt.toString(), Statistics.createZDateFromString(b).toString());
          
    }
    
    @Test
    public void testCreateStringFromDate()
    {
        // Make the calendar, make the string, compare them.
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        String b = "2018-09-30T17:30:00 UTC";
        assertEquals(b, Statistics.createStringFromDate(d));
        
        //Now do it with ZonedDateTime
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
       
        assertEquals(b, Statistics.createStringFromDate(zdt));
    }
    
    @Test
    public void testGetNumberOfReportingStations()
    {
        // Use both constructors, test getter for each
        StatsType t = StatsType.MINIMUM;
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt, 100, t);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics b = new Statistics(20, "name", d, 100, t);
        
        assertEquals(100, a.getNumberOfReportingStations());
        assertEquals(100, b.getNumberOfReportingStations());
    }
    
    @Test
    public void testGetUTCDateTimeString()
    {
        // Use both constructors, test getter for each
        StatsType t = StatsType.MINIMUM;
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt,100, t);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics b = new Statistics(20, "name", d, 100, t);
       
        //Test time/date
        assertEquals("2018-09-30T17:30:00 UTC",
                a.getUTCDateTimeString());
        assertEquals("2018-09-30T17:30:00 UTC", 
                b.getUTCDateTimeString());
    }
    
    @Test
    public void testNewerThan()
    {
        //Create base instances of statistics
        StatsType t = StatsType.MINIMUM;
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 05, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt,100, t);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,05);
        Statistics b = new Statistics(20, "name", d, 100, t);
        
        //Create GregorianCalendar objects that are older in time, date;
        GregorianCalendar f = new GregorianCalendar(2017,9,30,17,30,05);
        GregorianCalendar g = new GregorianCalendar(2018,8,30,17,30,05);
        GregorianCalendar h = new GregorianCalendar(2018,9,29,17,30,05);
        GregorianCalendar j = new GregorianCalendar(2018,9,30,16,30,05);
        GregorianCalendar k = new GregorianCalendar(2018,9,30,17,29,05);
        GregorianCalendar l = new GregorianCalendar(2018,9,30,17,30,04);
        //And one that isn't.
        GregorianCalendar p = new GregorianCalendar(2018,9,30,17,30,05);
        
        //Same for ZonedDateTime objects
        ZonedDateTime q = ZonedDateTime.of(2017,9,30,17,30,05,0,zone);
        ZonedDateTime w = ZonedDateTime.of(2018,8,30,17,30,05,0,zone);
        ZonedDateTime e = ZonedDateTime.of(2018,9,29,17,30,05,0,zone);
        ZonedDateTime r = ZonedDateTime.of(2018,9,30,16,30,05,0,zone);
        ZonedDateTime y = ZonedDateTime.of(2018,9,30,17,29,05,0,zone);
        ZonedDateTime u = ZonedDateTime.of(2018,9,30,17,30,04,0,zone);
        ZonedDateTime n = ZonedDateTime.of(2018,9,30,17,30,05,0,zone);
      
        //Test
        assertTrue(a.newerThan(f));
        assertTrue(a.newerThan(g));
        assertTrue(a.newerThan(h));
        assertTrue(a.newerThan(j));
        assertTrue(a.newerThan(k));
        assertTrue(a.newerThan(l));
        assertFalse(a.newerThan(p));
        assertTrue (b.newerThan(f));
        assertTrue(b.newerThan(g));
        assertTrue(b.newerThan(h));
        assertTrue(b.newerThan(j));
        assertTrue(b.newerThan(k));
        assertTrue(a.newerThan(l));
        assertFalse(a.newerThan(p));
        //ZonedDateTime Section
        assertTrue(a.newerThan(q));
        assertTrue(a.newerThan(w));
        assertTrue(a.newerThan(e));
        assertTrue(a.newerThan(r));
        assertTrue(a.newerThan(y));
        assertTrue(a.newerThan(u));
        assertFalse(a.newerThan(n));
        assertTrue (b.newerThan(q));
        assertTrue(b.newerThan(w));
        assertTrue(b.newerThan(e));
        assertTrue(b.newerThan(r));
        assertTrue(b.newerThan(y));
        assertTrue(a.newerThan(u));
        assertFalse(a.newerThan(n));
    }
    
    @Test
    public void testOlderThan()
    {
        //Create base instances of statistics
        StatsType t = StatsType.MINIMUM; ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 29, 17, 30, 05, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt,100, t);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,29,17,30,05);
        Statistics b = new Statistics(20, "name", d, 100, t);
        
        //Create GregorianCalendar objects that are newer in time, date;
        GregorianCalendar f = new GregorianCalendar(2019,9,29,17,30,05);
        GregorianCalendar g = new GregorianCalendar(2018,10,29,17,30,05);
        GregorianCalendar h = new GregorianCalendar(2018,9,30,17,30,05);
        GregorianCalendar j = new GregorianCalendar(2018,9,29,18,30,05);
        GregorianCalendar k = new GregorianCalendar(2018,9,29,17,31,05);
        GregorianCalendar l = new GregorianCalendar(2018,9,29,17,30,06);
        //And one that isn't.
        GregorianCalendar p = new GregorianCalendar(2018,9,29,17,30,05);
        
      //Same for ZonedDateTime objects
        ZonedDateTime q = ZonedDateTime.of(2019,9,29,17,30,05,0,zone);
        ZonedDateTime w = ZonedDateTime.of(2018,10,29,17,30,05,0,zone);
        ZonedDateTime e = ZonedDateTime.of(2018,9,30,17,30,05,0,zone);
        ZonedDateTime r = ZonedDateTime.of(2018,9,29,18,30,05,0,zone);
        ZonedDateTime y = ZonedDateTime.of(2018,9,29,17,31,05,0,zone);
        ZonedDateTime u = ZonedDateTime.of(2018,9,29,17,30,6,0,zone);
        ZonedDateTime n = ZonedDateTime.of(2018,9,29,17,30,05,0,zone);
        
        //Test
        assertTrue(a.olderThan(f));
        assertTrue(a.olderThan(g));
        assertTrue(a.olderThan(h));
        assertTrue(a.olderThan(j));
        assertTrue(a.olderThan(k));
        assertTrue(a.olderThan(l));
        assertFalse(a.olderThan(p));
        assertTrue (b.olderThan(f));
        assertTrue(b.olderThan(g));
        assertTrue(b.olderThan(h));
        assertTrue(b.olderThan(j));
        assertTrue(b.olderThan(k));
        assertTrue(b.olderThan(l));
        assertFalse(b.olderThan(p));
        //ZonedDateTime Section
        assertTrue(a.olderThan(q));
        assertTrue(a.olderThan(w));
        assertTrue(a.olderThan(e));
        assertTrue(a.olderThan(r));
        assertTrue(a.olderThan(y));
        assertTrue(a.olderThan(u));
        assertFalse(a.olderThan(n));
        assertTrue (b.olderThan(q));
        assertTrue(b.olderThan(w));
        assertTrue(b.olderThan(e));
        assertTrue(b.olderThan(r));
        assertTrue(b.olderThan(y));
        assertTrue(a.olderThan(u));
        assertFalse(a.olderThan(n));
    }
    
    @Test
    public void testSameAs()
    {
        //Create base instances of statistics
        StatsType t = StatsType.MINIMUM;
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 29, 17, 30, 05, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt,100, t);
        
        
        GregorianCalendar d = new GregorianCalendar(2018,9,29,17,30,05);
        Statistics b = new Statistics(20, "name", d, 100, t);
        
        //Create GregorianCalendar objects that are different in time, date;
        GregorianCalendar f = new GregorianCalendar(2019,9,29,17,30,05);
        GregorianCalendar g = new GregorianCalendar(2018,10,29,17,30,05);
        GregorianCalendar h = new GregorianCalendar(2018,9,30,17,30,05);
        GregorianCalendar j = new GregorianCalendar(2018,9,29,18,30,05);
        GregorianCalendar k = new GregorianCalendar(2018,9,29,17,31,05);
        GregorianCalendar l = new GregorianCalendar(2018,9,29,17,30,06);
        //And one that is the same.
        GregorianCalendar p = new GregorianCalendar(2018,9,29,17,30,05);
      //Same for ZonedDateTime objects
        ZonedDateTime q = ZonedDateTime.of(2019,9,29,17,30,05,0,zone);
        ZonedDateTime w = ZonedDateTime.of(2018,10,29,17,30,05,0,zone);
        ZonedDateTime e = ZonedDateTime.of(2018,9,30,17,30,05,0,zone);
        ZonedDateTime r = ZonedDateTime.of(2018,9,29,16,30,05,0,zone);
        ZonedDateTime y = ZonedDateTime.of(2018,9,29,17,29,05,0,zone);
        ZonedDateTime u = ZonedDateTime.of(2018,9,29,17,31,04,0,zone);
        ZonedDateTime n = ZonedDateTime.of(2018,9,29,17,30,05,0,zone);
        
        //Test
        assertFalse(a.sameAs(f));
        assertFalse(a.sameAs(g));
        assertFalse(a.sameAs(h));
        assertFalse(a.sameAs(j));
        assertFalse(a.sameAs(k));
        assertFalse(a.sameAs(l));
        assertTrue(a.sameAs(p));
        assertFalse (b.sameAs(f));
        assertFalse(b.sameAs(g));
        assertFalse(b.sameAs(h));
        assertFalse(b.sameAs(j));
        assertFalse(b.sameAs(k));
        assertFalse(b.sameAs(l));
        assertTrue(b.sameAs(p));
        //ZonedDateTime section
        assertFalse(a.sameAs(q));
        assertFalse(a.sameAs(w));
        assertFalse(a.sameAs(e));
        assertFalse(a.sameAs(r));
        assertFalse(a.sameAs(y));
        assertFalse(a.sameAs(u));
        assertTrue(a.sameAs(n));
        assertFalse (b.sameAs(q));
        assertFalse(b.sameAs(w));
        assertFalse(b.sameAs(e));
        assertFalse(b.sameAs(r));
        assertFalse(b.sameAs(y));
        assertFalse(b.sameAs(u));
        assertTrue(b.sameAs(n));
    }
    
    @Test
    public void testToString()
    {
        //Test in case AVERAGE
        StatsType ta = StatsType.AVERAGE; 
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018, 9, 30, 17, 30, 0, 0, zone);
       
        Statistics a = new Statistics(20, "name", zdt,100, ta);
        
        GregorianCalendar d = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics b = new Statistics(20, "name", d, 100, ta);
        
        assertEquals("Average %s = 20.0 %s at name", a.toString());
        assertEquals("Average %s = 20.0 %s at name", b.toString());
        
        //Test in case MAXIMU
        StatsType tx = StatsType.MAXIMU;
   
       
        Statistics ax = new Statistics(20, "name", zdt,100, tx);
        GregorianCalendar dx = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics bx = new Statistics(20, "name", dx, 100, tx);
        
        assertEquals("Maximum %s = 20.0 %s at name", ax.toString());
        assertEquals("Maximum %s = 20.0 %s at name", bx.toString());
        
        //Test in case MINIMUM
        StatsType tm = StatsType.MINIMUM;
       
        Statistics am = new Statistics(20, "name", zdt,100, tm);
        GregorianCalendar dm = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics bm = new Statistics(20, "name", dm, 100, tm);
        
        assertEquals("Minimum %s = 20.0 %s at name", am.toString());
        assertEquals("Minimum %s = 20.0 %s at name", bm.toString());
        
        //Test in case TOTAL
        StatsType tt = StatsType.TOTAL;
       
        Statistics at = new Statistics(20, "name", zdt,100, tt);
        GregorianCalendar dt = new GregorianCalendar(2018,9,30,17,30,00);
        Statistics bt = new Statistics(20, "name", dt, 100, tt);
        
        assertEquals("Total %s = 20.0 %s at name", at.toString());
        assertEquals("Total %s = 20.0 %s at name", bt.toString());
        
    }

}

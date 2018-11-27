import java.util.GregorianCalendar;
import java.time.ZonedDateTime;
/**
@author David Dowdell
@version 9/23/2018
Lab 11
This interface will compare dates and times
*/
public interface DateTimeComparable
{
    /** 
     * Tells whether a date/time is newer than another date/time
     * @param a date/time
     * @return true if it's newer, false if else
     */
    boolean newerThan(GregorianCalendar inDateTimeUTC);
    
    /** 
     * Tells whether a date/time is older than another date/time
     * @param a date/time
     * @return true if it's older, false if else
     */
    boolean olderThan(GregorianCalendar inDateTimeUTC);

    /** 
     * Tells whether a date/time is the same as another date/time
     * @param a date/time
     * @return true if it's the same, false if else
     */
    boolean sameAs(GregorianCalendar inDateTimeUTC);
    
    /** 
     * Tells whether a date/time is newer than another date/time
     * @param a date/time
     * @return true if it's newer, false if else
     */
    boolean newerThan(ZonedDateTime inDateTimeUTC);
    
    /** 
     * Tells whether a date/time is older than another date/time
     * @param a date/time
     * @return true if it's older, false if else
     */
    boolean olderThan(ZonedDateTime inDateTimeUTC);

    /** 
     * Tells whether a date/time is the same as another date/time
     * @param a date/time
     * @return true if it's the same, false if else
     */
    boolean sameAs(ZonedDateTime inDateTimeUTC);
}

import java.util.GregorianCalendar;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
@author David Dowdell
@version 9/23/2018
Lab 11
This will hold and find data about the time and date, and
number of stations.
*/
public class Statistics extends Observation implements DateTimeComparable
{
    /** The proper format of the date and time */
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";

    /** A formatter for formating the date/time as a string*/
    protected static DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /** The utc date and time */
    private GregorianCalendar utcDateTime;
    
    /** The utc date and time  as ZonedDateTime */
    private ZonedDateTime zdtDateTime;

    /** The number of reporting Mesonet stations */
    private int numberOfReportingStations;

    /** The type of statistic( average, minimum, maximu, or total) */
    private StatsType statType;

    /**
     *  Constructor for the Statistics class with a ZonedDateTime object
     *  @param The value
     *  @param the station id
     *  @param the date and time as ZonedDateTime
     *  @param number of valid stations
     *  @param the type of statistics
     *  @return instance of class Statistics
     *  */
    public Statistics(double value, String stid, ZonedDateTime zdt,
                        int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        
        zdtDateTime = zdt;
        String dateTimeStr = createStringFromDate(zdtDateTime);
        utcDateTime = createDateFromString(dateTimeStr);
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;

    }

    /**
     *  Constructor for the Statistics class with a GregorianCalendar
     *  @param The value
     *  @param the station id
     *  @param the date and time as GregorianCalendar
     *  @param number of valid stations
     *  @param the type of statistics
     *  @return instance of class Statistics
     *  */
    public Statistics(double value, String stid, GregorianCalendar dateTime,
            int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = dateTime;
        zdtDateTime = createZDateFromString(createStringFromDate(dateTime));
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;

    }

    /**
     * Converts String format "yyyy-MM-dd'T'HH:mm:ss z" to GregorianCalendar
     * @param String dateTimeStr
     * @return the GregorianCalendar
     */
    public static GregorianCalendar createDateFromString(String dateTimeStr)
    {
        //Split by each bit of punctuation and record parts
        String[] byDash = dateTimeStr.split("-");
        int year = Integer.parseInt(byDash[0]);
        int month = Integer.parseInt(byDash[1]);

        String[] byApostrophe = byDash[2].split("T");
        //Re-add since only the first T should cause a split
        for (int i = 2; i < byApostrophe.length; ++i)
        {
            byApostrophe[1] += "T" + byApostrophe[i];
        }
        int day = Integer.parseInt(byApostrophe[0]);

        String[] byColon = byApostrophe[1].split(":");
        int hour = Integer.parseInt(byColon[0]);
        int minute = Integer.parseInt(byColon[1]);
        String[] bySpace = byColon[2].split(" ");
        int second = Integer.parseInt(bySpace[0]);

        //Output as GregorianCalendar
        return new GregorianCalendar(year, month, day, hour, minute, second);

    }
    /**
     * Converts String format "yyyy-MM-dd'T'HH:mm:ss z" to ZonedDateTime
     * @param String dateTimeStr
     * @return the ZonedDateTime
     */
    public static ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        //Split by each bit of punctuation and record parts
        String[] byDash = dateTimeStr.split("-");
        int year = Integer.parseInt(byDash[0]);
        int month = Integer.parseInt(byDash[1]);

        String[] byApostrophe = byDash[2].split("T");
        //Re-add since only the first T should cause a split
        for (int i = 2; i < byApostrophe.length; ++i)
        {
            byApostrophe[1] += "T" + byApostrophe[i];
        }
        int day = Integer.parseInt(byApostrophe[0]);

        String[] byColon = byApostrophe[1].split(":");
        int hour = Integer.parseInt(byColon[0]);
        int minute = Integer.parseInt(byColon[1]);
        String[] bySpace = byColon[2].split(" ");
        int second = Integer.parseInt(bySpace[0]);
        ZoneId zId = ZoneId.of(bySpace[1]);

        //Output as GregorianCalendar
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, zId);
    }

    /**
     * Converts GregorianCalendar to String
     * @param GregorianCalendar
     * @return a String representing the date and time format "yyyy-MM-dd'T'HH:mm:ss z"
     */
    public static String createStringFromDate(GregorianCalendar calendar)
    {
        String frame = "%04d-%02d-%02dT%02d:%02d:%02d UTC";
        return String.format(frame, calendar.get(1),calendar.get(2),calendar.get(5),
                calendar.get(11), calendar.get(12), calendar.get(13));
    }
    /**
     * Converts ZonedDateTime to String
     * @param ZonedDateTime
     * @return a String representing the date and time format "yyyy-MM-dd'T'HH:mm:ss z"
     */
    public static String createStringFromDate(ZonedDateTime zdt)
    {
        return zdt.format(format);
    }

    /**
     * Return the number of reporting stations
     * @return the number of reporting stations
     */
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }

    /**
     * Return the UTC date/time as a string
     * @return the String
     */
    public String getUTCDateTimeString()
    {
        return createStringFromDate(utcDateTime);
    }

    /**
     * Tell if a time/date is newer
     * @return true if it's newer, false if otherwise
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        //Make 2 int arrays holding year, month, day, hour, minute second
        int[] now = {utcDateTime.get(1),utcDateTime.get(2),utcDateTime.get(5),
                utcDateTime.get(11),utcDateTime.get(12),utcDateTime.get(13)};
        int[] other = {inDateTime.get(1),inDateTime.get(2),inDateTime.get(5),
                inDateTime.get(11),inDateTime.get(12),inDateTime.get(13)};

        //Compare each piece of data
        if (now[0] > other[0])
        {
            return true;
        }
        if (now[1] > other[1])
        {
            return true;
        }
        if (now[2] > other[2])
        {
            return true;
        }
        if (now[3] > other[3])
        {
            return true;
        }
        if (now[4] > other[4])
        {
            return true;
        }
        //Last chance so return false otherwise
        return (now[5] > other[5]);
    }

    /**
     * Tell if a time/date is older
     * @return true if it's older, false if otherwise
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
      //Make 2 int arrays holding year, month, day, hour, minute second
        int[] now = {utcDateTime.get(1),utcDateTime.get(2),utcDateTime.get(5),
                utcDateTime.get(11),utcDateTime.get(12),utcDateTime.get(13)};
        int[] other = {inDateTime.get(1),inDateTime.get(2),inDateTime.get(5),
                inDateTime.get(11),inDateTime.get(12),inDateTime.get(13)};

        //Compare each piece of data
        if (now[0] < other[0])
        {
            return true;
        }
        if (now[1] < other[1])
        {
            return true;
        }
        if (now[2] < other[2])
        {
            return true;
        }
        if (now[3] < other[3])
        {
            return true;
        }
        if (now[4] < other[4])
        {
            return true;
        }
        //Last chance, so return false otherwise.
        return (now[5] < other[5]);
    }

    /**
     * Tell if a time/date is the same
     * @return true if it's the same, false if otherwise
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
      //Make 2 int arrays holding year, month, day, hour, minute second
        int[] now = {utcDateTime.get(1),utcDateTime.get(2),utcDateTime.get(5),
                utcDateTime.get(11),utcDateTime.get(12),utcDateTime.get(13)};
        int[] other = {inDateTime.get(1),inDateTime.get(2),inDateTime.get(5),
                inDateTime.get(11),inDateTime.get(12),inDateTime.get(13)};

        //Compare each piece of data
        if (now[0] != other[0])
        {
            return false;
        }
        if (now[1] != other[1])
        {
            return false;
        }
        if (now[2] != other[2])
        {
            return false;
        }
        if (now[3] != other[3])
        {
            return false;
        }
        if (now[4] != other[4])
        {
            return false;
        }
        //Last chance so return true otherwise
        return (now[5] == other[5]);
    }

    /**
     * Output statistics data as a String
     * Format like
     * Maximum Air Temperature[1.5m] = 36.5 C at HOOK
     * Minimum Air Temperature[1.5m] = 20.8 C at MIAM
     * Average Air Temperature[1.5m] = 32.4 C at Mesonet
     * Use %s to hold the place of the type of measurement
     * @return String
     */
    public String toString()
    {
        String val = String.format("%.1f",getValue());
        switch (statType)
        {
            case AVERAGE :
                return ("Average %s = " + val + " %s at " + getStid());
            case MAXIMU :
                return ("Maximum %s = " + val + " %s at " + getStid());
            case MINIMUM :
                return ("Minimum %s = " + val + " %s at " + getStid());
            case TOTAL :
                return ("Total %s = " + val + " %s at " + getStid());
            default :
             // If none of these return, there is some error.
                return "ERROR: incorrect statsType";
        }

    }

    @Override
    public boolean newerThan(ZonedDateTime inDateTimeUTC)
    {
        // Convert and then check
        GregorianCalendar gc = createDateFromString(createStringFromDate(inDateTimeUTC));
        return newerThan(gc);
    }

    @Override
    public boolean olderThan(ZonedDateTime inDateTimeUTC)
    {
        // Convert and then check
        GregorianCalendar gc = createDateFromString(createStringFromDate(inDateTimeUTC));
        return olderThan(gc);
    }

    @Override
    public boolean sameAs(ZonedDateTime inDateTimeUTC)
    {
        // Convert and then check
        GregorianCalendar gc = createDateFromString(createStringFromDate(inDateTimeUTC));
        return sameAs(gc);
    }
}
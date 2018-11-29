import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;


/**
@author David Dowdell
@version 9/19/2018
Lab 11
This program will take weather data input as a mdf file using the MapData class,
check and remove bad values using the Observation Class, and then format and output the data to the screen.
*/

public class Driver

{
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException
    {
        ZoneId zoneId = ZoneId.of("UTC");
        ZonedDateTime zdt = ZonedDateTime.of(2018,9,30,17,30,00, 0, zoneId);
        System.out.println(zdt.toString());
        
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z");
        
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z");

        String date = "2016-10-21T08:30:15 UTC";
        
        //convert String to LocalDate
       // ZonedDateTime zdt2 = ZonedDateTime.parse(date, f2);
        //System.out.println(zdt2.format(f2));
        
        // System.out.println(zdt.format(formatter));
        
        
        
        
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;
 
        final String directory = "data";
 
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
 
        mapData.parseFile();
        
        System.out.println(mapData.toString());
        
        MainFrame yep = new MainFrame();
    }
    
        
    
}
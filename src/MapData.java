import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.EnumMap;
import java.util.TreeMap;
/**
@author David Dowdell
@version 9/23/2018
Lab 11
This will hold measurements for all stations at a given time,
including temperature at 1.5m, 9m, and solar radiation.
*/
public class MapData
{
    /** Map to hold data for all stations */
    private HashMap<String, ArrayList<Observation>> dataCatalog;
    
    /** EnumMap of statistics to hold data */
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;
    
    /** TreeMap to hold the positions of the data */
    private TreeMap<String, Integer> paramPositions;   
    
    /** Number of missing Observations allowed for statistics to still be calculated and presented */
    private static final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    
    /** Number of Stations */
    private Integer numberOfStations = null;
    
    /** Label for temperature at 9m */
    private static final String TA9M = "TA9M";
    
    /** Label for temperature at 1.5m */
    private static final String TAIR = "TAIR";
    

    /** Label for solar radiation */
    private static final String SRAD = "SRAD";

    /** Label for windspeed */
    private static final String WSPD = "WSPD";
    
    /** Label for pressure */
    private static final String PRES = "PRES";
    
    /** Label for station ID */
    private static final String STID = "STID";
    
    /** The name "Mesonet" */
    private static final String MESONET = "Mesonet";
    
    /** The filename */
    private String fileName;
    
    /** The date and time */
    GregorianCalendar utcDateTime;
    
    /**
    Constructor for the MapData class. Takes arguments indicating the time of the measurements
    and a string of the location of the file holding the measurements.
    @param year Year the measurements were taken
    @param month Month the measurements were taken
    @param day Day the measurements were taken
    @param hour Hour the measurements were taken
    @param minute The minute the measurements were taken
    @param directory The directory of all the stations
    */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        
        utcDateTime = new GregorianCalendar(year,month,day,hour,minute);
        fileName = createFileName(year,month, day, hour, minute, directory);
 
    }
    public MapData(String filename, String directory)
    {
        fileName = directory + "//" + filename;
        int year = Integer.parseInt(filename.substring(0, 4));
        int month = Integer.parseInt(filename.substring(4,6));
        int day = Integer.parseInt(filename.substring(6,8));
        int hour = Integer.parseInt(filename.substring(8, 10));
        int minute = Integer.parseInt(filename.substring(10,12));
        
        utcDateTime = new GregorianCalendar(year,month,day,hour,minute);
    }
    /**
     * Creates a file name using the date with the format yyyyMMddHHmm.mdf
     * @return the file name
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        String frame = "%04d%02d%02d%02d%02d.mdf";
        
        fileName = String.format(frame, year, month, day, hour, minute);
        
        fileName = directory + "//" + fileName;
        return fileName;
    }
    
    /** 
     *  Read and parse a single string
     * @param the third line of the data file, as a string 
     */
    private void parseParamHeader(String inParamStr)
    {
        //Initialize
        paramPositions = new TreeMap<String, Integer>();
        //Split the header into parts
        String[] parts = inParamStr.split("\\s+");
        //Find locations of necessary data
        for (int i = 0; i < parts.length; ++i)
        {
            if (parts[i].equalsIgnoreCase(STID))
            {
                paramPositions.put(STID, 1);
            }
            if (parts[i].equalsIgnoreCase(TAIR))
            {
                paramPositions.put(TAIR, i);
            }
            if (parts[i].equalsIgnoreCase(TA9M))
            {
                paramPositions.put(TA9M,i);
            }

            if (parts[i].equalsIgnoreCase(SRAD))
            {
                paramPositions.put(SRAD, i);
            }
            if (parts[i].equalsIgnoreCase(WSPD))
            {
                paramPositions.put(WSPD, i);
            }
            if (parts[i].equalsIgnoreCase(PRES))
            {
                paramPositions.put(PRES, i);
            }
        }
    }
    
    /**
     * Parse a file, directory, to get data and store it
     * @throws IOException if some reading error
     */
    public void parseFile() throws IOException
    {
     // Attempt to read a mdf file, catch an error if something goes wrong:
    //    try
      //  {
            
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            //Skip first 2 lines
            br.readLine();
            br.readLine();
            // Find locations of data
            parseParamHeader(br.readLine());
            
            //Begin inputting data
            line = br.readLine();
            int counter = 0;
            String[] lineParts;
            ArrayList<Observation> sradList = new ArrayList<Observation>();
            ArrayList<Observation> tairList = new ArrayList<Observation>();
            ArrayList<Observation> ta9mList = new ArrayList<Observation>();
            ArrayList<Observation> wspdList = new ArrayList<Observation>();
            ArrayList<Observation> presList = new ArrayList<Observation>();
            int stP = paramPositions.get(STID);
            int srP = paramPositions.get(SRAD);
            int taP = paramPositions.get(TAIR);
            int t9P = paramPositions.get(TA9M);
            int wsP = paramPositions.get(WSPD);
            int prP = paramPositions.get(PRES);
            while (line != null)
            {
                
                lineParts =  line.split("\\s+");
               sradList.add(new Observation(Double.parseDouble(lineParts[srP]), lineParts[stP]));
               tairList.add(new Observation(Double.parseDouble(lineParts[taP]), lineParts[stP]));
               ta9mList.add(new Observation(Double.parseDouble(lineParts[t9P]), lineParts[stP]));
               wspdList.add(new Observation(Double.parseDouble(lineParts[wsP]), lineParts[stP]));
               presList.add(new Observation(Double.parseDouble(lineParts[prP]), lineParts[stP]));
               
                ++counter;
                
                line = br.readLine();
            }     
            numberOfStations = new Integer(counter); 
            br.close();
            //Copy arraylists into the desired storage, after initializing
            dataCatalog = new HashMap<String, ArrayList<Observation>>();
            
            dataCatalog.put(SRAD, sradList);
            dataCatalog.put(TAIR, tairList);
            dataCatalog.put(TA9M, ta9mList);
            dataCatalog.put(WSPD, wspdList);
            dataCatalog.put(PRES, presList);
               
           
    }
    
    /**
     * Calculates max, min, average, and total for a bunch of observations
     * @param ArrayList of Observation, and a String paramid
     */
    
    private void calculateStatistics(ArrayList<Observation> inData, String paramid)
    {
        //Add valid values, find max, min, count all invalid data.
        int numInvalid = 0;
        int numValid = 0;
        double max = -9999;
        int maxObs = -1;
        double min = 999999;
        int minObs = -1;
        double total = 0;
        for (int i = 0; i < inData.size(); ++i)
        {
            //Discount an observation if invalid
            if (!inData.get(i).isValid())
            {
                ++numInvalid;
            }
            //Count all valid observations
            else
            {
                ++numValid;
                total += inData.get(i).getValue();
                if (inData.get(i).getValue() < min)
                {
                    min = inData.get(i).getValue();
                    minObs = i;
                }
                if (inData.get(i).getValue() > max)
                {
                    max = inData.get(i).getValue();
                    maxObs = i;
                }
            }
            numberOfStations = numInvalid;
        }
        // Record findings by making Statistics objects,
        //and putting them into maps
        TreeMap<String, Statistics> tm = statistics.get(StatsType.MINIMUM);
        TreeMap<String, Statistics> mm = statistics.get(StatsType.MAXIMU);
        TreeMap<String, Statistics> am = statistics.get(StatsType.AVERAGE);
        
        tm.put(paramid, new Statistics(min, inData.get(minObs).getStid(), 
                    utcDateTime, numValid, StatsType.MINIMUM));
        statistics.put(StatsType.MINIMUM, tm);
        
        mm.put(paramid, new Statistics(max, inData.get(maxObs).getStid(), 
                    utcDateTime, numValid, StatsType.MAXIMU));
        statistics.put(StatsType.MAXIMU, mm);
        
        am.put(paramid,  new Statistics((total / numValid), MESONET, 
                    utcDateTime, numValid, StatsType.AVERAGE));
        statistics.put(StatsType.AVERAGE, am);
            
    }
    
    /**
     * Calculates all of the statistics by calling calculateStatistics multiple times.
     */
    private void calculateAllStatistics()
    {
        //First initialize statistics to be filled in.
        statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);
        statistics.put(StatsType.MAXIMU, new TreeMap<String,Statistics>());
        statistics.put(StatsType.MINIMUM, new TreeMap<String, Statistics>());
        statistics.put(StatsType.AVERAGE,  new TreeMap<String, Statistics>());
        //Then calculate by paramid
        calculateStatistics(dataCatalog.get(SRAD), SRAD);
        calculateStatistics(dataCatalog.get(TA9M), TA9M);
        calculateStatistics(dataCatalog.get(TAIR), TAIR);
        calculateStatistics(dataCatalog.get(WSPD), WSPD);
        calculateStatistics(dataCatalog.get(PRES), PRES);
        
    }
   
    
    /** 
     * If there are too many missing observations, output error message
     * Otherwise:
     * Format and output the data as a String in the specific format, example:
     * ========================================================
     * === 2018-08-30 17:45 ===
     * ========================================================
     * Maximum Air Temperature[1.5m] = 36.5 C at HOOK
     * Minimum Air Temperature[1.5m] = 20.8 C at MIAM
     * Average Air Temperature[1.5m] = 32.4 C at Mesonet
     * ========================================================
     * ========================================================
     * Maximum Air Temperature[9.0m] = 34.9 C at HOOK
     * Minimum Air Temperature[9.0m] = 20.7 C at MIAM
     * Average Air Temperature[9.0m] = 31.6 C at Mesonet
     * ========================================================
     * ========================================================
     * Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP
     * Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM
     * Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet
     * ========================================================
     * @return formatted data as a String
     */
    public String toString()
    {
        //First, fill in notable statistics
        calculateAllStatistics();
        
        if (numberOfStations > NUMBER_OF_MISSING_OBSERVATIONS)
        {
            return "ERROR: Too many missing observations.";
        }
        
        
        //Then output the string.
        String out = "========================================================";
     
       out += "\r\n";
       String frame = ("=== %04d-%02d-%02d %02d:%02d ===");
       out += String.format(frame, utcDateTime.get(1), utcDateTime.get(2), 
               utcDateTime.get(5), utcDateTime.get(11), utcDateTime.get(12));
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MAXIMU).get(TAIR).toString(),
               "Air Temperature[1.5m]", "C");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MINIMUM).get(TAIR).toString(),
                "Air Temperature[1.5m]", "C");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.AVERAGE).get(TAIR).toString(),
                "Air Temperature[1.5m]", "C");
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MAXIMU).get(TA9M).toString(),
                "Air Temperature[9.0m]", "C");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MINIMUM).get(TA9M).toString(),
                "Air Temperature[9.0m]", "C");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.AVERAGE).get(TA9M).toString(),
                "Air Temperature[9.0m]", "C");
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MAXIMU).get(SRAD).toString(),
                "Solar Radiation[1.5m]", "W/m^2");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.MINIMUM).get(SRAD).toString(),
                "Solar Radiation[1.5m]", "W/m^2");
       out += "\r\n";
       out += String.format(statistics.get(StatsType.AVERAGE).get(SRAD).toString(),
                "Solar Radiation[1.5m]", "W/m^2");
       out += "\r\n";
       for (int k = 0; k < 56; ++k)
       {
           out += "=";
       }
       //out += "\r\n";
       return out;
    }
    /**
     * This method returns an object array for the Maximum for the JTable in order:
     * STID, parameter, statistic, value, #ofReportingStations, date
     * @param stat
     * @return
     */
    public Object[] getMax(String stat) //stat will be TAIR, WSPD, SRAD, etc.
    {
        Object[] a = {statistics.get(StatsType.MAXIMU).get(stat).getStid(),
                stat, "MAXIMUM", statistics.get(StatsType.MAXIMU).get(stat).getValue(),
                statistics.get(StatsType.MAXIMU).get(stat).getNumberOfReportingStations(),
                statistics.get(StatsType.MAXIMU).get(stat).getUTCDateTimeString()};
        return a;
               
    }
    
    /**
     * This method returns an object array for the MInimum for the JTable in order:
     * STID, parameter, statistic, value, #ofReportingStations, date
     * @param stat
     * @return
     */
    public Object[] getMin(String stat) //stat will be TAIR, WSPD, SRAD, etc.
    {
        Object[] a = {statistics.get(StatsType.MINIMUM).get(stat).getStid(),
                stat, "MINIMUM", statistics.get(StatsType.MINIMUM).get(stat).getValue(),
                statistics.get(StatsType.MINIMUM).get(stat).getNumberOfReportingStations(),
                statistics.get(StatsType.MINIMUM).get(stat).getUTCDateTimeString()};
        
        return a;
               
    }
    
    /**
     * This method returns an object array for the Average for the JTable in order:
     * STID, parameter, statistic, value, #ofReportingStations, date
     * @param stat
     * @return
     */
    public Object[] getAvg(String stat) //stat will be TAIR, WSPD, SRAD, etc.
    {
        Object[] a = {statistics.get(StatsType.AVERAGE).get(stat).getStid(),
                stat, "AVERAGE", statistics.get(StatsType.AVERAGE).get(stat).getValue(),
                statistics.get(StatsType.AVERAGE).get(stat).getNumberOfReportingStations(),
                statistics.get(StatsType.AVERAGE).get(stat).getUTCDateTimeString()};
        return a;
               
    }
    
    
 
}

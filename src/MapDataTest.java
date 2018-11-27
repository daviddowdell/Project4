import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
/**
@author David Dowdell
@version 10/25/2018
Lab 11
This will test the MapData class
*/
public class MapDataTest
{

    @Before
    public void setUp() throws Exception
    {
        //This should be empty.
    }

   /**
    * Test for createFileName
    */

    @Test
    public void testCreateFileName()
    {
        MapData test1 = new MapData(1,1,1,1,1,"name");
        
        assertEquals("name//000101010101.mdf", test1.createFileName(1,1,1,1,1,"name"));
    }
    /**
     * Really the same test as testToString, but tests everything else too.
     * @throws IOException
     */
    @Test
    public void testParseFile() throws IOException
    {
        MapData test1 = new MapData(2018,8,30,17,45,"data");
        test1.parseFile();     
        test1.toString();
        assertEquals("========================================================\r\n" + 
                "=== 2018-08-30 17:45 ===\r\n" + 
                "========================================================\r\n" + 
                "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\r\n" + 
                "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\r\n" + 
                "Average Air Temperature[1.5m] = 32.4 C at Mesonet\r\n" + 
                "========================================================\r\n" + 
                "========================================================\r\n" + 
                "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\r\n" + 
                "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\r\n" + 
                "Average Air Temperature[9.0m] = 31.6 C at Mesonet\r\n" + 
                "========================================================\r\n" + 
                "========================================================\r\n" + 
                "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\r\n" + 
                "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\r\n" + 
                "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\r\n" + 
                "========================================================", test1.toString());
        
        MapData test2 = new MapData(2018,8,30,17,49,"data");
        test2.parseFile();
       
        assertEquals("ERROR: Too many missing observations.", test2.toString());
    }

   /**
    * Tests the toString method 
    * @throws IOException
    */

    @Test
    public void testToString() throws IOException
    {
        MapData test1 = new MapData(2018,8,30,17,45,"data");
        test1.parseFile();       
        test1.toString();
       
        assertEquals("========================================================\r\n" + 
                "=== 2018-08-30 17:45 ===\r\n" + 
                "========================================================\r\n" + 
                "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\r\n" + 
                "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\r\n" + 
                "Average Air Temperature[1.5m] = 32.4 C at Mesonet\r\n" + 
                "========================================================\r\n" + 
                "========================================================\r\n" + 
                "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\r\n" + 
                "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\r\n" + 
                "Average Air Temperature[9.0m] = 31.6 C at Mesonet\r\n" + 
                "========================================================\r\n" + 
                "========================================================\r\n" + 
                "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\r\n" + 
                "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\r\n" + 
                "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\r\n" + 
                "========================================================", test1.toString());
        
        MapData test2 = new MapData(2018,8,30,17,49,"data");
        test2.parseFile();
       
        assertEquals("ERROR: Too many missing observations.", test2.toString());
        
    }

}

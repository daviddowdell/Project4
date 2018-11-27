import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObservationTest
{

    @Before
    public void setUp() throws Exception
    {
        //This is meant to be empty.
    }

    @Test
    public void testObservation()
    {
      
        Observation testObs = new Observation(1.0, "name");
        
        assertEquals(1.0, testObs.getValue(), 0.01);
        assertEquals("name", testObs.getStid());
        
      
    }

    @Test
    public void testGetValue()
    {
        Observation testObs = new Observation(1.0, "name");
        
        assertEquals(1.0, testObs.getValue(), 0.01);
        
    }

    @Test
    public void testIsValid()
    {
        Observation testObs = new Observation(1.0, "name");
        assertTrue(testObs.isValid());
        
        testObs = new Observation(-300.0, "name");
        assertFalse(testObs.isValid());
        

    }

    @Test
    public void testGetStid()
    {
        Observation testObs = new Observation(1.0, "name");
        
        assertEquals("name", testObs.getStid());
       
    }

    @Test
    public void testToString()
    {
        Observation testObs = new Observation(1.0, "name");
        String tString = testObs.toString();
        assertEquals("1.0 C at name", tString);
        
        testObs = new Observation(-300.0, "name");
        tString = testObs.toString();
        assertEquals("ERROR: measurement is invalid", tString);
    
    }

}

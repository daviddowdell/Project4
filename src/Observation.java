/**
@author David Dowdell
@version 9/3/2018
Lab 11
This class is meant to take in a single measurement and alert when the measurement is bad. 
*/ 
public class Observation extends AbstractObservation
{
    /** The value to be checked */
    private double value;

    /** The station identification */
    private String stid;
    
    /**
     * Constructor for the observation class, with the measurement and its location.
     * @param value The measurement to be checked
     * @param stid The station at which the measurement was taken
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
    }
    
    /**
     * Getter for value
     * @return value
     */
    public double getValue()
    {
        return value;
    }
    
    /**
     * Tells whether the measurement is valid
     * @return true if it's valid and false if not
     */
    public boolean isValid()
    {
        return (value > -274.0);
       
    }
    
    /**
     * Getter for stid
     * @return stid
     */
    public String getStid()
    {
        return stid;
    }
    
    /**
     * Create a string with the data formatted as XX.X C at XXXX (using value and stid)
     * Unless isValid()returns false, then display an error message.
     * Note: do not use this method when trying to display solar radiation data.
     * @return output string
     */
    public String toString()
    {
        if (!isValid())
        {
            return ("ERROR: measurement is invalid");
        }
        else
        {
            String out = String.format("%.1f C at %s", value, stid);
            return out;
        }
    }
}


/**
@author David Dowdell
@version 9/23/2018
Lab 11
This will be an abstract class that Observation will implement
to tell whether a measurement is valid
*/

public abstract class AbstractObservation
{
    /** Tells whether is valid */
    
    
    /**
     * Constructor for AbsractObservation
     * @return nothing because an abstract class can't be implemented
     */
     public AbstractObservation()
     {
         //Nothing is needed in the constructor here.
     }
     
     /**
      * Tells whether valid is true or not, when implemented.
      * @return the truthfulness of valid
      */
     public abstract boolean isValid();
     
}

// Name: Ekra
// Student ID: 501237891
/*
 * 
 * This class simulates an ride service for a simple Uber app
 * 
 * A TMUberRide is-a TMUberService with some extra functionality
 */
public class TMUberRide extends TMUberService
{
  private int numPassengers;
  private boolean requestedXL;
  
  public static final String TYPENAME = "RIDE";
  
  // Constructor to initialize all inherited and new instance variables 
  public TMUberRide(Driver driver, String from, String to, User user, int distance, double cost, int numPassengers, boolean requestedXL)
  {
    // Fill in the code - make use of the super method

    // Start code
    super(driver, from, to, user, distance, cost, TYPENAME); // inhert variables
    this.numPassengers = numPassengers; // has no effect as of now
    this.requestedXL = requestedXL; // has no effect as of now
    // End code
  }
  
  public String getServiceType()
  {
    return TYPENAME;
  }

  public int getNumPassengers()
  {
    return numPassengers;
  }

  public void setNumPassengers(int numPassengers)
  {
    this.numPassengers = numPassengers;
  }

  public boolean isRequestedXL()
  {
    return requestedXL;
  }

  public void setRequestedXL(boolean requestedXL)
  {
    this.requestedXL = requestedXL;
  }
}

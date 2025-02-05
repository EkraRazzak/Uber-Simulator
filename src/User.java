// Name: Ekra
// Student ID: 501237891
/*
 * 
 * Class that simulates a user of a simple Uber app
 * 
 * Everything has been done for you except equals() method
 */
public class User 
{
  private String accountId;  
  private String name;
  private String address;
  private double wallet; // load up with money
  private int rides;
  private int deliveries;
  
  public User(String id, String name, String address, double wallet)
  {
    this.accountId = id;
    this.name = name;
    this.address = address;
    this.wallet = wallet;
    this.rides = 0;
    this.deliveries = 0;
  } 

  // Getters and Setters
  public String getAccountId()
  {
    return accountId;
  }
  public void setAccountId(String accountId)
  {
    this.accountId = accountId;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getAddress()
  {
    return address;
  }
  public void setAddress(String address)
  {
    this.address = address;
  }
  public double getWallet()
  {
    return wallet;
  }
  public void setWallet(double wallet) // Temporary change to double (originally int wallet)
  {
    this.wallet = wallet;
  }
  public int getRides()
  {
    return rides;
  }
  public void addRide()
  {
    this.rides++;
  }
  public void addDelivery()
  {
    this.deliveries++;
  }
  public int getDeliveries()
  {
    return deliveries;
  }
  
  // Pay for the cost of the service
  // This method assumes that there are sufficient funds in the wallet
  public void payForService(double cost)
  {
    wallet -= cost;
  }
  // Print Information about a User  
  public void printInfo()
  {
    System.out.printf("Id: %-5s Name: %-15s Address: %-15s Wallet: %2.2f", accountId, name, address, wallet);
  }
  
  /*
   * Two users are equal if they have the same name and address.
   * This method is overriding the inherited method in superclass Object
   * 
   * Fill in the code
   */
  public boolean equals(Object other)
  {
    // Start code
    if (this == other) // check if this object equals other object
    {
      return true;
    }

    if (!(other instanceof User)) // check if other is an instance of user
    {
        return false;
    }

    User otherUser = (User) other;
    if (this.name.equals(otherUser.getName()) && this.address.equals(otherUser.getAddress())) // check if this object attributes equal to other object attributes
    {
      return true;
    }
    
    return false;
    // End code
  }
  // Decrement ride/delivery methods (These are meant to be used in TMUberRide.java and TMUberDelivery.java respectively)
  public void decrementRides()  // Start code
  {
    this.rides--;
  }

  public void decrementDeliveries() 
  {
    this.deliveries--;          // End code
  }
  
}

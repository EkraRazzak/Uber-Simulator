// Name: Ekra
// Student ID: 501237891
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  private ArrayList<User>   users;
  private ArrayList<Driver> drivers;

  private ArrayList<TMUberService> serviceRequests; 

  public double totalRevenue; // Total revenues accumulated via rides and deliveries

  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;

  public TMUberSystemManager()
  {
    users   = new ArrayList<User>();
    drivers = new ArrayList<Driver>();
    serviceRequests = new ArrayList<TMUberService>(); 
    
    TMUberRegistered.loadPreregisteredUsers(users);
    TMUberRegistered.loadPreregisteredDrivers(drivers);
    
    totalRevenue = 0;
  }

  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  String errMsg = null;

  public String getErrorMessage()
  {
    return errMsg;
  }
  
  // Given user account id, find user in list of users
  // Return null if not found
  public User getUser(String accountId)
  {
    // Fill in the code

    // Start code
    for (User user : users)
    {
        if (user.getAccountId().equals(accountId)) // Checking if account id is found
        {
            return user; // returning user once found
        }
    }
    return null;
    // End code
  }
  
  // Check for duplicate user
  private boolean userExists(User user)
  {
    // Fill in the code
    // Start code
    for (User userCheck : users)
    {
      if (userCheck.equals(user)) // comparing user and the userCheck parameter
      {
        return true;
      }
    }
    // End code
    return false;
  }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
   // Fill in the code
   // Start code
   for (Driver driverCheck : drivers)
    {
      if (driverCheck.equals(driver)) // comparing driver and the driverCheck parameter
      {
        return true;
      }
    }
   // End code
   return false;
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests

  private boolean existingRequest(TMUberService req)
  {
    // Fill in the code
    // Start code
    for (TMUberService serviceReq : serviceRequests)
    {
      if (serviceReq.equals(req)) // checking if such a request exists
      {
        return true;
      }
    }
    // End code
    return false;
  }

  // Calculate the cost of a ride or of a delivery based on distance 

  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE; 
  }


  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }

  // Go through all drivers and see if one is available
  // Choose the first available driver
  // Return null if no available driver
  private Driver getAvailableDriver()
  {
    // Fill in the code
    // Start code
    for (Driver driver : drivers)
    {
        if (driver.getStatus() == Driver.Status.AVAILABLE) // check if friver status is available
        {
            return driver; // return driver since available
        }
    }

    return null; // if not found, return null
    // End code
  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers()
  {
    System.out.println();
    
    for (int i = 0; i < users.size(); i++) // iterate over users
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index); // print index followed by a period and space
      users.get(i).printInfo(); // print user info 
      System.out.println(); 
    }
  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    // Fill in the code
    // Start code
    System.out.println();
    
    for (int i = 0; i < drivers.size(); i++) // iterate over drivers
    {
        int index = i + 1;
        System.out.printf("%-2s. ", index); // print index followed by a period and space
        drivers.get(i).printInfo(); // print user info
        System.out.println(); 
    }
    // End code
  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    // Fill in the code
    // Start code
    System.out.println();
    
    for (int i = 0; i < serviceRequests.size(); i++) // iterate over service requests
    {
        int index = i + 1;
        System.out.printf("%-2s. ", index); // print index followed by a period and space
        serviceRequests.get(i).printInfo(); // print user info
        System.out.println(); 
    }
    // End code
  }

  // Helper method for registerNewUser because new user also needs an accountId, cannot be blank (ADDED BY ME)
  public static String generateUserAccountId(ArrayList<User> current)
  {
      int highestId = 0; // initialize
      for (User user : current) 
      {
          int id = Integer.parseInt(user.getAccountId().substring(3)); // extract numeric part of account id and convert to int
          if (id > highestId) // update id to make sure its highest
          {
              highestId = id;
          }
      }
      return "900" + (highestId + 1); // generate new account id
  }  

  // Add a new user to the system
  public boolean registerNewUser(String name, String address, double wallet)
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible errors that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!

    // Start code
    if (name == null || name.isEmpty()) // check for empty name, return errMsg
    {
        errMsg = "Invalid User Name";
        return false;
    }

    if (address == null || address.isEmpty()) // check for empty address, return errMsg
    {
        errMsg = "Invalid User Address";
        return false;
    }

    if (wallet < 0) // check for invalid wallet amount, return errMsg
    {
        errMsg = "Invalid Money in Wallet";
        return false;
    }

    User newUser = new User(TMUberRegistered.generateUserAccountId(users), name, address, wallet); // making a new user object

    if (userExists(newUser)) // check if user already exists, return errMsg
    {
        errMsg = "User Already Exists in System";
        return false;
    }

    users.add(newUser); // add new user if it passed all conditions
    return true;

    // End code
  }

  // Helper method for registerNewDriver because new driver also needs an accountId, cannot be blank (ADDED BY ME)
  public static String generateDriverId(ArrayList<Driver> current)
  {
    int highestId = 0; // initialize
    for (Driver driver : current) 
    {
        int id = Integer.parseInt(driver.getId().substring(3)); // extract numeric part of driver id and convert to int
        if (id > highestId) // update driver id to make sure its highest
        {
            highestId = id;
        }
    }
    return "700" + (highestId + 1); // generate new account id
  }

  // Add a new driver to the system
  public boolean registerNewDriver(String name, String carModel, String carLicensePlate) 
  {
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser

    // Start code
    if (name == null || name.isEmpty()) // check for empty name, return errMsg
    {
        errMsg = "Invalid Driver Name";
        return false;
    }

    if (carModel == null || carModel.isEmpty()) // check for empty car model, return errMsg
    {
        errMsg = "Invalid Car Model";
        return false;
    }

    if (carLicensePlate == null || carLicensePlate.isEmpty()) // check for empty license plate, return errMsg
    {
        errMsg = "Invalid Car Licence Plate";
        return false;
    }

    Driver newDriver = new Driver(TMUberRegistered.generateDriverId(drivers), name, carModel, carLicensePlate);
    if (driverExists(newDriver)) // check for pre existing driver, return errMsg
    {
        errMsg = "Driver Already Exists in System";
        return false;
    }

    drivers.add(newDriver); // add driver once it passed conditions
    return true;
    // End code
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public boolean requestRide(String accountId, String from, String to)
  {
    // Check for valid parameters
  	// Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user

    // Start code

    // check for valid parameters
    if (accountId == null || accountId.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty())
    {
      errMsg = "Invalid parameters";
      return false;
    }

    // use the account id to find the user object in the list of users
    User user = getUser(accountId);
    if (user == null)
    {
      errMsg = "User account not found";
      return false;
    }

    // get the distance for this ride (Note: must be > 1 city block or else we get errMsg)
    int distance = CityMap.getDistance(from, to);
    if (distance <= 1)
    {
      errMsg = "Insufficient travel distance";
      return false;
    }

    // find an available driver
    Driver driver = getAvailableDriver();
    if (driver == null)
    {
      errMsg = "No drivers available";
      return false;
    }

    // check if there is an existing ride request for this user (only one ride request per user at a time)
    TMUberRide newRide = new TMUberRide(driver, from, to, user, distance, 0, 0, false);
    if (existingRequest(newRide))
    {
        errMsg = "User already has ride request";
        return false;
    }

    // change the driver status to reflect that they are now on a ride
    driver.setStatus(Driver.Status.DRIVING);

     // Calculate the cost of the ride based on distance
    double rideCost = getRideCost(distance);

    // check if the user has enough money in their wallet (if not we get errMsg)
    if (user.getWallet() < rideCost)
    {
      errMsg = "Insufficient funds";
      return false;
    }

    // deduct the cost from the user's wallet
    user.setWallet(user.getWallet() - rideCost);

    // create the TMUberRide object with the calculated cost
    TMUberRide ride = new TMUberRide(driver, from, to, user, distance, rideCost, 0, false);

    // set the cost of the ride
    ride.setCost(rideCost);

    // add the ride request to the list of requests
    serviceRequests.add(ride);

    // increment the number of rides for this user
    user.addRide();

    return true;

    // End code
  }

  // Request a food delivery. User wallet will be reduced when drop off happens
  public boolean requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId)
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had

    // Start code
    
    // check for required user information
    if (accountId == null || accountId.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty() || restaurant == null || restaurant.isEmpty() || foodOrderId == null || foodOrderId.isEmpty())
    {
      errMsg = "Important user information is missing";
      return false;
    }

    // use the account id to find the user object in the list of users
    User user = getUser(accountId);
    if (user == null)
    {
      errMsg = "User account not found";
      return false;
    }

    // get the distance for this delivery (must be > 1 city block or else we get errMsg)
    int distance = CityMap.getDistance(from, to);
    if (distance <= 1)
    {
      errMsg = "Insufficient travel distance";
      return false;
    }

    // find an available driver (if not return errMsg)
    Driver driver = getAvailableDriver();
    if (driver == null)
    {
      errMsg = "No drivers available";
      return false;
    }

    // check if there is an existing delivery request for this user, restaurant, and food order id
    TMUberDelivery newDelivery = new TMUberDelivery(driver, from, to, user, distance, 0, restaurant, foodOrderId);
    if (existingRequest(newDelivery))
    {
        errMsg = "User already has a delivery request for this restaurant and food order";
        return false;
    }

    // change the driver status to reflect that they are now on a delivery
    driver.setStatus(Driver.Status.DRIVING);

    // calculate the cost of the delivery based on distance
    double deliveryCost = getDeliveryCost(distance);

    // check if the user has enough money in their wallet
    if (user.getWallet() < deliveryCost)
    {
      errMsg = "Insufficient funds";
      return false;
    }

    // deduct the cost from the user's wallet
    user.setWallet(user.getWallet() - deliveryCost);

    // create the TMUberDelivery object with the calculated cost
    TMUberDelivery delivery = new TMUberDelivery(driver, from, to, user, distance, deliveryCost, restaurant, foodOrderId);

    // set the cost of the delivery
    delivery.setCost(deliveryCost);

    // add the delivery request to the list of service requests
    serviceRequests.add(delivery);

    // increment the number of deliveries for this user
    user.addDelivery();

    return true;

    // End code
  }


  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public boolean cancelServiceRequest(int request)
  {
    // Check if valid request #
    // Remove request from list
    // Also decrement number of rides or number of deliveries for this user
    // since this ride/delivery wasn't completed

    // Start code

    // check if valid request #
    if (request < 0 || request >= serviceRequests.size())
    {
      errMsg = "Invalid request #";
      return false;
    }

    // get the service request at the specified index
    TMUberService service = serviceRequests.get(request);

    // remove request from list
    serviceRequests.remove(request);

    // decrement the number of rides or number of deliveries for this user
    if (service instanceof TMUberRide)
    {
      User user = service.getUser();
      user.decrementRides(); // calling decrement method from user

    }
    else if (service instanceof TMUberDelivery)
    {
      User user = service.getUser();
      user.decrementDeliveries(); // calling decrement method from user
    }

    return true;

    // End code
  }
  
  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public boolean dropOff(int request)
  {
    // See above method for guidance
    // Get the cost for the service and add to total revenues
    // Pay the driver
    // Deduct driver fee from total revenues
    // Change driver status
    // Deduct cost of service from user

    // Start code


    // check if valid request #
    if (request < 0 || request >= serviceRequests.size())
    {
      errMsg = "Invalid request";
      return false;
    }

    // get the service request at the specified index
    TMUberService service = serviceRequests.get(request);

    // get the cost for the service and add to total revenues
    double cost = service.getCost();
    totalRevenue += cost;

    // pay the driver
    Driver driver = service.getDriver();
    double driverFee = cost * PAYRATE;
    driver.pay(driverFee);

    // deduct driver fee from total revenues
    totalRevenue -= driverFee;

    // change driver status
    driver.setStatus(Driver.Status.AVAILABLE);

    // deduct cost of service from user
    User user = service.getUser();
    user.setWallet(user.getWallet() - cost);

    // remove request from list
    serviceRequests.remove(request);

    return true;
    // End code
  }

  // Sort users by name
  // Then list all users
  public void sortByUserName()
  {
    // Start code
    Collections.sort(users, new NameComparator());

    listAllUsers();
    // End code
  }

  // Helper class for method sortByUserName (ADDED BY ME, tweaked original code)
  private class NameComparator implements Comparator<User> // implements the comparator interface
  {
    // Start code
    @Override
    public int compare(User u1, User u2) 
    {
      return u1.getName().compareTo(u2.getName());
    }
    // End code
  }

  // Sort users by number amount in wallet
  // Then ist all users
  public void sortByWallet()
  {
    // Start code
    Collections.sort(users, new UserWalletComparator());

    listAllUsers();
    // End code
  }
  // Helper class for use by sortByWallet
  private class UserWalletComparator implements Comparator<User>
  {
    // Start code
    @Override
    public int compare(User u1, User u2) 
    {
      return Double.compare(u1.getWallet(), u2.getWallet());
    }
    // End code
  }

  // Sort trips (rides or deliveries) by distance
  // Then list all current service requests
  public void sortByDistance()
  {
    // Start code
    Collections.sort(serviceRequests, new DistanceComparator());

    listAllServiceRequests();
    // End code
  }

  // Helper class for use by sortByDistance (ADDED BY ME) 
  private class DistanceComparator implements Comparator<TMUberService>
  {
      @Override
      public int compare(TMUberService service1, TMUberService service2) 
      {
          return service1.compareTo(service2);
      }
  }

}
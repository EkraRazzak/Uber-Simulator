// Name: Ekra
// Student ID: 501237891
import java.util.Arrays;
import java.util.Scanner;

// EXPLANATION OF CITY STRUCTURE WHEN PROMPTED FOR ADDRESS BELOW:

// The city consists of a grid of 9 X 9 City Blocks

// Streets are east-west (1st street to 9th street)
// Avenues are north-south (1st avenue to 9th avenue)

// Example 1 of Interpreting an address:  "34 4th Street"
// A valid address *always* has 3 parts.
// Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
// Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
// Part 3: Must be "Street" or "Avenue" (case insensitive)

// Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
// For distance calculation you need to identify the the specific city block - in this example 
// it is city block (3, 4) (3rd avenue and 4th street)

// Example 2 of Interpreting an address:  "51 7th Avenue"
// Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
// For distance calculation you need to identify the the specific city block - 
// in this example it is city block (7, 5) (7th avenue and 5th street)
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{
  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s)
  {
    for (int i = 0; i < s.length(); i++)
      if (!Character.isDigit(s.charAt(i)))
        return false;
    return  true;
  }

  // Get all parts of address string
  // An easier solution would use String method split()
  // Other solutions are possible - you may replace this code if you wish
  private static String[] getParts(String address)
  {
    String parts[] = new String[3];
    
    if (address == null || address.length() == 0)
    {
      parts = new String[0];
      return parts;
    }
    int numParts = 0;
    try (Scanner sc = new Scanner(address)) {
      while (sc.hasNext())
      {
        if (numParts >= 3)
          parts = Arrays.copyOf(parts, parts.length+1);

        parts[numParts] = sc.next();
        numParts++;
      }
    }
    if (numParts == 1)
      parts = Arrays.copyOf(parts, 1);
    else if (numParts == 2)
      parts = Arrays.copyOf(parts, 2);
    return parts;
  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    // Fill in the code
    // Make use of the helper methods above if you wish
    // There are quite a few error conditions to check for 
    // e.g. number of parts != 3

    // Start code
    String[] parts = getParts(address);
    if (parts.length != 3) {
      return false;
    }
  
    // Check if the first part is all digits
    if (!allDigits(parts[0])) 
    {
      return false;
    }
  
    // Check if the second part is a valid ordinal
    String ordinal = parts[1].toLowerCase();
    if (!(ordinal.equals("1st") || ordinal.equals("2nd") || ordinal.equals("3rd") || ordinal.matches("\\dth"))) 
    {
      return false;
    }
  
    // Check if the third part is either "Street" or "Avenue" (case insensitive)
    String streetType = parts[2].toLowerCase();

    if (streetType.equals("street") || streetType.equals("avenue"))
    {
      return true;
    }
    return false;
    // End code
  }

  // Computes the city block coordinates from an address string
  // returns an int array of size 2. e.g. [3, 4] 
  // where 3 is the avenue and 4 the street
  // See comments at the top for a more detailed explanation
  public static int[] getCityBlock(String address)
  {
    // int[] block = {-1, -1};

    // Fill in the code
    // Start code
    int[] block = {-1, -1};

    if (!validAddress(address)) // If statement checks if the address is valid
    {
      System.out.println("Invalid address format.");
      return block;
    }
  
    String[] parts = getParts(address);
  
    if (parts[0].length() == 2 && allDigits(parts[0])) // Check which avenue it is
    {
      block[0] = Character.getNumericValue(parts[0].charAt(0));
    }
    if (parts.length == 3 && (parts[2].equalsIgnoreCase("street") || parts[2].equalsIgnoreCase("avenue"))) // Check which street it is
    {
      block[1] = Integer.parseInt(parts[1]);
    }
  
    return block;
    // End code
  }
  
  // Calculates the distance in city blocks between the 'from' address and 'to' address
  // Hint: be careful not to generate negative distances
  
  // This skeleton version generates a random distance
  // If you do not want to attempt this method, you may use this default code
  public static int getDistance(String from, String to)
  {
    // Fill in the code or use this default code below. If you use
    // the default code then you are not eligible for any marks for this part
    
    // Math.random() generates random number from 0.0 to 0.999
    // Hence, Math.random()*17 will be from 0.0 to 16.999
    // double doubleRandomNumber = Math.random() * 17; // default code
    // cast the double to whole number
    // int randomNumber = (int)doubleRandomNumber; // default code
    // return (randomNumber); // default code

    
    // Extract the numeric values of the from values (street and avenue)
    int fromAvenue = Character.getNumericValue(from.charAt(0));
    int fromStreet = Character.getNumericValue(from.charAt(3)); // takes index 3 of the input string
    // Extract the numeric values of the to values (street and avenue)
    int toAvenue = Character.getNumericValue(to.charAt(0));
    int toStreet = Character.getNumericValue(to.charAt(3)); // takes index 3 of the input string

    // calculate avenue and street distances separately
    int avenueDistance = Math.abs(toAvenue - fromAvenue);
    int streetDistance = Math.abs(toStreet - fromStreet);

    return avenueDistance + streetDistance; // then add them togehter for total distance of city blocks

    // End code

    // 
    // 34 4th street = (3 fromAvenue, 4 fromStreet) = (3rd avenue and 4th street)
    // 57 2nd avenue = (5 toAvenue, 2 toStreet) = (5th avenue and 2nd street)
    // |2-4| + |3-5| = 4 
    // avenueDistance = toStreet - fromStreet
    // streetDistance = toAvenue - fromAvenue  

    // 10 1st street = (1,1) = (1st avenue and 1st street)
    // 91 9th avenue = (9,9) = (9th avenue and 9th street) 
    // |9-1| + |9-1| = 16 

    // 34 2nd street and 34 5th street = 3
  }
}

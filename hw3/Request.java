import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class simulating an HTTP request.
 */
public class Request
{
   private String command;
   private Map<String, String>  params = new LinkedHashMap<>();

   /**
    * Reads a request.
    * @param input the scanner from which to read the request
    * @return the request
    */
   public static Request read(Scanner input)
   {
      Request request = new Request();
      request.command = input.nextLine();
      boolean done = false;
      while (!done)
      {
         String line = input.nextLine().trim();
         if (line.isEmpty()) done = true;
         else
         {
            int pos = line.indexOf('=');
            if (pos == -1) throw new IllegalArgumentException("= expected in " + line);
            request.params.put(line.substring(0, pos), line.substring(pos + 1));
         }
      }
      return request;
   }
   
   /**
    * Gets the value for a given key.
    * @param key the key for the requested value
    * @return the associated value
    */
   public String get(String key)
   {
      String result = params.get(key);
      if (result == null) throw new NoSuchElementException("No key " + key);
      return result;
   }
   
   /**
    * Gets the integer value for a given key.
    * @param key the key for the requested value
    * @return the associated integer value
    */
   public int getInt(String key)
   {
      return Integer.parseInt(get(key));
   }
   
   /**
    * Gets the command of this request
    * @return the command
    */   
   public String getCommand()
   {
      return command;
   }
}

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A successful or failed reply containing key=value pairs.
 */
public class Reply
{
   private boolean success;
   private Map<String, String>  params = new LinkedHashMap<>();

   /**
    * Constructs a successful reply with given keys and values.
    * @param keysAndValues alternating keys and values
    */
   public Reply(Object... keysAndValues)
   {
      this.success = true;
      for (int i = 0; i < keysAndValues.length; i += 2)
      {
         params.put(keysAndValues[i].toString(), keysAndValues[i + 1].toString());
      }
   }
   
   /**
    * Constructs a failed reply.
    * @param reason the reason for failure
    */
   public Reply(String reason)
   {
      this.success = false;
      params.put("reason", reason);
   }
   
   /**
    * Yields a string representation of this reply.
    * @return the string representation
    */
   public String toString()
   {
       StringBuilder result = new StringBuilder();
       result.append(success ? "OK\n" : "ERROR\n");
       for (Map.Entry<String, String> entry : params.entrySet())
       {
             result.append(entry.getKey());
             result.append("=");
             result.append(entry.getValue());
             result.append("\n");
       }
       return result.toString();
   }
}

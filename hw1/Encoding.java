
import java.util.*;

public class Encoding
{
   public static Set<String> morseCodes(int m, int n)
    {
        Set<String> result = new TreeSet<>();

        code(m,n, "", result);

        return result;
    }

    public static void code(int m, int n, String s, Set<String> result)
    {

        if( m == 0 && n == 0 ){
            result.add(s);
            return;
        }

        if( n == 0 ){
            code(m-1, n,s + ".", result);
            return;
        }
        if(m==0){
            code(m,n-1,s + "-", result);
            return;
        }

        code(m-1, n,s + ".", result);
        code(m,n-1,s + "-", result);

        return;
    }
}
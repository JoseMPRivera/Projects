import java.util.Iterator;

public interface IntSet
{
   boolean test(int n);
   void set(int n);
   void clear(int n);
   int min();
   int max();
   int size();
   Iterator<Integer> iterator();

}

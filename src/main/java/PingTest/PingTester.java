
package PingTest;

public class PingTester
{
   private static PingTester instance = null;
   
   private PingTester()
   {
      
   }
   
   public static PingTester getInstance()
   {
      if(instance == null)
      {
         instance = new PingTester();
      }
      return instance;
   }
   
   public String getPing()
   {
      return "OK";
   }
}

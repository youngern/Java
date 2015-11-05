import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Arrays;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
public class PassHash{
  private static String getPass(String password){
    String hexPass = new String();
    try {
      MessageDigest hash = MessageDigest.getInstance("SHA-1");
      int hashn = 100;
      while(hashn-- > 0) {
        hash.update(password.getBytes());
      }
      byte[] hashBytes = hash.digest();
      HexBinaryAdapter hexConverter = new HexBinaryAdapter();
      hexPass = hexConverter.marshal(hashBytes);
    } catch (NoSuchAlgorithmException alg) {
      System.err.println("No such algorithm");
    }
    return hexPass;
  }
  public static void main(String args[]){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter password: ");
    String password = getPass(input.nextLine());
    System.out.println();

    System.out.println("Type exit to quit, else press enter");
    while(!input.nextLine().equals("exit")) {
      System.out.print("Enter password again: ");
      String retry = getPass(input.nextLine());
      System.out.println();
      if(password.equals(retry)) {
         System.out.println("Those match!");
         System.out.println(password);
      } else {
         System.out.println("Those do not match");
      }
      System.out.println("Type exit to quit, else press enter");
    }
  }
}

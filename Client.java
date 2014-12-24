import java.util.*;
import java.io.*;
import java.net.*;

public class Client {

  static ObjectOutputStream objectOut;
  static ObjectInputStream objectIn;

  public static void sendArrayList(ArrayList<Integer> list){
    try {
      objectOut.writeObject(list);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static ArrayList<Integer>  getArrayList() {
    try {
      Object object = objectIn.readObject();
      @SuppressWarnings("unchecked")
      ArrayList<Integer> list = (ArrayList<Integer>) object;
      return list;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    // only happens if error occurs
    System.out.println("fatal: no object read from ObjectInputStream");
    System.exit(1);
		return null;
  }

  public static ArrayList<Integer> readRandomFile (String fname){
    ArrayList<Integer> list = new ArrayList<Integer>();
    try {
      Scanner s = new Scanner(new File(fname));
      while(s.hasNextInt())
        list.add(s.nextInt());
      return list;
    } 
    catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  
  public static void main(String [] args){
    try {
      Socket soc = new Socket("localhost", Integer.parseInt(args[0]));
      objectOut = new ObjectOutputStream(soc.getOutputStream());
      objectIn = new ObjectInputStream(soc.getInputStream());
    } catch(Exception e) {
      e.printStackTrace();
    }
    ArrayList<Integer> list = readRandomFile("random_numbers");
    System.out.println(list);
    sendArrayList(list);
    ArrayList<Integer> sortedlist = getArrayList();
  }
}

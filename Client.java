import java.util.*;
import java.io.*;
import java.net.*;

public class Client {

  static Socket soc;
  static ObjectOutputStream objectOut;
  static ObjectInputStream objectIn;

  public static void sendArrayList(ArrayList<Integer> list, int port){
    try {
      Socket sock = new Socket("localhost", port);
      ObjectOutputStream ObjectOut = new ObjectOutputStream(sock.getOutputStream());
      ObjectOut.writeObject(list);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static ArrayList<Integer>  getArrayList(int port) {
    try {
      Socket sock = new Socket("localhost", port);
      ObjectInputStream ObjectIn = new ObjectInputStream(sock.getInputStream());
      Object object = ObjectIn.readObject();
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
      int port = Integer.parseInt(args[0]);
      soc = new Socket("localhost", port);
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

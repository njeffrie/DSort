import java.util.*;
import java.io.*;
import java.net.*;

public class Client {
  public static void sendArrayList(ArrayList<Integer> list){
    try {
      Socket sock = new Socket("localhost", 6066);
      ObjectOutputStream ObjectOut = new ObjectOutputStream(sock.getOutputStream());
      ObjectOut.writeObject(list);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static ArrayList<Integer>  getArrayList() {
    try {
      Socket sock = new Socket("localhost", 6066);
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
    ArrayList<Integer> list = readRandomFile("random_numbers");
    System.out.println(list);
    sendArrayList(list);
    ArrayList<Integer> sortedlist = getArrayList();
  }
}

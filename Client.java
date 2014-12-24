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
  
  public static void writeRandomFile (String fname, ArrayList<Integer> list) 
    throws FileNotFoundException 
  {
    PrintWriter pout = new PrintWriter(fname);
    pout.print(list);
    pout.flush();
    pout.close();
  }

  public static void main(String [] args){
    try {
      Socket soc = new Socket("localhost", Integer.parseInt(args[0]));
      System.out.println("created socket");
      objectOut = new ObjectOutputStream(soc.getOutputStream());
      System.out.println("created output stream");
    System.out.println("starting file read");
    ArrayList<Integer> list = readRandomFile("random_numbers");
    System.out.println(list);
    sendArrayList(list);
    objectIn = new ObjectInputStream(soc.getInputStream());
    System.out.println("created input stream");
    ArrayList<Integer> sortedlist = getArrayList();
    System.out.println(sortedlist);
      writeRandomFile("sorted_numbers", list);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

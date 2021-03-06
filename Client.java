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
    for (int i=0; i<list.size(); i++){
      pout.print(list.get(i) + " ");
    }
    pout.flush();
    pout.close();
  }

  public static void main(String [] args){
    if(args.length != 2){
      System.out.println("Usage: arg0 = port number, arg1 = source file name");
      System.exit(1);
    }
    try {
      try {
        Socket soc = new Socket("localhost", Integer.parseInt(args[0]));
        objectOut = new ObjectOutputStream(soc.getOutputStream());
        ArrayList<Integer> list = readRandomFile(args[1]);
        //System.out.println("Unsorted List:\n"+list);
        sendArrayList(list);
        objectIn = new ObjectInputStream(soc.getInputStream());
        ArrayList<Integer> sortedlist = getArrayList();
        //System.out.println("Sorted List:\n"+sortedlist);
        writeRandomFile("sorted_numbers", sortedlist);
      } catch(ConnectException ce) {
        System.out.println("Failed to connect. Check port compatibility, make sure Server is running");
        System.exit(1);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

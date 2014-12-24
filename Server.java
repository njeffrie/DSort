import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
  public static void main(String[] args) {
    try 
    {
      ServerSocket myServerSocket = new ServerSocket(6060);
      Socket skt = myServerSocket.accept();
      ObjectInputStream in = new ObjectInputStream(skt.getInputStream());
      Object object = in.readObject();
      @SuppressWarnings("unchecked")
      ArrayList<Integer> list = (ArrayList<Integer>) object;
      Collections.sort(list);
      ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
      out.writeObject(list);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}

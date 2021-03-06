import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Thread{
  private ServerSocket serverSocket;

  public Server(int port) throws IOException
  {
    serverSocket = new ServerSocket(port);
  }

  public void run()
  {
    while (true)
    {
      try
      {
        Socket server = serverSocket.accept();
        ObjectInputStream objectInput = 
          new ObjectInputStream(server.getInputStream());
        Object object = objectInput.readObject();
        @SuppressWarnings("unchecked")
        ArrayList<Integer> list = (ArrayList<Integer>) object;
        System.out.println("got list... sorting");
        Collections.sort(list);
        System.out.println("finished sorting list");
        ObjectOutputStream objectOutput = 
          new ObjectOutputStream(server.getOutputStream());
        objectOutput.writeObject(list);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("Usage: arg0 = port number");
      System.exit(1);
    }
    int port = Integer.parseInt(args[0]);
    try
    {
      Thread t = new Server(port);
      t.start();
    }catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

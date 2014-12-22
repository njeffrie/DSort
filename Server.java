import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Server {
	
	static ServerSocket ss;
	static Socket s;
	static BufferedReader br;
	static BufferedWriter bw;
	static String[] names = new String[1000];
	static BufferedReader[] brArray = new BufferedReader[1000];
	static BufferedWriter[] bwArray = new BufferedWriter[1000];
	
	public static void main(String[] args) {
		
		try {
		
			ss = new ServerSocket(8100);
		
			while(true) {
				
				s = ss.accept();
	
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				String name = br.readLine();
				
				int nextSpace = 0;
				while(names[nextSpace] != null) {
					nextSpace++;
				}
				
				names[nextSpace] = name;
				brArray[nextSpace] = br;
				bwArray[nextSpace] = bw;
		
				RTS t = new RTS(br, bw, name, names, brArray, bwArray);
				
				int i=0;
				while(names[i] != null) {
					if((i != nextSpace) && (names[i].compareTo("0")!=0)){
						bw.write("@#$"+names[i]);
						bw.newLine();
						bw.flush();
					}
					i++;
				}
				bw.write("$#@");
				bw.newLine();
				bw.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

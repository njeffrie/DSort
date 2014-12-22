import java.io.*;

public class randomgen{

	static int[] randoms;
	static int min, max, range;
	static PrintWriter pout;
	
	public static void main(String args[]){
		if(args.length != 3){
			System.out.println("Usage: arg1 = number of ints to generate, arg2 = min, arg3 = max");
			System.exit(1);
		}
		
		randoms = new int[(int)Integer.parseInt(args[0])];
		min = Integer.parseInt(args[1]);
		max = Integer.parseInt(args[2]);
		range = max - min;
		
		try{
			pout = new PrintWriter("randout.txt");
		
			for(int i=0; i<randoms.length; i++){
				randoms[i] = (int)(Math.random()*range+min);
			}
		
			for(int i=0; i<randoms.length; i++){
				//System.out.println(randoms[i]);
				pout.print(randoms[i]+" ");
			}
			pout.flush();
			pout.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

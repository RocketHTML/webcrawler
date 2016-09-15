package Ringz.doll;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class RocketPrinter extends Thread { // takes an active socket, tests if its active, prints to its output stream from system.in

	Socket friend;
	OutputStream friendStream;
	PrintWriter printer;
	Doll doll;

	public RocketPrinter(Socket activeSocket, Doll d){
		doll = d;
		friend = activeSocket;
		try{
			friendStream = friend.getOutputStream(); //IOException
			printer = new PrintWriter(friendStream,true);
			if(friend.isConnected()){
				printer.println(doll.tug());doll.print("RocketIO Build 1 Connection Success");}
			else doll.print("RocketPrinter failed to connect.");	
		}
		catch(IOException nope){
			System.out.println("cannot get friend's output stream");
		}
	}
	
	public void run(){
		// hopefully this thread blocking, waiting on system.in, 
			// does not block the main 
		
		Scanner darkly = new Scanner(System.in);
		String input = darkly.nextLine(); // from system.in
		while(null != input){
			printer.println(doll.name()+": "+input);
			if(input.equalsIgnoreCase("unicorn") || interrupted()) break;
			input = darkly.nextLine(); // from system.in
			

		}
		
		System.out.println("Rocket printer shutting down");
				

	}






}
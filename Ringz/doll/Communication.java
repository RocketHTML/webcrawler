package Ringz.doll;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URL;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Scanner;

// hostname 
// global IP address: 98.200.77.230

// Things to do
// Make a ServerSocket to 9090
// clientSocket = serverSocket.accept();
// BufferedReader br = new BF(clientSocket.getInputStream());
// String incoming;
// while ((incoming = br.readLine()) != null)
	// 

public class Communication{

	private Help help;
	private Doll doll; 
	private ArrayList<Socket> connections;
	private Socket attention;
	private String globalIP;
	private ArrayList<String> hosts;
	private int serverPort = 10050;
	public Communication(Doll d){ 
		doll = d; help = d; 
		connections = new ArrayList<Socket>();	
		hosts = new ArrayList<String>();
		globalIP = getGlobalAddress();	}


	public Socket getAttention(){return attention;}
	public ArrayList<String> getConnections()
	{
		ArrayList<String> connects = new ArrayList<String>();
		for(Socket connect : connections)
			connects.add(connect.getInetAddress().getHostName());
		return connects; 
	}


	public String getHostAddress()
	{
		String hostAddress;
		try{
		InetAddress me = InetAddress.getLocalHost();
		hostAddress = me.getHostAddress();
		return hostAddress;
		}
		catch(UnknownHostException vegeta){ 
			help.initCause(vegeta);
			help.print("Why is the host unknown?...");
			return "";	}

		//ServerSocket aladdin = new ServerSocket()
	}
	public String getHostAddress(Socket friend){
		try{
			return friend.getInetAddress().getHostName();
		}
		catch(Exception e){help.initCause(e);help.printStackTrace(); return null;}
	}

	public String getGlobalAddress()
	{

		try {
			URL whatismyip = new URL("https://www.myglobalip.com");
			BufferedReader in = new BufferedReader(
				new InputStreamReader(whatismyip.openStream()));
			
			String ip; //reading the page's html line by line into ip
			String one = "\\Qh3 id=\"default\"\\E|"+
				"\\Q<h3 id=\"ip4\"\\E|"+
				"\\Q<h3 id=\"ip4\"\\E";
			Pattern defPatt = Pattern.compile(one); 
			while(null != (ip = in.readLine()) )	{
			 	//help.print(ip, 1);
			 	Matcher matcher = defPatt.matcher(ip);
			 	if(matcher.find()){
			 		String two = "\\d+\\.[\\d\\.]*";
			 		//a didget followed by another didget or a .
			 		Pattern addrPatt = Pattern.compile(two);
			 		Matcher matcher2 = addrPatt.matcher(ip);
			 		if(matcher2.find()) //Matcher.find() generates the group
			 			return matcher2.group();
			 		//return matcher2.group();
			 		//return "";
			 	}

			 }
			 help.print("Could not find Global Address using myglobalip.com...");
			 return ip;
			} //should be IP address
			// Learn pattern matching
			// return the globalIPAddress 

			//metacharacters: <([{\^-=$!|]})?*+.>
	
	//	<h3 id="default"><span class="ip">98.200.77.230</span></h3>
      //  <h3 id="ip4" style="display:none"><span class="ip"></span></h3>
        //<h3 id="ip6" style="display:none"><span class="ip"></span></h3>
	

		catch(Exception x) {
			help.initCause(x);
			help.print(help.getMessage());
			return "global address not found";	}

	}

	

	public void listen (){ 		listen(serverPort);		}
	public void listen (int port){
		try{
			ServerSocket me = new ServerSocket(port, 0, InetAddress.getLocalHost());

boolean unicornServer = false;
while(true){
			help.print("my GlobalIpAddress is " + getGlobalAddress());
			help.print("listening on port "+port);
			Socket original = me.accept(); // Sockets are created with InetAddresses
			attention = original;	// set attention
			help.print("connection accepted");
			String originalHost = original.getInetAddress().getHostName();

			//////////////////////////////////////////////
			//////////////   Listen to New friend ////////////
			//////////////////////////////////////////////
			BufferedReader in = 
				new BufferedReader(new InputStreamReader(attention.getInputStream()));
			help.print("input stream from "+originalHost+" obtained"); // needs to be regexed 

boolean unicorn = false;
			//////////////////////////////////////////////////////////
			////////// Print Incoming Messages /////////////
			//////////////////////////////////////////////////
			///////// corrupt code:  while(incoming == null) {incoming = in.readLine();}  /////////
			String line = in.readLine();
			help.print(line, true);
			if ((null == line) || attention.isClosed())	continue;	// Listen again if initialze connection broke early 

					////////////////////////////////////////////
			///////////////// Capture friendGlobalIp /////////////////
			///////////////////////////////////////////////////////////

			String[] input = line.split(": ");
			String friendGlobalIp = input[1];	// first message is always global IP address
			boolean add = true;
			
			for(String host : hosts)
				if (host.equalsIgnoreCase(friendGlobalIp))	// check to see if it should be added (in n time currently)
					add = false;
			if(add) hosts.add(friendGlobalIp); 
				//connections.add(new Socket(InetAddress.getByName(friendGlobalIp), original.getPort()));

					// Creating this new socket, using friendGlobalIp, rejected me...
					// Maybe because it hadn't used accept yet....  
					// So even just making the socket, requires the server to call accept()


						/////////////////	/////////////////
			/////////////////  Read incoming messages /////////////
					/////////////////	/////////////////
			String incoming = in.readLine();
			while(!unicorn && (incoming != null) && !attention.isClosed()) 
				{	help.print(incoming, true);  
					input = incoming.split(": ");
					if(input.length == 1) break;
					if(input[1].equalsIgnoreCase("unicorn")) {unicorn = true; break;}
					if(attention.isClosed()) {break;} // unicorn was said..(or connection terminated)
					incoming = in.readLine();	}

			if(!attention.isClosed())attention.close();
			help.print("closing input stream");


while (!unicorn){
			//////////////////////////////////////////////
			//////////////   Find My New Friend ////////////
			//////////////////////////////////////////////

			
			help.print("opening send stream");
			Socket lucky = me.accept();
			PrintWriter toSend = new PrintWriter(lucky.getOutputStream(), true);
			toSend.println(doll.name()+ ": "+this.globalIP);
		//	int counter = 0;
		//	while(!unicorn){
		//		if ((counter++ % 10000000) == 0)
		//	toSend.println(doll.name() + ": "+counter);
			//Thread.sleep(500);
		//	}
			
			//while(true){
			//String luckyHost = lucky.getInetAddress().getHostName();
			//if (!attention.getInetAddress().getHostName().equalsIgnoreCase(luckyHost))
			//	 continue;
			//break; 
			//}
			attention = lucky; //reset attention 
			
			

			//////////////////////////////////////////////
			//////////////   Send to Friend ////////////
			//////////////////////////////////////////////

			
			BufferedReader speak = new BufferedReader(new InputStreamReader(System.in));
			String response = speak.readLine(); // One definition says this is the bad code
			//help.print(response);
			while((null != response) && !attention.isClosed()){
				unicornServer = response.equalsIgnoreCase("unicorn server");
				unicorn = response.equalsIgnoreCase("unicorn");

			toSend.println(doll.name() +": "+response);   ///// print to client 

				if(unicorn || unicornServer) break; 
				if(response.equals("")) break;
			response = speak.readLine();

			}
			attention.close();
			help.print("closing send stream");
			if(unicorn || unicornServer)break;
			//////////////////////////////////////////////
			//////////////   Find My Friend ////////////
			//////////////////////////////////////////////
			

			while(true){
			lucky = me.accept(); /// listen for luckies, overwriting until attention is matched ///
			String luckyHost = lucky.getInetAddress().getHostName();
			if (!attention.getInetAddress().getHostName().equalsIgnoreCase(luckyHost))
				continue;
			attention = lucky; //reset attention
			break;
			}
				// I think I can be slow when it's my turn to read,
				// because reading lets the outputStream stack up.
			//////////////////////////////////////////////
			//////////////   Listen to Friend ////////////
			//////////////////////////////////////////////
			BufferedReader listen = new BufferedReader(new InputStreamReader(attention.getInputStream()));
			String initGlobalIp = listen.readLine();
			help.print(initGlobalIp, true);

			String friend = listen.readLine(); //the actual message 
			while(!attention.isClosed() && (null != friend)) {
				help.print(friend, true);
				input = friend.split(": ");
				if(input.length == 1)break;
				unicorn = input[1].equalsIgnoreCase("unicorn");  
				//// Here we can make an interface to control the Server from client  /////
				/////////// Notice we can break the input up by ": ", and assign meaning to each index /////
				///// name = input[0]. message = input[1]. command = input[2]. location = input[3]
				///////////////////////////////////////////////////////////////////////////////////////////
				if(unicorn) {help.print("unicorn detected. communication terminating...");break;}
				friend = listen.readLine();
			}

}// !unicorn
}// !unicornServer
		}
		catch(Exception e){
			help.initCause(e);
			help.print(help.getMessage());
		}

	}
	
	public void listenLocally (){
		try{
		ServerSocket me = new ServerSocket(serverPort, 0, InetAddress.getLoopbackAddress()); // works
		attention = me.accept(); // works with poke()
		help.print("connection to self success"); // works 
		}
		catch(Exception e){
			help.initCause(e);
			help.print(help.getMessage());
		}
	}

	

	public void pokeLocally(){ 	poke(InetAddress.getLoopbackAddress(), serverPort, "local poke");	}
	public void poke()
	{
		if 		(null != attention)  	poke(	attention.getInetAddress().getHostName(),	serverPort);
		else 							pokeLocally();
	}

	public void poke(String host)					{ poke(host, serverPort); }
	public void poke(String host, int port){
		try		
		{ poke(	InetAddress.getByName(host), port, getGlobalAddress()	);	}
		///// notice the InetAddress needs

		catch(Exception e)	{ help.initCause(e);	help.print(help.getMessage());			}
	}
	

	public void poke(InetAddress host, int port, String globalIP){
		try
			{

			//////////////////////////////////////////////////////////
			////////// Send Global IP Address  ////////////////////////////
			//////////////////////////////////////////////////////////
			
			doll.print("sending my IP "+globalIP);
			boolean unicorn = false;
while(!unicorn){
			String[] input;
			//////////////////////////////////////////////////////////
			///////// Send a Message  /////////////////////////
			//////////////////////////////////////////////////////////
			Socket target = new Socket(host, port); // this didn't throw an error, but it did timeout. Nothing found. 
			help.print("*speaks*");
			PrintWriter printer = new PrintWriter(target.getOutputStream(), true);	// this isn't working 
			BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
			printer.println(doll.name()+": "+globalIP); // made it 
			String send = cin.readLine(); // waiting.
			while((send != null) && !target.isClosed() && (!unicorn)){ 
				String message = doll.name()+": "+send;
				printer.println(message);
				input = message.split(": ");
					// will throw if the reciever shuts itself off.

				if(input.length==1)break; // nope 
				if(input[1].equals("unicorn")) {unicorn = true;break;} //nope

				send = cin.readLine(); //

			 }
			 if(!target.isClosed()) 	{target.close();help.print("we closed the send socket");}
			 else help.print(host.getHostName() + " has closed");
			 // We closed the "let me send" connection.
			 if(unicorn) break; // if unicorn, cease communication.  


			// Opening the "let me read" connection.
			//////////////////////////////////////////////////////////
			//////// Search for Response /////////////////////////////////
			//////////////////////////////////////////////////////////
			Socket reTarget = new Socket(host, port);	// Server should be trying to accept this.... 
			reTarget.setSoTimeout(0);
			//BufferedReader listen = new BufferedReader(new InputStreamReader(reTarget.getInputStream())); 
			Scanner scanInput = new Scanner(reTarget.getInputStream());			
														// Server gets your output stream to complete connection
			help.print("*listens*");
			String response="";
			while(scanInput.hasNextLine())
			{response = scanInput.nextLine(); // perhaps this was null
			help.print(response, true);
			input = response.split(": ");
			if(input.length == 1) break;
			if(input[1].equalsIgnoreCase("unicorn") || input[1].equalsIgnoreCase("unicornServer"))
					{unicorn = true; break;}
			}

			if(!reTarget.isClosed()) 	{reTarget.close();help.print("closing response socket");}
			else help.print("response socket was closed");
					// Response Socket closed. 

			if(unicorn) break;
}// !unicorn

			help.print("keywork UNICORN detected... Connection Terminating...");
			
			
			boolean add = true;
			for(int i=0; i < hosts.size(); i++)
				if(	hosts.get(i).equalsIgnoreCase(	host.getHostName()	))
					add = false;

			attention = 	new Socket(host, port);
			if (add)	{	connections.add(attention); hosts.add(host.getHostName());	}

		}

		catch(Exception e){
			help.initCause(e);
			help.print(help.getMessage());
			
		}
	}

	public void communicate() { listen();}
	public void communicate(String host) { poke(host);}
	public void communicate(String host, int port) { poke(host, port);}
	

		//String friendIp = hosts.get(connections.indexOf(friend)).getHostName(); // listens appends it; it will be there
					
				//BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				//PrintWriter sendString = new PrintWriter(friend.getOutputStream(), true);
				
			//	new Thread(new Runnable(){
			//		public void run(){
			//			BufferedReader inStream = 
			//			new BufferedReader(new InputStreamReader(friend.getInputStream()));
			//			String incoming;
			//			while((incoming = inStream.readLine())!= null)
			//				System.out.println(incoming);
			//		}
			//	});

	public void rocketIO(){
		try{
			ServerSocket base = new ServerSocket(serverPort,0, InetAddress.getLocalHost());
			help.print("listening...");
			Socket friend = base.accept();
			friend.setSoTimeout(0);
			RocketPrinter rp = new RocketPrinter(friend, doll); //this prints to its output stream
			rp.start();
			// start reading from it's inputstream
			Scanner incoming = new Scanner(friend.getInputStream());
			String message = incoming.nextLine();
			String[] input;
			while(null != message){
				help.print(message, true);
				input = message.split(": ");
				if(input.length > 1 && input[1].equalsIgnoreCase("unicorn")){help.print("unicorn detected"); break;}
				if(incoming.hasNextLine())message = incoming.nextLine();
				else message = null;
			}
			help.print("no longer reading input...");
			if(rp.getState() != Thread.State.TERMINATED){help.print("Terminating print thread");rp.interrupt();}
					rp.interrupt(); //must not have permission

		}
		catch(Exception c){
			help.print("rocketIO failed");
			c.printStackTrace();
		}
		


	}

	public void rocketIO(String host){
		try{
			Socket friend = new Socket(host, serverPort); // failed to connect 
			friend.setSoTimeout(0);
			help.print("searching...");

			RocketPrinter rp = new RocketPrinter(friend, doll);
			rp.start();

			Scanner incoming = new Scanner(friend.getInputStream());
			String message = incoming.nextLine();
			String[] input;
			while(null != message){
				help.print(message, true);
				input = message.split(": ");
				if(input.length > 1 && input[1].equalsIgnoreCase("unicorn")){help.print("unicorn detected"); break;}
				if(incoming.hasNextLine())message=incoming.nextLine(); // this is why it shuts down. friend closed output stream
				else message = null;
			}
			help.print("no longer reading input...");
			if(rp.getState() != Thread.State.TERMINATED){help.print("Terminating print thread");rp.interrupt();}
					rp.interrupt();
		}
		catch(Exception x){
			help.print("rocketIO failed");
			x.printStackTrace();

		}
	}		
	




}






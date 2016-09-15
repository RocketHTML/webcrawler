package Ringz.doll;
import java.net.Socket;
import java.util.ArrayList;

public class Doll extends Help{

///////  Class Fields ////////
	private int limbs;
	private int battery = 0;
	private String name;
	private boolean on = false;
	
	private TV tv;
	private Communication communications; /// turns out you can get the same socket's inputStream AND outputStream simultaneously. 
	private Filetree filetree; 
//////////////   //////////////


///////////// Constructors /////////////////
/////////////////////////////////////////////

public Doll(String n){
	super(n);
	filetree = new Filetree(this);
	communications = new Communication(this);
	if(n.equals(""))
		setName(getProperty("user.name"));
	else setName(n);
	limbs = name.length() % 5;
	
	
	
}

public Doll(){
	this(""); // this doll has no limbs
}

/////////////////////////////////////////
/////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////
//////////////// BASIC METHODS ///////////////////
/////////////////////////////////////////////////////////////////////////
public String tug(){
	String result;

	if (limbs == 4) 	result = name + " has all " + limbs + " limbs!!";
	else  				result = name + "'s limbs: "+limbs();
	
	return result;
		
}


public void eat(Burger b){
	if(b.eat())
		{battery = 2; System.out.println(name + " ate burger. Tank full.");}
	else
		System.out.println(name + ": burger already eaten...");
	battery();

}

public void powerSwitch(){
	tv.powerSwitch();
	if(battery>0)
		battery--;
	else System.out.println(name + ": hungry...");

}

public int battery(){
	return battery;
}

public int limbs(){return limbs;}
public String name(){return name;}
public void setName(String n){super.setName(n); name=n;}

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
	/////  Socket Communication Methods ////////
//////////////////////////////////////////////////////////////////////////
public String getHostAddress(){					return communications.getHostAddress();			}
public String getHostAddress(Socket friend){	return communications.getHostAddress(friend);	}
public String getGlobalAddress(){				return communications.getGlobalAddress();		}

public Socket getAttention(){				return communications.getAttention();		}
public ArrayList<String> getConnections(){	return communications.getConnections();		}

public void listen(){	 		 communications.listen();					}
public void listen(int port){	 communications.listen(port);				}
public void listenLocally(){	 communications.listenLocally();			}

public void poke(){								communications.poke();				}
public void poke(String host){ 					communications.poke(host);			}
public void poke(String host, int port){		communications.poke(host, port);	}

public void communicate(){						communications.communicate();				}
public void communicate(String host){			communications.communicate(host);			}
public void communicate(String host, int port){	communications.communicate(host, port);		}

public void rocketIO(){							communications.rocketIO();					}
public void rocketIO(String host){				communications.rocketIO(host);				}
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
		////// File Tree Methods ////////////
//////////////////////////////////////////////////////////////////////////
public String[] getProperties(){	return filetree.getProperties();	}
public String getProperty(String prop){	return filetree.getProperty(prop);}
public void printProperties(){			filetree.printProperties();		}



//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
	/// Throwable Methods ///
		/// Extended from Help class - read Help class for throwable methods
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////  Need to overwrite the getMessage() Help method 
////
//public String getMessage(){ //should depend on the amount of energy }

public String toString(){	return this.getMessage(); }
//public void drop(){cause = null;}




//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////



}
package Ringz;
import Ringz.doll.Doll;


public class Chat{
	public final static String RINGZ = "148.85.251.109";

	public static void main(String[] args){
		String IP = RINGZ;
		if(null != args && args.length != 0){
			System.out.println("looking for: "+IP);
			IP = args[0];
		}
			

		Doll gaz = new Doll("");
		gaz.rocketIO(IP);


	}
}
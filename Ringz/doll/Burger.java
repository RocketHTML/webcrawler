package Ringz.doll;

public class Burger
{
	private boolean eaten = false;

	public boolean eat(){
		boolean answer = eaten;
		eaten = true;
		return !answer;
	}	
}
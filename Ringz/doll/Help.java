package Ringz.doll;
public class Help extends Throwable{
	public String name = "gaz";
	
	public Help(){}
	public Help(String n){ name = n; }

	/////////// publically useful methods ///////////
	public void print(String s)								{if(null==s)return;print(name+": "+s,true);}
	public void print(String[] s)							{if(null==s)return;print(name,true);print(s,true);}
	public static void print(String s, boolean no_name_tag)			{if(null==s)return;System.out.println(s);}
	public static void print(String[] s, boolean lul)		{if(null==s)return;for(String x: s)System.out.print(x+" ");print("",true);}

	public void pop(){print("made it.");}

	public void explode() throws Help {throw this;}
	public void setName(String s){name = s;}

protected Throwable cause; 
public String getMessage(){
	if (null == cause)
		return "";
	return ""+cause;
}
public Throwable initCause(Throwable c){cause=c;return c;}
public Throwable getCause(){return cause;}
public String toString(){
	return getMessage();
}
public void drop(){cause = null;}




}
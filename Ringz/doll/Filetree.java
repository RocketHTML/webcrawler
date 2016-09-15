package Ringz.doll;
import java.util.Properties;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;

	///// check to see what Kim's properties are //////

public class Filetree{
	private Doll doll;
	private Help help;
	//private String[] properties;
	private Hashtable<String,String> props;
	private Properties sysProperties; //os.name//
	private final String[] propNames = 
		{
			"user.name", "user.language","user.country","user.home"
			,"user.timezone","user.dir","os.name","file.separator"
		};


	public Filetree(Doll d){
			//	I want to write a file. But where?
			//  I want to make a directory. But where?
			//	In local directory (user.dir)  
				// file.separator
				// user.dir + file.separator + my_dir_name

			////// first print properties /////
			/////	Figure out how to get all key names /////
			//////// Set<String> Properties.stringPropertyNames() //////
				//// a Set is a collection that contains no duplicates ////
		doll = d;
		help = new Help(d.name()+".filetree");
		sysProperties = System.getProperties();
		initProperties(); // uses sysProps to get filtered properties
		//try{
		//OutputStream out = System.out; 
		//properties.store(out, null);	}   catch(IOException e) {help.print("inable to print to system.out...");	}

		

	}
	
	public String[] getSysProperties() 	{	return sysProperties.stringPropertyNames().toArray(new String[0]);			}
	public String[] getProperties()		{	return this.propNames;	}
	public String getProperty(String prop){	return this.props.get(prop);}
	public void printProperties()		{	for(String prop: this.propNames)  help.print(prop+" "+props.get(prop));	}

	public void initProperties()	{
		Hashtable<String,String> newProps = new Hashtable<>();
		for(String prop : propNames)	newProps.put(prop,sysProperties.getProperty(prop));
		this.props = newProps;		}

	public void write(String filename, String toWrite, boolean append){} //if it starts with /, its absolute. otherwise append currentDir
	public String read(String filename){return null;}
	public void cd(){cd(props.get("user.home"));} //filetree needs current directory variable
	public void cd(String foldername){}






}
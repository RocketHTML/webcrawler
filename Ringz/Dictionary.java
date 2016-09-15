
package Ringz;
import java.util.Scanner;
import Ringz.doll.Help;
import Ringz.webcrawler.Finder;

public class Dictionary {

	public static String getDefinition(String word){
		if(null==word)return null;
		String output = Ringz.webcrawler.URLCrawler.getMeta("http://www.merriam-webster.com/dictionary/"+word,"description");
		if(null==output)return null;
		String def = Finder.remove(output, "usage, synonyms, more.");
		String almostClean = Finder.remove(def,"&[^:]*;");
		String cleaner = Finder.remove(almostClean,".$");
		String clean = Finder.remove(cleaner,"define [^:]+: ");	// if remove finds no matches, it returns an ArrayIndexOutOfBoundsException
		
	//	Help.print("clean: "+clean,true);
		return clean;
	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Help.print("\nDictionary...\n\nsearch:", true);
		while(input.hasNextLine()){
			Help.print(getDefinition(input.nextLine()),true);
			Help.print("",true);
			Help.print("search:",true);
		}
	}


}
package Ringz;
import java.util.Scanner;
import Ringz.webcrawler.URLCrawler;
import Ringz.webcrawler.Finder;
import Ringz.doll.Help;

public class News {
	final static String google = "https://www.google.com/search?q=";


	public static void main(String[] whoh){

		Scanner in = new Scanner(System.in);
		System.out.println("query:");
		while(in.hasNextLine()){
			String[] dirtyUrls = URLCrawler.getCousinURLs(noSpaces(google+in.nextLine()));
			String[] urls = Finder.find(dirtyUrls, "\\Qurl?q=\\E([^&]+).*");
			String[] old = {"","",""}; //title description keywords 
			boolean oldBool = false;

			for(String url: urls) {
				String[] summary = URLCrawler.getSummary(url);
				int count = 0;
				for (String x: summary) {
					//Help.print("new is: "+x,true);
					//Help.print("old is: "+old[count],true);
					if(null != x && !x.equalsIgnoreCase(old[count])) {
						Help.print(x,true);
						oldBool = false;
						//Help.print("no match",true);
					} else if(null != x && x.equalsIgnoreCase(old[count]))oldBool = true;

					old[count++] = x;
				}

				/*
				String title = URLCrawler.getTitle(url); 					/// Each of these find methods costs a query to the url
				title = Finder.remove(title,"&[^:]*;");
				String description = URLCrawler.getMeta(url, "description");	/// Maybe I should make a "get summary" method
				description = Finder.remove(description,"&[^:]*;");
				String keywords = URLCrawler.getMeta(url, "keywords"); /// that gets title, description, and keywords
				keywords = Finder.remove(keywords,"&[^:]*;"); // gotta ping it 3 tiemes
				
				Help.print(title,true);
				Help.print(description,true);
				if(null!=keywords)	Help.print(keywords.split(", | |,"),true);
				*/
				if(!oldBool)
				Help.print("",true);
			} //// you need to 1. getPage() on each url in that list, 
			/// but 2. find <title>___<title>, <meta name="description" content="___">
			System.out.println("query:");
		}


	}


	private static String noSpaces(String spaces){
		StringBuilder noSpaces = new StringBuilder();
		for(int i=0; i<spaces.length();i++){
			if(spaces.charAt(i)==' ')
				noSpaces.append("+");
			else noSpaces.append(spaces.charAt(i));
		}
		return noSpaces.toString();
	}



	








}


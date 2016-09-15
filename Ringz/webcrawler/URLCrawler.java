package Ringz.webcrawler;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.util.ArrayList;

public class URLCrawler extends Ringz.doll.Doll{	/// crawls pages using URLs
	private String currentURL;
	private Ringz.doll.Doll help = new Ringz.doll.Doll("gaz.URLCrawler");
	// at some point I should make a regex builder class 
	public URLCrawler(){this("http://google.com");}
	public URLCrawler(String page){this(page,"");}
	public URLCrawler(String page, String name){super(name);currentURL=page;}

	public void setURL(String url)	{	currentURL = url;}
	public void setHelp(String name){	help = new Ringz.doll.Doll(name);}
	public String getParentURL(){return getParentURL(currentURL);}
	public static String getParentURL(String url) {String parentUrl = Finder.remove(url, "/[^/]*$|/[^/]*/$"); return parentUrl; }
	public String[] getCousinURLs()	{	return getCousinURLs(currentURL);}
	public static String[] getCousinURLs(String pageurl){
		/// find ALL urls on this page 
		/// construct a match regex that will return a complete URL 100% of the time 
			/// need a list of sample URLs. 
			/// ideally the regex rejects the current URL
		/// give finder the page, and the regex 
			/// we can check to see what indexes the current URL is in, and make a new array without it
			/// return the array, or construct a new one without current within it
		String regex1 = "\\Qa href=\\E\"([^\"]*)\"";  // a href="" : return whats between ""
		// String regex2 = "";
		String page = getPage(pageurl);
		String parentURL = getParentURL(pageurl);

		String[] unclean = Finder.findClean(page, regex1);  // found no http anchored in this site. some javascrip:void(0)
		String[] clean = new String[unclean.length];
		for(int i=0; i<unclean.length;i++){
			char start = unclean[i].charAt(0);
			if(start == '/')	clean[i] = parentURL+unclean[i];	//relative to the current page (parent page as source)
			else 				clean[i] = unclean[i];
		}
		return clean;
	}

	public String[] getFolders(){return getFolders(currentURL);}
	public String[] getFolders(String indexURL) {
		String[] all = getCousinURLs(indexURL);
		ArrayList<String> folders = new ArrayList<>();
		for(String x: all)
			if(x.charAt(x.length()-1)=='/')
				folders.add(x);
		return folders.toArray(new String[0]);

	}
	public String[] getFiles(){return getFiles(currentURL);}
	public String[] getFiles(String indexURL){
		String[] all = getCousinURLs(indexURL);
		ArrayList<String> files = new ArrayList<>();
		for(String x: all)
			if(!(x.charAt(x.length()-1)=='/')) files.add(x);
		return files.toArray(new String[0]);
	}

	public String getPage(){ return getPage(currentURL); }
	public static String getPage(String url){
		try{
			URL pageURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) pageURL.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0");
			Scanner in = new Scanner(connection.getInputStream());
			String result="";
			StringBuilder results = new StringBuilder();

			while( in.hasNextLine() )	{	results.append(in.nextLine());	}

			return results.toString().toLowerCase();
		}
		catch(Exception e){
			//Ringz.doll.Help.echo("URLCrawler.getPage() failed: "+url);
			//System.out.println(e.getMessage());
			return null;
		} 

	}

	public static String getTitle(String url){
		String[] title = Finder.find(getPage(url), "\\Q<title\\E[^>]*>([^<]*)");
		if(title != null)return title[0];
		else return ("no title: "+url);
	}

	public static String getMeta(String url, String name){
		String regex = "<meta[^>]+name=\"" + name + "\"[^>]*content=\"([^\"]*)\"";
		String[] content = Finder.find(getPage(url), regex);
		if (null != content && content.length != 0)
			return content[0];
		return null;//(name + " not found: "+url);
	}

	public static String[] getSummary(String url){
		String page = getPage(url);
		String[] titleArray = Finder.find(page, "\\Q<title\\E[^>]*>([^<]*)");
		String title = "no title: "+url;
		if(titleArray != null && titleArray.length>0){title = Finder.remove(titleArray[0],"&[^:]*;");}

		String[] descriptionArray = Finder.find(page,"<meta[^>]+name=\"description\"[^>]*content=\"([^\"]*)\"");
		String[] keywordsArray = Finder.find(page, "<meta[^>]+name=\"keywords\"[^>]*content=\"([^\"]*)\"");
		String description = null; String keywords = "";

		if(descriptionArray != null && descriptionArray.length>0)	description = Finder.remove(descriptionArray[0],"&[^:]*;");
		if(keywordsArray != null && keywordsArray.length>0)			keywords = Finder.remove(keywordsArray[0],"&[^:]*;") + "\n"+url;
		String[] result = {title,description,keywords};

		return result;


	}
	













}
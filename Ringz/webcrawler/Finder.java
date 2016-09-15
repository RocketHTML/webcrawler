package Ringz.webcrawler;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Scanner;
import Ringz.doll.Help;

public class Finder {
	

	public static String[] find(String page, String regex){	//// returns all matches in string 
		if(page==null)return null;
		Matcher matcher = Pattern.compile(regex).matcher(page);
		ArrayList<String> result = new ArrayList<>();
		int groups = matcher.groupCount();

		while(matcher.find())	
		{
			if(groups==0){result.add(matcher.group()); /*Help.echo("yep");*/}
			else if(groups>0) for(int i = 1; i <= groups; i++)
								result.add(matcher.group(i));	
		}

		if(result.isEmpty()) return null;
		return result.toArray(new String[0]);

	}

	public static String[] find(String[] words, String regex){
		ArrayList<String> result = new ArrayList<>();
		for(String x: words){
			String[] xx = find(x,regex);
			if(null!=xx)
				for (String y: xx)
					result.add(y);
		}
		return result.toArray(new String[0]);
	}

	public static String[] findClean(String page, String regex){	// no duplicates 
		String[] unclean = find(page, regex);
		ArrayList<String> clean = new ArrayList<>();
		for(String x: unclean)
			if(!clean.contains(x))clean.add(x);
		return clean.toArray(new String[0]);

	}

	// Doesnt work because there is no \n in the html pages returned 
	public static String[] findLine(String page, String regex){
		if(page==null)return null;
		ArrayList<String> result = new ArrayList<>();
		Scanner liner = new Scanner(page);
		Pattern search = Pattern.compile(regex);
		while(liner.hasNextLine()){
			String line = liner.nextLine();
			Matcher lineMatcher = search.matcher(line);
			if(lineMatcher.find())
				result.add(line);
		}
		return result.toArray(new String[0]);
	}

	
	// only removes correctly if it's the last thing in the string 
	public static String remove(String line, String regex){	// just works on main match
		if(null==line)return null;
		Matcher matcher = Pattern.compile(regex).matcher(line);
		StringBuilder answer = new StringBuilder();
		int[] start = new int[matcher.groupCount()+1]; 
		int[] end = new int[matcher.groupCount()+1];		// declare starts and ends 
			// append the start of the find
		for(int i = 0; i<start.length; i++)
			if(matcher.find()){		start[i] = matcher.start();	end[i] = matcher.end();	} //init starts and ends

			// walk past start - ends 
			int k = 0;
			boolean stick = true;
			for (int i = 0; i<line.length(); i++){
				if(k<end.length){
					if(i == start[k])	stick = false;
					if(i == end[k])		{stick = true; k++;	}
				}
				if(stick)		answer.append(line.charAt(i));
			}
		
		return answer.toString();	


	}






}
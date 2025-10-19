package dsaa.lab10;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName{
	public String name;
	// TODO? You can change implementation of Link collection
	public SortedMap<String,Link> link;
	
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = "";
		while (!line.equals("eod")) {
			line = scan.nextLine();
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++) {
				String[] symbols = words[i].split("");
				String linkstring = "";
				if (symbols.length > 5) {
					linkstring = (symbols[0] + symbols[1] + symbols[2] + symbols[3] + symbols[4]).toLowerCase();
				}
				if (linkstring.equals("link=")) {
					Link newlink = createLink(words[i].substring(5, symbols.length));
					if (newlink != null) {
						link.put(newlink.ref, newlink);
					}
				}
			}
		}
	}

	public static boolean isCorrectId(String id) {
		char[] chars = id.toCharArray();
		if(!Character.isLetter(chars[0]))
		{
			return false;
		}
		for(int i=1;i<chars.length;i++)
		{
			if(chars[i]!='_'&&!Character.isLetter(chars[i])&&!Character.isDigit(chars[i]))
			{
				return false;
			}
		}
		return true;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	static Link createLink(String link) {
		String word = "";
		int number=1;
		char[] chars = link.toCharArray();
		int counter = 1;
		boolean nawias=false;
		for(int i=0;i<chars.length;i++)
		{
			if(chars[i]=='(')
			{
				nawias=true;
				break;
			}
			if(chars[i]!='_'&&!Character.isLetter(chars[i])&&!Character.isDigit(chars[i]))
			{
				return null;
			}
			counter++;
			word += chars[i];
		}
		if(!isCorrectId(word))
		{ return null; }
		if(nawias)
		{
			if(chars[chars.length-1]!=')')
			{
				return null;
			}
			String word2=link.substring(counter, chars.length-1);
			for(int i=counter;i<chars.length-1;i++)
			{
				if(!Character.isDigit(chars[i]))
				{
					return null;
				}
			}
			number=Integer.parseInt(word2);
		}
		return new Link(word.toLowerCase(), number);
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		//TODO?
		retStr+=link;		
		return retStr;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}

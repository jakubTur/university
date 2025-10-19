package dsaa.lab03;

import java.util.Scanner;

public class Document
	{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan)
	{
		this.name=name;
		link=new TwoWayUnorderedListWithHeadAndTail<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = "";
		while(!line.equals("eod"))
		{
			line = scan.nextLine();
			String[] words = line.split(" ");
            for (String word : words)
			{
                String[] symbols = word.split("");
                String linkstring = "";
                if (symbols.length > 5)
				{
                    linkstring = (symbols[0] + symbols[1] + symbols[2] + symbols[3] + symbols[4]).toLowerCase();
                }
                if (linkstring.equals("link="))
				{
                    if (correctLink(word.substring(5, symbols.length)))
					{
                        link.add(new Link((word.substring(5, symbols.length).toLowerCase())));
                    }
                }
            }
		}
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
		char[] chars = link.toCharArray();
		if(!Character.isLetter(chars[0]))
		{
			return false;
		}
		else {
			for(int i =1; i<chars.length;i++)
			{
				if(chars[i]!='_'&&!Character.isLetter(chars[i])&&!Character.isDigit(chars[i]))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String linklist = "Document: "+name;
		for(int i=0; i<link.size();i++)
		{
			linklist += "\n" + link.get(i).ref;
		}
		return linklist;
	}
	
	public String toStringReverse() {
		String retStr="Document: "+name;
		return retStr+link.toStringReverse();
	}

}

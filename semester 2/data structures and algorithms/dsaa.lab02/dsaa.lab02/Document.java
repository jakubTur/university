package dsaa.lab02;

import java.util.Scanner;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links = new OneWayLinkedList<Link>();
	public Document(String name, Scanner scan) {
			// DONE
			load(scan);
			this.name = name;
		}
	public void load(Scanner scan) {
		// DONE
		String line = "";
		while(!line.equals("eod"))
		{
			line = scan.nextLine();
			String[] words = line.split(" ");
            for (String word : words)
			{
                String[] symbols = word.split("");
                String link = "";
                if (symbols.length > 5)
				{
                    link = (symbols[0] + symbols[1] + symbols[2] + symbols[3] + symbols[4]).toLowerCase();
                }
                if (link.equals("link="))
				{
                    if (correctLink(word.substring(5, symbols.length)))
					{
                        links.add(new Link((word.substring(5, symbols.length).toLowerCase())));
                    }
                }
            }
		}
	}
	// accepted only small letters, capital letter, digits nad '_' (but not on the beginning)
	private static boolean correctLink(String link)	 {
		char[] chars = link.toCharArray();
		if(!Character.isLetter(chars[0]))
		{
			return false;
		}
		else
		{
			for(int i = 1; i < chars.length; i++)
			{
				if(chars[i] != '_' && !Character.isLetter(chars[i]) && !Character.isDigit(chars[i]))
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString()
	{
		String linklist = "Document: " + name;
		for(int i = 0; i < links.size(); i++)
		{
			linklist += "\n" + links.get(i).ref;
		}
//		if(!links.isEmpty()) { linklist = (linklist  + links.get(links.size()-1).ref); }
		return linklist;
	}

}

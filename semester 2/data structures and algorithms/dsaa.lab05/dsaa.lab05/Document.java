package dsaa.lab05;

import java.util.ListIterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = "";
		while(!line.equals("eod"))
		{
			line = scan.nextLine();
			String[] words = line.split(" ");
			for(int i = 0;i<words.length;i++)
			{
				String[] symbols = words[i].split("");
				String linkstring = "";
				if(symbols.length>5) { linkstring = (symbols[0]+symbols[1]+symbols[2]+symbols[3]+symbols[4]).toLowerCase(); }
				if(linkstring.equals("link="))
				{
					Link newlink = createLink(words[i].substring(5, symbols.length));
					if(newlink!=null)
					{
						link.add(newlink);
					}
				}
			}
		}
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	

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
	public static Link createLink(String link) {
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
		int j = 0;
		for(int i=0; i<link.size();i++) {
			j++;
			retStr += link.get(i).toString();
			if(j==10)
			{
				retStr+="\n";
				j=0;
			}
			else
			{
				retStr+=" ";
			}
		}
		return retStr;
	}

	public String toStringReverse() {
		String retStr="Document: "+name+"\n";
		ListIterator<Link> iter=link.listIterator();
		while(iter.hasNext()) {
			iter.next();
		}
		iter.next();
		int i = link.size();
		int j =0;
		while(iter.hasPrevious()||i==1)
		{
			j++;
			i--;
			retStr += link.get(i).toString();
			if(j==10)
			{
				retStr+="\n";
				j=0;
			}
			else
			{
				retStr+=" ";
			}

			iter.previous();
		}
		return retStr;
	}
	public int[] getWeights() {
		int[] ret = new int[link.size()];
		for(int i=0;i<link.size();i++)
		{
			ret[i]=link.get(i).weight;
		}
		return ret;
	}

	public static void showArray(int[] arr) {
		String show="";
		for(int i=0;i<=arr.length-2;i++)
		{
			show+=arr[i]+" ";
		}
		show+=arr[arr.length-1];
		System.out.println(show);
	}

	void bubbleSort(int[] arr) {
		showArray(arr);
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
			showArray(arr);
		}
	}

	public void insertSort(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			int key = arr[i];
			int j = i + 1;
			while (j <= arr.length - 1 && arr[j] < key) {
				arr[j - 1] = arr[j];
				j = j + 1;
			}
			arr[j - 1] = key;
			showArray(arr);
		}
	}
	public void selectSort(int[] arr) {
		showArray(arr);
		for(int i=0;i<arr.length-1;i++)
		{
			int high = arr[0];
			int index=0;
			for(int j=0;j<arr.length-i;j++)
			{
				if(arr[j]>high) { high=arr[j]; index=j;}
			}
			arr[index]=arr[arr.length-1-i];
			arr[arr.length-1-i]=high;
			showArray(arr);
		}
	}

}

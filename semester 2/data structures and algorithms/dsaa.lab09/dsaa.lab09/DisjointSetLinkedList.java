package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
	}
	
	@Override
	public void makeSet(int item) {
		arr[item]=new Element();
		arr[item].next=NULL;
		arr[item].representant=item; //head
		arr[item].length=1;          //size
		arr[item].last=item;         //tail

	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int a = findSet(itemA);
		int b = findSet(itemB);
		if(a==b) { return false; }
		int newsize=arr[a].length+arr[b].length;
		Element temp=arr[a];
		Element temp2 = arr[b];
		if(temp.length<temp2.length)
		{
			int t = a;
			a=b;
			b=t;
			temp=arr[a];
			temp2=arr[b];
		}
		arr[temp.last].next=b;
		temp.length=newsize;
		temp.last=temp2.last;
		while(b!=NULL)
		{
			arr[b].representant=a;
			b=arr[b].next;
		}
		return true;
	}
	@Override
	public String toString() {
		String ret = "Disjoint sets as linked list:\n";
		for (int i=0;i<arr.length;i++)
		{
			int a=i;
			if(arr[a].representant==a)
			{
				int last=arr[a].last;
				while(a!=NULL)
				{
					ret+=a;
					if(a!=last)
					{
						ret+=", ";
						a = arr[a].next;
					}
				}
				ret += "\n";
			}
		}
		return ret.substring(0, ret.length() - 1);
	}

}

package dsaa.lab10;

public class DisjointSetForest implements DisjointSetDataStructure {
	
	class Element{
		int rank;
		int parent;
	}

	Element []arr;
	
	public DisjointSetForest(int size) {
		arr=new Element[size];
	}
	
	@Override
	public void makeSet(int item) {
		arr[item] = new Element();
		arr[item].parent = item;
		arr[item].rank = 0;
	}

	@Override
	public int findSet(int item) {
		if (arr[item].parent != item)
		{
			arr[item].parent = findSet(arr[item].parent);
		}
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int a=findSet(itemA);
		int b=findSet(itemB);
		if(a==b) { return false; }
		if(arr[a].rank>arr[b].rank)
		{
			arr[b].parent=a;
		}
		else if(arr[a].rank<arr[b].rank)
		{
			arr[a].parent=b;
		}
		else
		{
			arr[a].parent=b;
			arr[b].rank+=1;
		}
		return true;
	}

	
	@Override
	public String toString() {
		String ret="Disjoint sets as forest:"+"\n";
		for (int i=0;i<arr.length;i++)
		{
			ret+=(i+" -> "+arr[i].parent+"\n");
		}
		return ret.substring(0,ret.length()-1);
	}

	@Override
	public int countSets() {
		int counter = 0;
		for (int i=0;i<arr.length;i++)
		{
			if (arr[i].parent==i)
			{
				counter++;
			}
		}
		return counter;
	}
}

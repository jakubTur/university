package dsaa.lab07;

import java.util.LinkedList;

public class HashTable{
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}
	int elnumber=0;
	public HashTable(int initCapacity, double maxLF) {
		this.arr = new LinkedList[initCapacity];
		this.size = initCapacity;
		this.maxLoadFactor=maxLF;
	}
	private double loadFactor(int size, int noOfElements) {
		return ((double) noOfElements) / size;
	}
	public boolean add(Object elem) {
		if(elem==null) { return false; }
		if(loadFactor(arr.length, elnumber+1)>maxLoadFactor)
		{
			doubleArray();
		}
		int index = elem.hashCode() % arr.length;
		if(arr[index] == null)
		{
			arr[index] = new LinkedList();
			arr[index].add(elem);
		} else {
			arr[index].add(elem);
		}
		elnumber++;
		return true;
	}
	private void doubleArray()
	{
		LinkedList temp = new LinkedList();
		for (LinkedList list : arr) {
			if (list != null){
				for (Object elem : list)
				{
					temp.add(elem);
				}
			}
		}
		size = size*2;
		elnumber = 0;
		arr = new LinkedList[size];
		for (Object elem : temp)
		{
			add(elem);
		}
	}


	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++)
		{
			ret+=i+":";
			LinkedList list = arr[i];
			if(list!=null)
			{
				for(Object element : list)
				{
					IWithName el = (IWithName) element;
					ret += " " + el.getName() + ",";
				}
				ret=ret.substring(0, ret.length()-1);
			}
			ret+="\n";
		}
		return ret;
	}

	public Object get(Object toFind) {
		LinkedList list = arr[toFind.hashCode() % arr.length];
		if (list == null) {
			return null;
		}
		for (Object elem : list) {
			if (elem.equals(toFind))
				return elem;
		}
		return null;
	}

}


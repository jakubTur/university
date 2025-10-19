package dsaa.lab10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

public class Graph {
	int arr[][];
	//TODO? Collection to map Document to index of vertex 
	// You can change it
	HashMap<String,Integer> name2Int;
	@SuppressWarnings("unchecked")
	//TODO? Collection to map index of vertex to Document
	// You can change it
	Entry<String, Document>[] arrDoc=(Map.Entry<String, Document>[])new Map.Entry[0];

	// The argument type depend on a selected collection in the Main class
	public Graph(SortedMap<String,Document> internet){
		int size=internet.size();
		arr=new int[size][size];
		name2Int = new HashMap<>();
		arrDoc = new Map.Entry[size];
		int index = 0;
		for (Map.Entry<String, Document> entry : internet.entrySet())
		{
			String name = entry.getKey();
			name2Int.put(name, index);
			arrDoc[index] = entry;
			index++;
		}
		for (Map.Entry<String, Document> entry : internet.entrySet())
		{
			Document document = entry.getValue();
			int x = name2Int.get(document.name);
			for (Link link : document.link.values())
			{
				int y = name2Int.get(link.ref);
				arr[x][y] = link.weight;
			}
		}
	}

	public String bfs(String start)
	{
		Integer first = name2Int.get(start);
		if(first==null) { return null; }
		String ret = "";
		boolean[] visited = new boolean[arr.length];
		visited[first] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(first);
		while(!queue.isEmpty())
		{
			int vertex = queue.poll();
			ret+=arrDoc[vertex].getKey()+", ";
			for(int i=0;i<arr.length;i++)
			{
				if(arr[vertex][i]>0&&!visited[i])
				{
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		return ret.substring(0, ret.length() - 2);
	}

	public String dfs(String start) {
		Integer first = name2Int.get(start);
		if(first==null) { return null; }
		StringBuilder ret = new StringBuilder();
		boolean[] visited = new boolean[arr.length];
		loop(first, visited, ret);
		return ret.substring(0, ret.length() - 2);
	}
	private void loop(int vertex, boolean[] visited, StringBuilder ret) {
		visited[vertex] = true;
		ret.append(arrDoc[vertex].getKey()).append(", ");
		for(int i=0;i<arr.length;i++)
		{
			if(arr[vertex][i]>0&&!visited[i])
			{
				loop(i, visited, ret);
			}
		}
	}
	public int connectedComponents() {
		DisjointSetForest dsf = new DisjointSetForest(arr.length);
		for(int i=0;i<arr.length;i++)
		{
			dsf.makeSet(i);
		}
		for(int i=0;i<arr.length; i++)
		{
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i][j]>0)
				{
					dsf.union(i, j);
				}
			}
		}
		return dsf.countSets();
	}
}

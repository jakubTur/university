package dsaa.lab08;

import java.util.NoSuchElementException;

public class BST<T> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}
	private Node root=null;
	int size=0;
	public BST() {
	}

	public T getElement(T toFind)
	{
		Node temp=root;
		int comp=-10000;
		while (comp!=0)
		{
			comp = ((Comparable) temp.value).compareTo(toFind);
			if (comp < 0)
			{
				temp = temp.right;
			}
			if (comp > 0)
			{
				temp = temp.left;
			}
			if(temp==null) { return null; }
		}
		return temp.value;
	}

	public T successor(T elem)
	{
		if(getElement(elem)==null) {return null;}
		Node temp=root;
		while(temp.value!=elem)
		{
			int comp = ((Comparable) temp.value).compareTo(elem);
			if (comp == 0)
			{
				break;
			}
			if (comp < 0)
			{
				temp = temp.right;
			}
			if (comp > 0)
			{
				temp = temp.left;
			}
		}
		if (temp.right != null)
		{
			temp=temp.right;
			while (temp.left != null)
			{
				temp = temp.left;
			}
			return temp.value;
		}
		else
		{
			Node temp2=temp.parent;
			while(temp2!=null&&temp2.parent!=null&&temp!=temp2.right)
			{
				temp=temp2;
				temp2=temp2.parent;
			}
			if(temp2==null) { return null; }
			return temp2.value;
		}
	}
	private String inOrderWalk(Node x, String res){
		if(x != null){
			res = inOrderWalk(x.left, res);
			res += x.value + ", ";
			res = inOrderWalk(x.right, res);
		}
		return res;
	}
	private String preOrderWalk(Node x, String res){
		if(x != null){
			res += x.value + ", ";
			res = preOrderWalk(x.left, res);
			res = preOrderWalk(x.right, res);
		}
		return res;
	}
	private String postOrderWalk(Node x, String res){
		if(x != null){
			res = postOrderWalk(x.left, res);
			res = postOrderWalk(x.right, res);
			res += x.value + ", ";
		}
		return res;
	}
	public String toStringInOrder() {
		String response = inOrderWalk(root, "");
		if(response != ""){
			return response.substring(0, response.length() -2);
		}
		return "";
	}

	public String toStringPreOrder() {
		String response = preOrderWalk(root, "");
		if(response != ""){
			return response.substring(0, response.length() -2);
		}
		return "";
	}

	public String toStringPostOrder() {
		String response = postOrderWalk(root, "");
		if(response != ""){
			return response.substring(0, response.length() -2);
		}
		return "";
	}


	public boolean add(T elem) {
		if(size()==0)
		{
			root=new Node(elem);
			size++;
			return true;
		}
		size++;
		Node temp = root;
		int comp=-100000;
		while(comp!=0)
		{
			comp= ((Comparable) temp.value).compareTo(elem);
			if(comp<0)
			{
				if(temp.right==null)
				{
					temp.right=new Node(elem, null,null,temp);
					return true;
				}
				else
				{
					temp=temp.right;
				}
			}
			if(comp>0)
			{
				if(temp.left==null)
				{
					temp.left=new Node(elem, null,null,temp);
					return true;
				}
				else
				{
					temp=temp.left;
				}
			}
		}
		return false;
	}


	public T remove(T value) {
		Node temp = root;
		boolean left=false;
		if(root==null||getElement(value)==null) { return null; }
		size--;
		int comp=-100000;
		while(comp!=0)
		{
			comp=((Comparable) temp.value).compareTo(value);
			if(comp<0)
			{
				temp=temp.right;
				left=false;
			}
			if(comp>0)
			{
				temp=temp.left;
				left=true;
			}
		}
		if(temp.left==null&&temp.right==null)
		{
			if (root == temp)
			{
				root=null;
				return temp.value;
			}
			if(left)
			{
				temp.parent.left=null;
			}
			else
			{
				temp.parent.right=null;
			}
			return temp.value;
		}
		if(temp.left!=null&&temp.right!=null)
		{
			Node temp2=root;
			while(successor(temp.value)!=temp2.value)
			{

				comp= ((Comparable) temp2.value).compareTo(successor(temp.value));
				if(comp<0)
				{
					temp2=temp2.right;

				}
				if(comp>0)
				{
					temp2=temp2.left;
				}
				if(comp==0) { break; }
			}
			if(temp2.left!=null)
			{
				temp2.left.parent=temp2.parent;
				temp2.parent.left=temp2.left;

			}
			else if(temp2.right!=null)
			{
				temp2.right.parent=temp2.parent;
				temp2.parent.left=temp2.right;
			}
			else
			{
				temp2.parent.left=null;
			}
				T ret=temp.value;
			temp.value=temp2.value;
			return ret;
		}
		if(temp.left!=null||temp.right!=null)
		{
			if(temp!=root)
			{
				if (temp.left != null) {
					temp.left.parent = temp.parent;
					if(left)
					{
						temp.parent.left = temp.left;
					}
					else
					{
						temp.parent.right=temp.left;
					}

				}
				else
				{
					temp.right.parent = temp.parent;
					if(left)
					{
						temp.parent.left = temp.right;
					}
					else
					{
						temp.parent.right=temp.right;
					}

				}
			}
			else
			{
				if(temp.left!=null) { temp.left.parent=null; root=temp.left;}
				if(temp.right!=null) { temp.right.parent=null; root=temp.right;}
			}
			return temp.value;
		}
		return temp.value;
	}

	public void clear() {
		size=0;
		root=null;
	}

	public int size() {
		return size;
	}

}

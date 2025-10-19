package dsaa.lab07;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object=e;
		}
		public Element(E e, Element next, Element prev) {
			this.object=e;
			this.next=next;
			this.prev=prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			elem.prev = this;
			this.next.prev = elem;
			elem.next = this.next;
			this.next = elem;
		}
		// assert it is NOT a sentinel
		public void remove() {
			this.prev.next=this.next;
			this.next.prev=this.prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}


	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E>{
		//TODO
		public InnerIterator() {
			//TODO
		}
		@Override
		public boolean hasNext() {
			//TODO
			return false;
		}

		@Override
		public E next() {
			//TODO
			return null;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		Element el;
		public InnerListIterator() {
			el = new Element(null, sentinel.next, sentinel);
		}
		@Override
		public boolean hasNext() {
			return el.next!=null&&el.next!=sentinel;
		}

		@Override
		public E next() {
			el = el.next; return el.object;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasPrevious() {
			return el.prev!=sentinel&&el.prev!=null;
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public E previous() {
			el=el.prev; return el.object;
		}
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		this.sentinel = new Element(null);
		this.sentinel.next = sentinel;
		this.sentinel.prev = sentinel;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		Element x = sentinel.next;
		Element addon = new Element(e);
		int size = this.size();
		if(size == 0)
		{
			sentinel.addAfter(addon);
		}
		else
		{
			while( ((Comparable) x.object).compareTo(e) <= 0)
			{
				if(x.next != sentinel)
				{
					x = x.next;
				}
				else
				{
					x.addAfter(addon);
					return true;
				}
			}
			x = x.prev;
			x.addAfter(addon);
		}

		return true;
	}

	private Element getElement(int index) {
		if(index<0||index>=this.size())
		{
			throw new NoSuchElementException();
		}
		// DONE Auto-generated method stub
		Element x = sentinel;
		for(int i = 0;i<=index;i++)
		{
			x=x.next;
		}
		return x;
	}

	private Element getElement(E obj) {
		if(!contains(obj))
		{
			throw new NoSuchElementException();
		}
		// DONE Auto-generated method stub
		Element x = sentinel;
		int index=indexOf(obj);
		for(int i = 0;i<index;i++)
		{
			x=x.next;
		}
		return x;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		sentinel.prev=sentinel;
		sentinel.next=sentinel;
	}

	@Override
	public boolean contains(E element) {
		Element x = sentinel;
		if(!(x.next==sentinel))
		{
			while(!element.equals(x.next.object))
			{
				x = x.next;
				if(x.next == null)
				{
					return false;
				}
			}
		}
		else{ return false; }
		return true;
	}

	@Override
	public E get(int index) {
		if(index<0||index>=this.size())
		{
			throw new NoSuchElementException();
		}
		Element x = sentinel;
		for(int i = 0;i<=index;i++)
		{
			x=x.next;
		}
		return x.object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		Element x = sentinel;
		int i = -1;
		if(!contains(element)) { return i; }
		do{
			if(x.next != null){
				i++;
				x = x.next;
			} else {
				return -1;
			}
		}while(!x.object.equals(element));
		return i;
	}

	@Override
	public boolean isEmpty() {
		//TODO
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		if(index<0||index>=size())
		{
			throw new NoSuchElementException();
		}
		Element x = sentinel;
		for(int i = 0;i<index;i++)
		{
			x=x.next;
		}
		Element temp = x.next;
		x.next.remove();
		return temp.object;
	}

	@Override
	public boolean remove(E e) {
		if(!contains(e)) { return false; }
		Element x = sentinel;
		while(x.next!=sentinel)
		{
			if(x.next.object.equals(e))
			{
				x.next.remove();
				return true;
			}
			x = x.next;
		}
		return true;
	}

	@Override
	public int size() {
		int size = 0;
		Element x = sentinel;
		while(x.next!=sentinel)
		{
			x=x.next;
			size++;
		}
		return size;
	}

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		if(!(other==this) && other.size() != 0)
		{
			Element x = this.sentinel.next;
			Element addon = other.sentinel.next;
			while (addon != other.sentinel)
			{
				if (x != sentinel && ((Comparable) x.object).compareTo(addon.object) <= 0)
				{
					x = x.next;
					continue;
				}
				Element temp = addon.next;
				x.prev.addAfter(addon);
				addon = temp;
			}
			other.clear();
		}
	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		if(!isEmpty()&&contains(e))
		{
			Element x = sentinel.next;
			while(x.next!=sentinel)
			{
				if(((Comparable) x.object).compareTo(e)==0)
				{
					this.remove(e);
				}
				x=x.next;
			}
		}
	}

}


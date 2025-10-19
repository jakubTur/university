package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element
	{
		public Element(E e) {
			this.object = e;
		}
		public Element(E e, Element next, Element prev)
		{
			this.object = e;
			this.next = next;
			this.prev = prev;
		}
		E object;
		Element next = null;
		Element prev = null;
	}
	
	Element head;
	Element tail;
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;
		// TODO maybe more fields....
		
		public InnerIterator() {
			pos = head;
		}
		@Override
		public boolean hasNext() {
			return pos.next != null;
		}
		
		@Override
		public E next() {
			pos = pos.next;
			return null;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p = head;
		// TODO maybe more fields....

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {

			return p.next != null;
		}

		@Override
		public boolean hasPrevious() {
			return p.prev!=null;
		}

		@Override
		public E next() {
			p = p.next;
			return null;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			p = p.prev;
			return null;
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
		public void set(E e) {
			p = new Element(e);
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
		// make a head and a tail	
		head = null;
		tail = null;
	}
	
	@Override
	public boolean add(E e) {
		Element addon = new Element(e);
		if(head == null)
		{
			head = addon;
			tail = addon;
		}
		else
		{
			Element temp = tail;
			tail = addon;
			temp.next = tail;
			tail.prev = temp;
		}
		return true;
	}
	@Override
	public void remodd()
	{
		int size = this.size();
		Element x = head;
		if(size != 0 && size != 1)
		{
			if(size == 2)
			{
				tail = head;
				tail.prev = null;
				head.next = null;
			}
			else
			{
				while(x.next.object != null)
				{
					x.next = x.next.next;
					if(x.next != null)
					{
						x.next.prev = x;
					}
					x = x.next;
					if(x == null)
					{
						tail = x.prev;
						break;
					}
					if(x.next == null)
					{
						tail = x;
						break;
					}
				}
			}
		}
	}
	@Override
	public void add(int index, E element) {
		int size = this.size();
		if(index < 0 || index > size)
		{
			throw new NoSuchElementException();
		}
		Element addon = new Element(element);
		if(index == 0)
		{
			Element temp = head;
			head = addon;
			if(tail == null) { tail=head; }
			else
			{
				head.next = temp;
				if(size == 1)
				{
					tail = temp; tail.prev = head;
				}
			}
		}
		else if(size == index)
		{
			add(element);
		}
		else
		{
			Element x = head;
			for(int i = 0; i < index-1; i++)
			{
				x = x.next;
			}
			if(x != null)
			{
				Element temp;
				temp = x.next;
				x.next = addon;
				addon.prev = x;
				addon.next = temp;
				temp.prev = addon;
			}
			else
			{ throw new NoSuchElementException(); }
		}
	}
	@Override
	public void clear()
	{
		head = null;
		tail = null;
	}

	@Override
	public boolean contains(E element) {
		if(head == null)
		{
			return false;
		}
		Element x = head;
		while(!x.object.equals(element))
		{
			if(x.next == null)
			{
				return false;
			}
			x = x.next;
		}
		return true;
	}

	@Override
	public E get(int index) {
		if(index<0||index>=size())
		{
			throw new NoSuchElementException();
		}
		Element x = head;
		for(int i = 0;i<index;i++)
		{
			x=x.next;
		}
		return x.object;
	}

	@Override
	public E set(int index, E element) {
		if(index < 0 || index > size())
		{
			throw new NoSuchElementException();
		}
		Element x = head;
		for(int i = 0; i < index; i++)
		{
			x = x.next;
		}
		E temp = x.object;
		x.object = element;
		return temp;
	}

	@Override
	public int indexOf(E element) {
		Element start = head;
		int i = 0;
		while(start != null)
		{
			if(start.object.equals(element)) { return i; }
			i++;
			start = start.next;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return ((head == null)&&(tail==null));
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		if(index < 0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		Element x = head;
		Element ret;
		if(index == 0)
		{
			ret = head; head = x.next; head.prev = null; return ret.object;
		}
		if(index == this.size() - 1)
		{
			ret = tail; tail = tail.prev; tail.next = null; return ret.object;
		}
		for(int i = 0;i < index; i++)
		{
			x = x.next;
		}
		ret = x;
		Element pre = x.prev;
		x = x.next;
		x.prev = pre;
		pre.next = x;
		return ret.object;
	}

	@Override
	public boolean remove(E e) {
		if(!contains(e))
		{
			return false;
		}
		Element x = head;
		while(!x.object.equals(e))
		{
			x = x.next;
		}
		if(head == x && tail == x) { tail = null; head = null; }
		else if(x == head) { head = x.next; }
		else if(x == tail) { tail = tail.prev; tail.next = null; }
		else
		{
			Element pre = x.prev;
			x = x.next;
			x.prev = pre;
			pre.next = x;
		}
		return true;
	}

	@Override
	public int size() {
		Element x = head;
		int size = 0;
		while(x != null)
		{
			size++;
			x = x.next;
		}
		return size;
	}
	public String toStringReverse() {
		ListIterator<E> iter = new InnerListIterator();
		String retStr = "";
		if(isEmpty())
		{
			return retStr;
		}
		while(iter.hasNext())
		{
			iter.next();
		}
		if(this.isEmpty())
		{
			return retStr;
		}
		//TODO use reverse direction of the iterator
		Element x = tail;
		while(iter.hasPrevious() && x != null)
		{
			Link item = (Link) x.object;
			if (item != null)
			{
				retStr += "\n" + item.ref;
			}
			x = x.prev;
		}
		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other)
	{
		if(other.head != head)
		{
			if (!other.isEmpty())
			{
				if (this.isEmpty())
				{
					this.head = other.head;
					this.tail = other.tail;
					other.clear();
				}
				else
				{
					Element temp1 = new Element(this.tail.object, other.head, this.tail.prev);
                    temp1.next = new Element(other.head.object, other.head.next, this.tail);
					this.tail.prev.next = temp1;
					other.head.prev = temp1;
					this.tail = new Element(other.tail.object, null, other.tail.prev);
					other.clear();
				}
			}
		}
	}
}


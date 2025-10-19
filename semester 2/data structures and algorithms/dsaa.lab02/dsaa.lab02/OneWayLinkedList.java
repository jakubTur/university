package dsaa.lab02;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object = e;
		}
		E object;
		Element next = null;
	}

	Element sentinel;

	private class InnerIterator implements Iterator<E>{
		Element el;

		public InnerIterator() {

			el = sentinel;
		}
		@Override
		public boolean hasNext() {

            return el.next != null;
        }

		@Override
		public E next() {

			return el.next.object;
		}
	}

	public OneWayLinkedList() {
		// make a sentinel
		this.sentinel = new Element(null);
		// DONE
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
	public boolean add(E e)
	{
		// DONE Auto-generated method stub
		Element x = new Element(e);
		Element y = sentinel;
		while(y.next != null){
			y = y.next;
		}
		y.next = x;
		return true;
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {
		// DONE Auto-generated method stub
		if (index < 0 || index >= size()) {
			throw new NoSuchElementException();
		}
		else
		{
			Element x = sentinel;
			Element y = new Element(element);
			for (int i = 0; i != index; i++) {
				x = x.next;
			}
			if(x != null) {
				Element temp = x.next;
				x.next = y;
				x.next.next = temp;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
	}


	@Override
	public void clear() {
		// DONE Auto-generated method stub
		sentinel.next = null;
	}

	@Override
	public boolean contains(E element) {
		// DONE Auto-generated method stub
		Element x = sentinel;
		if(x.next != null)
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
		else { return false; }
		return true;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
		if(index < 0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		// DONE Auto-generated method stub
		Element x = sentinel;
		for(int i = 0; i <= index; i++)
		{
			x = x.next;
		}
		return x.object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
		// DONE Auto-generated method stub
		if(index < 0 || index>=size())
		{
			throw new NoSuchElementException();
		}
		Element x = sentinel;
		for(int i = 0; i <= index; i++)
		{
			x = x.next;
		}
		E temp = x.object;
		x.object = element;
		return temp;
	}
	@Override
	public int indexOf(E element) {
		// DONE
		Element x = sentinel;
		int i = -1;
		if(!contains(element)) { return i; }
		while(!x.object.equals(element))
		{
			if(x.next != null)
			{
				i++;
				x = x.next;
			}
			else
			{
				return -1;
			}
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		// DONE Auto-generated method stub
        return sentinel.next == null;
    }

	@Override
	public E remove(int index) throws NoSuchElementException {
		// DONE Auto-genera-ted method stub
		if(index<0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		Element x = sentinel;
		for(int i = 0; i < index; i++)
		{
			x = x.next;
		}
		Element temp = x.next;
		x.next = x.next.next;
		return temp.object;
	}

	@Override
	public boolean remove(E e) {
		// DONE Auto-generated method stub
		if(!contains(e)) { return false; }
		Element x = sentinel;
		while(x.next != null)
		{
			if(x.next.object.equals(e))
			{
				if(x.next.next == null) { x.next = null; }
				else { x.next = x.next.next; }
				return true;
			}
			x = x.next;
		}
		return true;
	}
	@Override
	public int size() {
		// DONE Auto-generated method stub
		int size = 0;
		Element x = sentinel;
		while(x.next != null)
		{
			x = x.next;
			size++;
		}
		return size;
	}

}


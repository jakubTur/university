package dsaa.lab02;

public class Link{
	public String ref;
	public Link(String ref) {
		this.ref = ref;
	}
	@Override
	public boolean equals(Object element) {
		Link link = (Link) element;
		return this.ref.equals(link.ref);
	}
	// in the future there will be more fields
}

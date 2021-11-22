package structures.graph;

public class Connects<U, V> {

	private U first;
	private V second;


	public Connects(U first, V second) {
		super();
		this.first = first;
		this.second = second;
	}


	public U getFirst() {
		return first;
	}


	public void setFirst(U first) {
		this.first = first;
	}


	public V getSecond() {
		return second;
	}


	public void setSecond(V second) {
		this.second = second;
	}

}

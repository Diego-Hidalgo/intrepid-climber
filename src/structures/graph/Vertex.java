package structures.graph;

public class Vertex<E extends Comparable<E>> implements VertexInterface<E> {
	
	private E value;
	private int distance;
	
	
	public Vertex(E value) {
		this.value = value;
		distance = 0;
	}

	@Override
	public E getValue() {
		return value;
	}

	
	@Override
	public void setValue(E value) {
		this.value = value;
	}

	@Override
	public int getDistance() {
		return distance;
	}

	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}

}

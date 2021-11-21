package structures.graph;

public interface VertexInterface<E extends Comparable<E>> {
	
	public E getValue();
	public void setValue(E value);
	public int getDistance();
	public void setDistance(int distance);
}

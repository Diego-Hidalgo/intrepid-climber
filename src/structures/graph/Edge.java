package structures.graph;

public class Edge<E extends Comparable<E>>{
	
	private int weight;
	private Vertex<E> vertex;
	
	public Edge(int weight, Vertex<E> vertex) {
		super();
		this.weight = weight;
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex<E> getVertex() {
		return vertex;
	}

	public void setVertex(Vertex<E> vertex) {
		this.vertex = vertex;
	}
	
	
}

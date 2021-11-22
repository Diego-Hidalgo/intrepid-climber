package structures.graph;

public class Vertex<E>  {

	private E value;
	private int id;
	private int distance;
	private Colors color;
	private Connects<Integer, Integer> timestamps;
	private Vertex<E> predecessor;


	public Vertex(E value, int id) {
		this.value = value;
		this.id = id;
		this.distance = 0;
		color = null;
		timestamps = new Connects<Integer, Integer>(null, null);
		predecessor = null;
	}


	public E getValue() {
		return value;
	}


	public void setValue(E value) {
		this.value = value;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public Colors getColor() {
		return color;
	}


	public void setColor(Colors color) {
		this.color = color;
	}


	public Connects<Integer, Integer> getTimestamps() {
		return timestamps;
	}


	public void setTimestamps(Connects<Integer, Integer> timestamps) {
		this.timestamps = timestamps;
	}


	public Vertex<E> getPredecessor() {
		return predecessor;
	}


	public void setPredecessor(Vertex<E> predecessor) {
		this.predecessor = predecessor;
	}

}

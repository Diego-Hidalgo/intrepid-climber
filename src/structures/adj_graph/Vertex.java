package structures.adj_graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> implements Comparable<Vertex<T>> {

	private T value;
	private List<Vertex<T>> adjacent;
	private List<Integer> weights;
	private int key;
	private T predecessor;
	private Color color;
	private TimeStamp timeStamp;

	public Vertex(T value) {
		this.value = value;
		adjacent = new ArrayList<>();
		weights = new ArrayList<>();
		predecessor = null;
		color = Color.WHITE;
		timeStamp = new TimeStamp();
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Vertex<T>> getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(List<Vertex<T>> adjacent) {
		this.adjacent = adjacent;
	}

	public List<Integer> getWeights() {
		return weights;
	}

	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public T getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(T predecessor) {
		this.predecessor = predecessor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int start() {
		return timeStamp.getStart();
	}

	public void setStart(int start) {
		timeStamp.setStart(start);
	}

	public int end() {
		return timeStamp.getEnd();
	}

	public void setEnd(int end) {
		timeStamp.setEnd(end);
	}

	public void link(Vertex<T> v, int w) {
		adjacent.add(v);
		weights.add(w);
	}

	public boolean contains(Vertex<T> v) {
		return adjacent.contains(v);
	}

	public int weight(Vertex<T> v) {
		for(int i = 0; i < adjacent.size(); i ++) {
			if(adjacent.get(i) == v)
				return weights.get(i);
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public int compareTo(Vertex<T> o) {
		return Integer.compare(key, o.key);
	}

	@Override
	public String toString() {
		String msg = "";
		for(int i = 0; i < adjacent.size(); i ++) {
			Vertex<T> adj = adjacent.get(i);
			int w = weights.get(i);
			msg += value + " -> " + adj.value() + " : " + w + "\n";
		}
		return msg;
	}

}

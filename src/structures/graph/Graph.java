package structures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<E> {

	private ArrayList<Vertex<E>> vertex;
	private  ArrayList <ArrayList<Integer>> edges;

	public Graph() {
		super();
		setVertex(new ArrayList<Vertex<E>>());
		edges = new ArrayList<ArrayList<Integer>>();
	}

	public ArrayList<Vertex<E>> getVertex() {
		return vertex;
	}

	public void setVertex(ArrayList<Vertex<E>> vertex) {
		this.vertex = vertex;
	}

	public ArrayList <ArrayList<Integer>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList <ArrayList<Integer>> edges) {
		this.edges = edges;
	}

	public boolean containsValue(E e) {
		boolean found = false;
		for (int i = 0; i < vertex.size() && !found; i++) {
			if(vertex.get(i).getValue().equals(e)) {
				found = true;
			}
		}
		return found;
	}

	//@Override
	public void insertVertex(E value) {
		//FALTA
	}

	//@Override
	public void insertEdge(Vertex<E> vertex1, Vertex<E> vertex2, int weight) {
		//FALTA
	}

	//@Override
	public void BFS(Vertex<E> s) {
		for (int i = 0; i < getVertex().size(); i++) {
			Vertex<E> u = getVertex().get(i);
			u.setColor(Colors.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setPredecessor(null);
		}

		s.setColor(Colors.GRAY);
		s.setDistance(0);
		s.setPredecessor(null);

		Queue<Vertex<E>> q = new LinkedList<Vertex<E>>();
		q.add(s);

		while(!q.isEmpty()) {
			Vertex<E> u = q.poll();
			ArrayList<Integer> edge = edges.get(u.getId());
			for (int i = 0; i < edge.size(); i++) {
				if(edge.get(i) != Integer.MAX_VALUE && i != u.getId()) {
					Vertex<E> v = getVertex().get(i);
					if(v.getColor() == Colors.WHITE) {
						v.setColor(Colors.GRAY);
						v.setDistance(u.getDistance()+1);
						v.setPredecessor(u);
						q.add(v);
					}
				}
			}
			
			u.setColor(Colors.BLACK);
		}
	}

	//@Override
	public void DFS() {

	}

}

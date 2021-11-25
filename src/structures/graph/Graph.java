package structures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<E> implements GraphInterface<E> {

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

	@Override
	public void insertVertex(E value) {
		Vertex<E> newVertex = new Vertex<E>(value, getVertex().size());
		getVertex().add(newVertex);
		edges.add(new ArrayList<Integer>());

		for (int i = 0; i < edges.size()-1; i++) {
			edges.get(i).add(Integer.MAX_VALUE);
			edges.get(edges.size()-1).add(Integer.MAX_VALUE);
		}
		edges.get(edges.size()-1).add(0);
	}

	@Override
	public void deleteVertex(Vertex<E> v) {
		vertex.remove(v.getId());
		edges.remove(v.getId());
		for (int i = 0; i < vertex.size(); i++) {
			if(vertex.get(i).getId() > v.getId()) {
				vertex.get(i).setId(vertex.get(i).getId() - 1);
			}
			
			edges.get(i).remove(v.getId());
		}
	}
	
	@Override
	public void insertEdge(Vertex<E> vertex1, Vertex<E> vertex2, int weight) {
		edges.get(vertex1.getId()).set(vertex2.getId(), weight);
		edges.get(vertex1.getId()).set(vertex2.getId(), weight);
	}

	@Override
	public void bfs(Vertex<E> s) {
		for (int i = 0; i < getVertex().size(); i++) {
			getVertex().get(i).setColor(Colors.WHITE);
			getVertex().get(i).setDistance(Integer.MAX_VALUE);
			getVertex().get(i).setPredecessor(null);
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

	@Override
	public void dfs() {
		for (int i = 0; i < getVertex().size(); i++) {
			getVertex().get(i).setColor(Colors.WHITE);
			getVertex().get(i).setPredecessor(null);
		}
		for (int i = 0; i < getVertex().size(); i++) {
			if(getVertex().get(i).getColor().equals(Colors.WHITE)) {
				dfsVisit(getVertex().get(i),0);
			}
		}
	}

	public int dfsVisit(Vertex<E> u, int time){
		time = time + 1;
		u.getTimestamps().setFirst(time);
		u.setColor(Colors.GRAY);
		ArrayList<Integer> edge = edges.get(u.getId());
		for (int i = 0; i < edge.size(); i++) {
			if(edge.get(i) != Integer.MAX_VALUE && i != u.getId()) {
				Vertex<E> v = getVertex().get(i);
				if(v.getColor() == Colors.WHITE) {
					v.setPredecessor(u);
					time = dfsVisit(v, time);
				}
			}
		}

		u.setColor(Colors.BLACK);
		time = time + 1;
		u.getTimestamps().setSecond(time);
		return time;
	}

	@Override
	public void dijkstra() {

	}

	@Override
	public void floyd() {

	}

	@Override
	public void prim() {

	}

	@Override
	public void kruskal() {

	}
}

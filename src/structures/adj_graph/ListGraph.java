package structures.adj_graph;

import structures.GraphInterface;

import java.util.*;

public class ListGraph<T> implements GraphInterface<T> {

	private List<Vertex<T>> vertices;
	private int size;
	private int time;

	public ListGraph() {
		vertices = new ArrayList<>();
		size = 0;
		time = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T t) {
		for(int i = 0; i < size; i ++) {
			if(vertices.get(i).value() == t)
				return true;
		}
		return false;
	}

	@Override
	public void insert(T t) {
		if(!contains(t)) {
			vertices.add(new Vertex<T>(t));
			++ size;
		}
	}

	@Override
	public void insert(List<T> ts) {
		for (T t : ts)
			insert(t);
	}

	public Vertex<T> getVertexByValue(T t) {
		for(int i = 0; i < size; i ++) {
			Vertex<T> current = vertices.get(i);
			if(current.value() == t)
				return current;
		}
		return null;
	}

	@Override
	public void insert(T t, T v, int w) {
		insert(t);
		insert(v);
		Vertex<T> tV = getVertexByValue(t);
		Vertex<T> vV = getVertexByValue(v);
		tV.link(vV, w);
		vV.link(tV, w);
	}

	@Override
	public void insert(T t, List<T> adjacent, List<Integer> weights) {
		if(adjacent.size() != weights.size())
			return;
		insert(t);
		insert(adjacent);
		Vertex<T> u = getVertexByValue(t);
		for(int i = 0; i < adjacent.size(); i ++) {
			Vertex<T> v = getVertexByValue(adjacent.get(i));
			int w = weights.get(i);
			u.link(v, w);
			v.link(u, w);
		}
	}

	@Override
	public void remove(T t) {
		Vertex<T> u = getVertexByValue(t);
		if(u == null)
			return;
		vertices.remove(u);
		for(Vertex<T> v : vertices) {
			List<Vertex<T>> adj = v.getAdjacent();
			List<Integer> w = v.getWeights();
			int i = adj.indexOf(u);
			if(i != -1)
				w.remove(i);
			adj.remove(u);
		}
		-- size;
	}

	@Override
	public void bfs(T t) {
		Vertex<T> s = getVertexByValue(t);
		if(s == null)
			return;
		for(Vertex<T> u : vertices) {
			u.setColor(Color.WHITE);
			u.setKey(Integer.MAX_VALUE);
			u.setPredecessor(null);
		}
		s.setColor(Color.GRAY);
		s.setKey(0);
		Queue<Vertex<T>> queue = new LinkedList<>();
		queue.add(s);
		while(!queue.isEmpty()) {
			Vertex<T> u = queue.poll();
			for(Vertex<T> v : u.getAdjacent()) {
				if(v.getColor() == Color.WHITE) {
					v.setColor(Color.GRAY);
					v.setKey(u.getKey() + 1);
					v.setPredecessor(u.value());
					queue.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}
	}

	@Override
	public void dfs() {
		for(Vertex<T> u : vertices) {
			u.setColor(Color.WHITE);
			u.setPredecessor(null);
			u.setKey(Integer.MAX_VALUE);
		}
		time = 0;
		for(Vertex<T> u : vertices) {
			if(u.getColor() == Color.WHITE) {
				u.setKey(0);
				dfsVisit(u);
			}
		}
	}

	private void dfsVisit(Vertex<T> u) {
		u.setStart(++ time);
		u.setColor(Color.GRAY);
		for(Vertex<T> v : u.getAdjacent()) {
			if(v.getColor() == Color.WHITE) {
				v.setPredecessor(u.value());
				v.setKey(u.getKey() + 1);
				dfsVisit(v);
			}
		}
		u.setColor(Color.BLACK);
		u.setEnd(++ time);
	}

	private int weight(Vertex<T> u, Vertex<T> v) {
		return u.weight(v);
	}

	private void initializeSingleSource(Vertex<T> s) {
		for(Vertex<T> v : vertices) {
			v.setColor(Color.WHITE);
			v.setKey(Integer.MAX_VALUE);
			v.setPredecessor(null);
		}
		s.setKey(0);
	}

	private void relax(Vertex<T> u, Vertex<T> v) {
		if(u.getKey() == Integer.MAX_VALUE)
			return;
		if(v.getKey() > u.getKey() + weight(u, v)) {
			v.setKey(u.getKey() + weight(u, v));
			v.setPredecessor(u.value());
		}
	}

	@Override
	public void dijkstra(T t) {
		Vertex<T> source = getVertexByValue(t);
		if(source == null)
			return;
		initializeSingleSource(source);
		List<Vertex<T>> shortest = new ArrayList<>();
		PriorityQueue<Vertex<T>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			Vertex<T> u = queue.poll();
			shortest.add(u);
			for(Vertex<T> v : u.getAdjacent())
				relax(u, v);
		}
	}

	@Override
	public void floyd() {

	}

	@Override
	public void prim(T t) {
		Vertex<T> r = getVertexByValue(t);
		if(r == null)
			return;
		initializeSingleSource(r);
		PriorityQueue<Vertex<T>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			Vertex<T> u = queue.poll();
			for(Vertex<T> v : u.getAdjacent()) {
				if(v.getColor() == Color.WHITE && weight(u, v) < v.getKey()) {
					queue.remove(v);
					v.setKey(weight(u, v));
					v.setPredecessor(u.value());
					queue.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}
	}

	@Override
	public void kruskal() {

	}

	@Override
	public String toString() {
		String msg = "";
		for(Vertex<T> v : vertices) {
			msg += v.value() + " :"
					+ "\nPredecessor: " + v.getPredecessor()
					+ "\nstart: " + v.start()
					+ "\nend: " + v.end()
					+ "\nkey: " + v.getKey()
					+ "\n" + v.toString();
		}
		return msg;
	}

}

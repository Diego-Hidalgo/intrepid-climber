package structures.adj_graph;

import structures.GraphInterface;

import java.util.*;

public class ListGraph<E> implements GraphInterface<E> {

	private List<Vertex<E>> vertices;
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
	public boolean contains(E e) {
		for(int i = 0; i < size; i ++) {
			if(vertices.get(i).value() == e)
				return true;
		}
		return false;
	}

	@Override
	public void insert(E e) {
		if(!contains(e)) {
			vertices.add(new Vertex<E>(e));
			++ size;
		}
	}

	@Override
	public void insert(List<E> es) {
		for (E e : es)
			insert(e);
	}

	public Vertex<E> getVertexByValue(E e) {
		for(int i = 0; i < size; i ++) {
			Vertex<E> current = vertices.get(i);
			if(current.value() == e)
				return current;
		}
		return null;
	}

	@Override
	public void insert(E e, E v, int w) {
		insert(e);
		insert(v);
		Vertex<E> tV = getVertexByValue(e);
		Vertex<E> vV = getVertexByValue(v);
		tV.link(vV, w);
		vV.link(tV, w);
	}

	@Override
	public void insert(E e, List<E> adjacent, List<Integer> weights) {
		if(adjacent.size() != weights.size())
			return;
		insert(e);
		insert(adjacent);
		Vertex<E> u = getVertexByValue(e);
		for(int i = 0; i < adjacent.size(); i ++) {
			Vertex<E> v = getVertexByValue(adjacent.get(i));
			int w = weights.get(i);
			u.link(v, w);
			v.link(u, w);
		}
	}

	@Override
	public void remove(E e) {
		Vertex<E> u = getVertexByValue(e);
		if(u == null)
			return;
		vertices.remove(u);
		for(Vertex<E> v : vertices) {
			List<Vertex<E>> adj = v.getAdjacent();
			List<Integer> w = v.getWeights();
			int i = adj.indexOf(u);
			if(i != -1)
				w.remove(i);
			adj.remove(u);
		}
		-- size;
	}

	@Override
	public void bfs(E e) {
		Vertex<E> s = getVertexByValue(e);
		if(s == null)
			return;
		for(Vertex<E> u : vertices) {
			u.setColor(Color.WHITE);
			u.setKey(Integer.MAX_VALUE);
			u.setPredecessor(null);
		}
		s.setColor(Color.GRAY);
		s.setKey(0);
		Queue<Vertex<E>> queue = new LinkedList<>();
		queue.add(s);
		while(!queue.isEmpty()) {
			Vertex<E> u = queue.poll();
			for(Vertex<E> v : u.getAdjacent()) {
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
		for(Vertex<E> u : vertices) {
			u.setColor(Color.WHITE);
			u.setPredecessor(null);
			u.setKey(Integer.MAX_VALUE);
		}
		time = 0;
		for(Vertex<E> u : vertices) {
			if(u.getColor() == Color.WHITE) {
				u.setKey(0);
				dfsVisit(u);
			}
		}
	}

	private void dfsVisit(Vertex<E> u) {
		u.setStart(++ time);
		u.setColor(Color.GRAY);
		for(Vertex<E> v : u.getAdjacent()) {
			if(v.getColor() == Color.WHITE) {
				v.setPredecessor(u.value());
				v.setKey(u.getKey() + 1);
				dfsVisit(v);
			}
		}
		u.setColor(Color.BLACK);
		u.setEnd(++ time);
	}

	private int weight(Vertex<E> u, Vertex<E> v) {
		return u.weight(v);
	}

	private void initializeSingleSource(Vertex<E> s) {
		for(Vertex<E> v : vertices) {
			v.setColor(Color.WHITE);
			v.setKey(Integer.MAX_VALUE);
			v.setPredecessor(null);
		}
		s.setKey(0);
	}

	private void relax(Vertex<E> u, Vertex<E> v) {
		if(u.getKey() == Integer.MAX_VALUE)
			return;
		if(v.getKey() > u.getKey() + weight(u, v)) {
			v.setKey(u.getKey() + weight(u, v));
			v.setPredecessor(u.value());
		}
	}

	@Override
	public void dijkstra(E e) {
		Vertex<E> source = getVertexByValue(e);
		if(source == null)
			return;
		initializeSingleSource(source);
		List<Vertex<E>> shortest = new ArrayList<>();
		PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			Vertex<E> u = queue.poll();
			shortest.add(u);
			for(Vertex<E> v : u.getAdjacent())
				relax(u, v);
		}
	}

	@Override
	public void floyd() {

	}

	@Override
	public void prim(E e) {
		Vertex<E> r = getVertexByValue(e);
		if(r == null)
			return;
		initializeSingleSource(r);
		PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			Vertex<E> u = queue.poll();
			for(Vertex<E> v : u.getAdjacent()) {
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
		for(Vertex<E> v : vertices) {
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

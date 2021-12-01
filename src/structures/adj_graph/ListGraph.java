package structures.adj_graph;

import structures.Edge;
import structures.GraphInterface;
import java.util.*;

public class ListGraph<E> implements GraphInterface<E> {

	private List<ListVertex<E>> vertices;
	private List<Edge<ListVertex<E>>> edges;
	private int size;
	private int time;

	public ListGraph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
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
			if(vertices.get(i).value().equals(e))
				return true;
		}
		return false;
	}

	@Override
	public void insert(E e) {
		if(!contains(e)) {
			vertices.add(new ListVertex<E>(e));
			++ size;
		}
	}

	@Override
	public void insert(List<E> es) {
		for (E e : es)
			insert(e);
	}

	public ListVertex<E> getVertexByValue(E e) {
		for(int i = 0; i < size; i ++) {
			ListVertex<E> current = vertices.get(i);
			if(current.value().equals(e))
				return current;
		}
		return null;
	}

	private void addEdgeSorted(Edge<ListVertex<E>> toAdd) {
		int i = 0;
		int edgesSize = edges.size();
		while(i < edgesSize && toAdd.compareTo(edges.get(i)) > 0)
			++ i;
		edges.add(i, toAdd);
	}

	@Override
	public void insert(E e, E v, int w) {
		insert(e);
		insert(v);
		ListVertex<E> eV = getVertexByValue(e);
		ListVertex<E> vV = getVertexByValue(v);
		eV.link(vV, w);
		addEdgeSorted(new Edge<>(eV, vV, w));
	}

	@Override
	public void insert(E e, List<E> adjacent, List<Integer> weights) {
		if(adjacent.size() != weights.size())
			return;
		insert(e);
		insert(adjacent);
		ListVertex<E> u = getVertexByValue(e);
		for(int i = 0; i < adjacent.size(); i ++) {
			ListVertex<E> v = getVertexByValue(adjacent.get(i));
			int w = weights.get(i);
			u.link(v, w);
			addEdgeSorted(new Edge<>(u, v, w));
		}
	}

	@Override
	public void remove(E e) {
		ListVertex<E> u = getVertexByValue(e);
		if(u == null)
			return;
		vertices.remove(u);
		for(ListVertex<E> v : vertices) {
			List<ListVertex<E>> adj = v.getAdjacent();
			List<Integer> w = v.getWeights();
			int i = adj.indexOf(u);
			if(i != -1)
				w.remove(i);
			adj.remove(u);
		}
		-- size;
	}

	@Override
	public int weight(E u, E v) {
		ListVertex<E> uV = getVertexByValue(u);
		ListVertex<E> vV = getVertexByValue(v);
		if(uV == null || vV == null)
			return Integer.MAX_VALUE;
		else
			return weight(uV, vV);
	}

	private int weight(ListVertex<E> u, ListVertex<E> v) {
		return u.weight(v);
	}

	@Override
	public int indexOf(E e) {
		ListVertex<E> eV = getVertexByValue(e);
		if(eV == null)
			return -1;
		else
			return vertices.indexOf(eV);
	}

	@Override
	public void clear() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		size = 0;
		time = 0;
	}

	@Override
	public void bfs(E e) {
		ListVertex<E> s = getVertexByValue(e);
		if(s == null)
			return;
		for(ListVertex<E> u : vertices) {
			u.setColor(Color.WHITE);
			u.setKey(Integer.MAX_VALUE);
			u.setPredecessor(null);
		}
		s.setColor(Color.GRAY);
		s.setKey(0);
		Queue<ListVertex<E>> queue = new LinkedList<>();
		queue.add(s);
		while(!queue.isEmpty()) {
			ListVertex<E> u = queue.poll();
			for(ListVertex<E> v : u.getAdjacent()) {
				if(v.getColor() == Color.WHITE) {
					v.setColor(Color.GRAY);
					v.setPredecessor(u.value());
					queue.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}
	}

	@Override
	public void dfs() {
		for(ListVertex<E> u : vertices) {
			u.setKey(0);
			u.setColor(Color.WHITE);
			u.setPredecessor(null);
			u.setKey(Integer.MAX_VALUE);
		}
		time = 0;
		for(ListVertex<E> u : vertices) {
			if(u.getColor() == Color.WHITE) {
				dfsVisit(u);
			}
		}
	}

	private void dfsVisit(ListVertex<E> u) {
		u.setStart(++ time);
		u.setColor(Color.GRAY);
		for(ListVertex<E> v : u.getAdjacent()) {
			if(v.getColor() == Color.WHITE) {
				v.setPredecessor(u.value());
				dfsVisit(v);
			}
		}
		u.setColor(Color.BLACK);
		u.setEnd(++ time);
	}

	private void initializeSingleSource(ListVertex<E> s) {
		for(ListVertex<E> v : vertices) {
			v.setColor(Color.WHITE);
			v.setKey(Integer.MAX_VALUE);
			v.setPredecessor(null);
		}
		s.setKey(0);
	}

	private void relax(ListVertex<E> u, ListVertex<E> v) {
		if(u.getKey() == Integer.MAX_VALUE)
			return;
		if(v.getKey() > u.getKey() + weight(u, v)) {
			v.setKey(u.getKey() + weight(u, v));
			v.setPredecessor(u.value());
		}
	}

	@Override
	public List<ListVertex<E>> dijkstra(E e) {
		ListVertex<E> source = getVertexByValue(e);
		if(source == null)
			return null;
		initializeSingleSource(source);
		List<ListVertex<E>> shortest = new ArrayList<>();
		PriorityQueue<ListVertex<E>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			ListVertex<E> u = queue.poll();
			shortest.add(u);
			for(ListVertex<E> v : u.getAdjacent())
				relax(u, v);
		}
		return shortest;
	}

	@Override
	public int[][] floyd() {
		int[][] dist = new int[size][size];
		for(int i = 0; i < size; i ++)
			for(int j = 0; j < size; j ++)
				if(i != j)
					dist[i][j] = weight(vertices.get(i), vertices.get(j));
		for(int k = 0; k < size; k ++) {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)
						continue;
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		return dist;
	}

	@Override
	public int prim(E e) {
		int total = 0;
		ListVertex<E> r = getVertexByValue(e);
		if(r == null)
			return total;
		initializeSingleSource(r);
		PriorityQueue<ListVertex<E>> queue = new PriorityQueue<>(vertices);
		while(!queue.isEmpty()) {
			ListVertex<E> u = queue.poll();
			for(ListVertex<E> v : u.getAdjacent()) {
				if(v.getColor() == Color.WHITE && weight(u, v) < v.getKey()) {
					queue.remove(v);
					v.setKey(weight(u, v));
					total += v.getKey();
					v.setPredecessor(u.value());
					queue.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}
		return total;
	}

	private void makeSet(List<List<ListVertex<E>>> sets, ListVertex<E> v) {
		List<ListVertex<E>> set = new ArrayList<>();
		set.add(v);
		sets.add(set);
	}

	private int findSet(List<List<ListVertex<E>>> sets, ListVertex<E> v) {
		for(int i = 0; i < sets.size(); i ++) {
			if(sets.get(i).contains(v))
				return i;
		}
		return -1;
	}

	private void union(List<List<ListVertex<E>>> sets, ListVertex<E> u, ListVertex<E> v) {
		int vi = findSet(sets, v);
		List<ListVertex<E>> removed = sets.remove(vi);
		int ui = findSet(sets, u);
		sets.get(ui).addAll(removed);
	}

	@Override
	public int kruskal() {
		int total = 0;
		List<Edge<ListVertex<E>>> A = new ArrayList<>();
		List<List<ListVertex<E>>> sets = new ArrayList<>();
		for(ListVertex<E> v : vertices)
			makeSet(sets, v);
		for(Edge<ListVertex<E>> edge : edges) {
			if(findSet(sets, edge.u()) != findSet(sets, edge.v())) {
				A.add(edge);
				union(sets, edge.u(), edge.v());
				total += edge.weight();
			}
		}
		return total;
	}

	@Override
	public String toString() {
		String msg = "";
		for(ListVertex<E> v : vertices) {
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

package structures;

import java.util.List;

import structures.adj_graph.ListVertex;

public interface GraphInterface<E> {

	int size();

	boolean contains(E e);

	void insert(E e);

	void insert(List<E> es);

	void insert(E e, E v, int w);

	void insert(E e, List<E> adjacent, List<Integer> weights);

	void remove(E e);

	int weight(E u, E v);

	void bfs(E e);

	void dfs();

	List<ListVertex<E>> dijkstra(E e);

	void floyd();

	void prim(E e);

	void kruskal();

}

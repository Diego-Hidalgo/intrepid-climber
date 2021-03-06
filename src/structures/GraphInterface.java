package structures;

import java.util.List;

public interface GraphInterface<E> {

	int size();

	boolean contains(E e);

	void insert(E e);

	void insert(List<E> es);

	void insert(E e, E v, int w);

	void insert(E e, List<E> adjacent, List<Integer> weights);

	void remove(E e);

	int weight(E u, E v);

	int indexOf(E e);

	void clear();

	void bfs(E e);

	void dfs();

	int dijkstra(E e);

	int[][] floyd();

	int prim(E e);

	int kruskal();

}

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

	void bfs(E e);

	void dfs();

	void dijkstra(E e);

	void floyd();

	void prim(E e);

	void kruskal();

}

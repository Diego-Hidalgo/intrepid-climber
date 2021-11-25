package structures.graph;

import java.util.List;

public interface GraphInterface<T> {

	int size();

	boolean contains(T t);

	void insert(T t);

	void insert(List<T> ts);

	void insert(T t, T v, int w);

	void insert(T t, List<T> adjacent, List<Integer> weights);

	void remove(T t);

	void bfs(T t);

	void dfs();

	void dijkstra(T t);

	void floyd();

	void prim(T t);

	void kruskal();


}

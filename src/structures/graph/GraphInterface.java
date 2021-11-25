package structures.graph;

public interface GraphInterface<E> {

	public void insertVertex(E value);
	public void deleteVertex(Vertex<E> vertex);
	public void insertEdge(Vertex<E> vertex1, Vertex<E> vertex2, int weight);
	public void bfs(Vertex<E> s);
	public void dfs();
	public void dijkstra();
	public void floyd();
	public void prim();
	public void kruskal();
}

package structures.matrix_graph;

import structures.Edge;
import structures.GraphInterface;
import structures.adj_graph.ListVertex;
import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<E> implements GraphInterface<E> {

    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private int[][] matrix;
    private List<MatrixVertex<E>> vertices;
    private List<Edge<MatrixVertex<E>>> edges;
    private int capacity;

    public MatrixGraph() {
        size = 0;
        matrix = new int[INITIAL_CAPACITY][INITIAL_CAPACITY];
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        capacity = INITIAL_CAPACITY;
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

    private boolean isFull() {
        return (size == capacity);
    }

    private void extendCapacity() {
        capacity = capacity + 10;
        int[][] extended = new int[capacity][capacity];
        for(int i = 0; i < size; i ++)
            for(int j = 0; j < size; j ++)
                extended[i][j] = matrix[i][j];
        matrix = extended;
    }

    @Override
    public void insert(E e) {
        if(!contains(e)) {
            if(isFull())
                extendCapacity();
            MatrixVertex<E> toAdd = new MatrixVertex<>(e, size);
            vertices.add(toAdd);
            for(int i = 0; i < size; i ++) {
                matrix[i][size] = Integer.MAX_VALUE;
                matrix[size][i] = Integer.MAX_VALUE;
            }
            matrix[size][size] = 0;
            ++ size;
        }
    }

    @Override
    public void insert(List<E> es) {
        for(E e : es)
            insert(e);
    }

    private MatrixVertex<E> getVertexByValue(E e) {
        for(int i = 0; i < size; i ++) {
            MatrixVertex<E> current = vertices.get(i);
            if(current.value() == e)
                return current;
        }
        return null;
    }

    private void addEdgeSorted(Edge<MatrixVertex<E>> toAdd) {
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
        MatrixVertex<E> eV = getVertexByValue(e);
        MatrixVertex<E> vV = getVertexByValue(v);
        int ePosition = eV.getPosition();
        int vPosition = vV.getPosition();
        matrix[ePosition][vPosition] = w;
        addEdgeSorted(new Edge<>(eV, vV, w));
    }

    @Override
    public void insert(E e, List<E> adjacent, List<Integer> weights) {
        if(adjacent.size() != weights.size())
            return;
        insert(e);
        insert(adjacent);
        MatrixVertex<E> eV = getVertexByValue(e);
        int ePosition = eV.getPosition();
        for(int i = 0; i < adjacent.size(); i ++) {
            MatrixVertex<E> vV = getVertexByValue(adjacent.get(i));
            int adjPosition = vV.getPosition();
            int w = weights.get(i);
            matrix[ePosition][adjPosition] = w;
            addEdgeSorted(new Edge<>(eV, vV, w));
        }
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public int weight(E u, E v) {
        MatrixVertex<E> uV = getVertexByValue(u);
        MatrixVertex<E> vV = getVertexByValue(v);
        if(uV == null || vV == null)
            return Integer.MAX_VALUE;
        else
            return weight(uV, vV);
    }

    private int weight(MatrixVertex<E> u, MatrixVertex<E> v) {
        return matrix[u.getPosition()][v.getPosition()];
    }

    @Override
    public int indexOf(E e) {
        MatrixVertex<E> eV = getVertexByValue(e);
        if(eV == null)
            return -1;
        else
            return eV.getPosition();
    }

    @Override
    public void clear() {
        size = 0;
        vertices = new ArrayList<>();
        matrix = new int[INITIAL_CAPACITY][INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    @Override
    public void bfs(E e) {

    }

    @Override
    public void dfs() {

    }

    @Override
    public List<ListVertex<E>> dijkstra(E e) {
		return null;
    }

    @Override
    public int[][] floyd() {
        int[][] dist = matrix.clone();
        for(int k = 0; k < size; k ++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
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
        return 0;
    }

    private void makeSet(List<List<MatrixVertex<E>>> sets, MatrixVertex<E> v) {
        List<MatrixVertex<E>> set = new ArrayList<>();
        set.add(v);
        sets.add(set);
    }

    private int findSet(List<List<MatrixVertex<E>>> sets, MatrixVertex<E> v) {
        for(int i = 0; i < sets.size(); i ++) {
            if(sets.get(i).contains(v))
                return i;
        }
        return -1;
    }

    private void union(List<List<MatrixVertex<E>>> sets, MatrixVertex<E> u, MatrixVertex<E> v) {
        int vi = findSet(sets, v);
        List<MatrixVertex<E>> removed = sets.remove(vi);
        int ui = findSet(sets, u);
        sets.get(ui).addAll(removed);
    }

    @Override
    public int kruskal() {
        int total = 0;
        List<Edge<MatrixVertex<E>>> A = new ArrayList<>();
        List<List<MatrixVertex<E>>> sets = new ArrayList<>();
        for (MatrixVertex<E> v : vertices)
            makeSet(sets, v);
        for (Edge<MatrixVertex<E>> edge : edges) {
            if (findSet(sets, edge.u()) != findSet(sets, edge.v())) {
                A.add(edge);
                union(sets, edge.u(), edge.v());
                total += edge.weight();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        String msg1 = "";
        String msg2 = "";
        for(int i = 0; i < size; i ++) {
            msg1 += vertices.get(i) + "\n";
            for(int j = 0; j < size; j ++) {
                if(matrix[i][j] == Integer.MAX_VALUE)
                    msg2 += "∞ ";
                else
                    msg2 += matrix[i][j] + " ";
            }
            msg2 += "\n";
        }
        return msg1 + msg2;
    }

}

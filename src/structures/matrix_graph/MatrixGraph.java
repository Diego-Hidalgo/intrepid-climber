package structures.matrix_graph;

import structures.GraphInterface;
import structures.adj_graph.ListVertex;
import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<E> implements GraphInterface<E> {

    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private List<MatrixVertex<E>> vertices;
    private int[][] matrix;
    private int capacity;

    public MatrixGraph() {
        size = 0;
        vertices = new ArrayList<>();
        matrix = new int[INITIAL_CAPACITY][INITIAL_CAPACITY];
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

    @Override
    public void insert(E e, E v, int w) {
        insert(e);
        insert(v);
        int ePosition = getVertexByValue(e).getPosition();
        int vPosition = getVertexByValue(v).getPosition();
        matrix[ePosition][vPosition] = w;
        matrix[vPosition][ePosition] = w;
    }

    @Override
    public void insert(E e, List<E> adjacent, List<Integer> weights) {
        if(adjacent.size() != weights.size())
            return;
        insert(e);
        insert(adjacent);
        int ePosition = getVertexByValue(e).getPosition();
        for(int i = 0; i < adjacent.size(); i ++) {
            int adjPosition = getVertexByValue(adjacent.get(i)).getPosition();
            int w = weights.get(i);
            matrix[ePosition][adjPosition] = w;
            matrix[adjPosition][ePosition] = w;
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
    public void prim(E e) {

    }

    @Override
    public void kruskal() {

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

package structures.matrix_graph;

import structures.GraphInterface;

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
            matrix[ePosition][adjPosition] = weights.get(i);
            matrix[adjPosition][ePosition] = weights.get(i);
        }
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public int weight(E u, E v) {
        if(!contains(u) || !contains(v))
            return Integer.MAX_VALUE;
        else
            return weight(getVertexByValue(u), getVertexByValue(v));
    }

    private int weight(MatrixVertex<E> u, MatrixVertex<E> v) {
        return matrix[u.getPosition()][v.getPosition()];
    }

    @Override
    public void bfs(E e) {

    }

    @Override
    public void dfs() {

    }

    @Override
    public void dijkstra(E e) {

    }

    @Override
    public void floyd() {

    }

    @Override
    public void prim(E e) {

    }

    @Override
    public void kruskal() {

    }

    @Override
    public String toString() {
        String msg = "";
        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j ++) {
                if(matrix[i][j] == Integer.MAX_VALUE)
                    msg += "∞ ";
                else
                    msg += matrix[i][j] + " ";
            }
            msg += "\n";
        }
        return msg;
    }

}

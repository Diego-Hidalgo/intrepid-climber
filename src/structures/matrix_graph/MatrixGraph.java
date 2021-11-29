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
        int[][] extended = new int[capacity + 10][ capacity + 10];
        for(int i = 0; i < size; i ++)
            for(int j = 0; j < size; j ++)
                extended[i][j] = matrix[i][j];
        matrix = extended;
        capacity = capacity + 10;
    }

    @Override
    public void insert(E e) {

    }

    @Override
    public void insert(List<E> es) {

    }

    @Override
    public void insert(E e, E v, int w) {

    }

    @Override
    public void insert(E e, List<E> adjacent, List<Integer> weights) {

    }

    @Override
    public void remove(E e) {

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

}

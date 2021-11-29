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
        return false;
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

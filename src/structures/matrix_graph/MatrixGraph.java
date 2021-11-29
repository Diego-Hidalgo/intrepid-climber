package structures.matrix_graph;

import structures.GraphInterface;

import java.util.List;

public class MatrixGraph<E> implements GraphInterface<E> {

    @Override
    public int size() {
        return 0;
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

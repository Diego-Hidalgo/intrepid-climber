package structures;

import structures.adj_graph.Color;

public class Edge<E> implements Comparable<Edge<E>>{

    private E u;
    private E v;
    private int weight;
    private Color color;

    public Edge(E u, E v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
        this.color = Color.WHITE;
    }

    public E u() {
        return u;
    }

    public void setU(E u) {
        this.u = u;
    }

    public E v() {
        return v;
    }

    public void setV(E v) {
        this.v = v;
    }

    public int weight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int compareTo(Edge<E> o) {
        return Integer.compare(weight, o.weight);
    }

}

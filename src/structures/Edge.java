package structures;

public class Edge<E> implements Comparable<Edge<E>>{

    private E u;
    private E v;
    private int weight;

    public Edge(E u, E v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
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


    @Override
    public int compareTo(Edge<E> o) {
        return Integer.compare(weight, o.weight);
    }

}

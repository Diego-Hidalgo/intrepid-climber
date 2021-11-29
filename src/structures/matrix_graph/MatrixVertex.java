package structures.matrix_graph;

public class MatrixVertex<T> {

    private T value;
    private int position;

    public MatrixVertex(T value, int position) {
        this.value = value;
        this.position = position;
    }

    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
	
}

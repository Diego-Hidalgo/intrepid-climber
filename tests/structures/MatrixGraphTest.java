package structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import structures.adj_graph.ListGraph;
import structures.matrix_graph.MatrixGraph;

public class MatrixGraphTest {

	MatrixGraph<Integer> graph;
	List<Integer> v;
	List<Integer> w;

	public MatrixGraph<Integer> graphScenary1() {
		graph = new MatrixGraph<>();
		v = new ArrayList<>();


		return graph;
	}

	public MatrixGraph<Integer> graphScenary2() {
		graph = new MatrixGraph<>();
		v = new ArrayList<>();
		w = new ArrayList<>();


		return graph;
	}

	public int[][] setupScenary3(){
		int[][] matrix = {
				{0,3,4},
				{3,0,1},
				{4,1,0}
		};
		return matrix;
	}


	@Test
	public void testInsert() {
		MatrixGraph<Integer> graph = graphScenary1();


	}

	@Test
	public void testInsert2() {
		MatrixGraph<Integer> graph = graphScenary2();

	}
}

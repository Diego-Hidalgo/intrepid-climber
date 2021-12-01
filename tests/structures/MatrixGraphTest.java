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
	List<Integer> e;


	public MatrixGraph<Integer> graphScenary1() {
		graph = new MatrixGraph<Integer>();
		v = new ArrayList<>();
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);

		graph.insert(1, 2, 0);
		graph.insert(1, 3, 0);
		graph.insert(2, 3, 0);
		graph.insert(2, 4, 0);
		graph.insert(3, 4, 0);

		return graph;
	}

	public MatrixGraph<Integer> graphScenary2() {
		graph = new MatrixGraph<Integer>();
		v = new ArrayList<>();
		e = new ArrayList<>();

		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);

		graph.insert(1, 2, 15);
		graph.insert(1, 3, 11);
		graph.insert(2, 3, 12);
		graph.insert(2, 4, 14);
		graph.insert(3, 4, 3);

		return graph;

	}


	@Test
	public void testInsert() {
		MatrixGraph<Integer> graph = graphScenary1();

		v.add(5);

		graph.insert(2, 5, 0);
		graph.insert(4, 5, 0);

		assertTrue(graph.contains(5));
	}

	@Test
	public void testInsert2() {
		MatrixGraph<Integer> graph = graphScenary2();
		
		v.add(5);

		graph.insert(2, 5, 1);
		graph.insert(4, 5, 2);

		assertTrue(graph.contains(5));
	}
	
	@Test
	public void testFloyd() {
		MatrixGraph<Integer> graph = graphScenary2();
		
		int[][] g = graph.floyd();
		
		assertEquals(0, g[0][0]);
		assertEquals(11, g[0][2]);
		assertEquals(14, g[0][3]);
		assertEquals(0, g[1][1]);
		assertEquals(12, g[1][2]);
		assertEquals(14, g[1][3]);
		assertEquals(0, g[2][2]);
		assertEquals(3, g[2][3]);
		assertEquals(0, g[3][3]);
	}
	
}

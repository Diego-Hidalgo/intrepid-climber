package structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import structures.adj_graph.ListGraph;

public class ListGraphTest {

	ListGraph<Integer> graph;
	List<Integer> v;
	List<Integer> w;
	
	public ListGraph<Integer> graphScenary1() {
		graph = new ListGraph<>();
		v = new ArrayList<>();
		
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);
		v.add(5);
		
		graph.insert(1, 2, 0);
		graph.insert(1, 3, 0);
		graph.insert(2, 3, 0);
		graph.insert(2, 4, 0);
		graph.insert(2, 5, 0);
		graph.insert(3, 4, 0);
		graph.insert(4, 5, 0);
		
		return graph;
	}
	
	public ListGraph<Integer> graphScenary2() {
		graph = new ListGraph<>();
		v = new ArrayList<>();
		w = new ArrayList<>();
		
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);
		v.add(5);
		
		graph.insert(1, 2, 15);
		graph.insert(1, 3, 11);
		graph.insert(2, 3, 12);
		graph.insert(2, 4, 14);
		graph.insert(2, 5, 1);
		graph.insert(3, 4, 3);
		graph.insert(4, 5, 2);
		
		return graph;
	}
	
	@Test
	public void testInsert() {
		ListGraph<Integer> graph = graphScenary1();
		
		v.add(6);
		graph.insert(5, 6, 0);
		graph.insert(2, 6, 0);
		
		assertTrue(graph.contains(6));
	}
	
	@Test
	public void testInsert2() {
		ListGraph<Integer> graph = graphScenary2();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		assertTrue(graph.contains(6));
	}
	
	@Test
	public void testRemove() {
		ListGraph<Integer> graph = graphScenary1();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		graph.remove(3);
		
		assertFalse(graph.contains(3));
	}
	
	@Test
	public void testRemove2() {
		ListGraph<Integer> graph = graphScenary2();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		graph.remove(5);
		
		assertFalse(graph.contains(5));
	}
	
	@Test
	public void testBfs() {
		ListGraph<Integer> graph = graphScenary1();
		
		graph.bfs(1);
		
		assertEquals(0,graph.getVertexByValue(1).getKey());
		assertEquals(1,graph.getVertexByValue(2).getKey());
		assertEquals(1,graph.getVertexByValue(3).getKey());
		assertEquals(2,graph.getVertexByValue(4).getKey());
		assertEquals(2,graph.getVertexByValue(5).getKey());
	}
	
	/*@Test
	public void testDfs() {
		ListGraph<Integer> adj_graph = graphScenary1();
		
		adj_graph.dfs();
		
	}*/
	
	@Test
	public void testDijkstra() {
		ListGraph<Integer> graph = graphScenary2();
		
	}
	
	@Test
	public void testFloyd() {
		ListGraph<Integer> graph = graphScenary2();
		
		int[][] g = graph.floyd();
		
		assertEquals(0, g[0][0]);
		assertEquals(15, g[0][1]);
		assertEquals(11, g[0][2]);
		assertEquals(14, g[0][3]);
		assertEquals(16, g[0][4]);
		
		assertEquals(15, g[1][0]);
		assertEquals(0, g[1][1]);
		assertEquals(6, g[1][2]);
		assertEquals(3, g[1][3]);
		assertEquals(1, g[1][4]);
		
		assertEquals(11, g[2][0]);
		assertEquals(6, g[2][1]);
		assertEquals(0, g[2][2]);
		assertEquals(3, g[2][3]);
		assertEquals(5, g[2][4]);
		
		assertEquals(14, g[3][0]);
		assertEquals(3, g[3][1]);
		assertEquals(3, g[3][2]);
		assertEquals(0, g[3][3]);
		assertEquals(2, g[3][4]);
		
		assertEquals(16, g[4][0]);
		assertEquals(1, g[4][1]);
		assertEquals(5, g[4][2]);
		assertEquals(2, g[4][3]);
		assertEquals(0, g[4][4]);
	}
	
	
	@Test
	public void testPrim() {
		ListGraph<Integer> graph = graphScenary2();
		
		graph.prim(2);
		
		assertEquals(0, graph.getVertexByValue(2).getKey());
		assertEquals(1, graph.getVertexByValue(5).getKey());
		assertEquals(2, graph.getVertexByValue(4).getKey());
		assertEquals(3, graph.getVertexByValue(3).getKey());
		assertEquals(11, graph.getVertexByValue(1).getKey());
		
	}
	
	@Test
	public void testKruskal() {
		ListGraph<Integer> graph = graphScenary2();
		
		
	}
}


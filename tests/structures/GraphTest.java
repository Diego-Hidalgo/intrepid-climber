package structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import structures.graph.Color;
import structures.graph.Graph;

public class GraphTest {

	Graph<Integer> graph;
	List<Integer> v;
	List<Integer> w;
	
	public Graph<Integer> graphScenary1() {
		graph = new Graph<>();
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
	
	public Graph<Integer> graphScenary2() {
		graph = new Graph<>();
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
		Graph<Integer> graph = graphScenary1();
		
		v.add(6);
		graph.insert(5, 6, 0);
		graph.insert(2, 6, 0);
		
		assertTrue(graph.contains(6));
	}
	
	@Test
	public void testInsert2() {
		Graph<Integer> graph = graphScenary2();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		assertTrue(graph.contains(6));
	}
	
	@Test
	public void testRemove() {
		Graph<Integer> graph = graphScenary1();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		graph.remove(3);
		
		assertFalse(graph.contains(3));
	}
	
	@Test
	public void testRemove2() {
		Graph<Integer> graph = graphScenary2();
		
		v.add(6);
		graph.insert(4, 6, 1);
		graph.insert(5, 6, 5);
		
		graph.remove(5);
		
		assertFalse(graph.contains(5));
	}
	
	@Test
	public void testBfs() {
		Graph<Integer> graph = graphScenary1();
		
		graph.bfs(1);
		
		assertEquals(0,graph.getVertexByValue(1).getKey());
		assertEquals(1,graph.getVertexByValue(2).getKey());
		assertEquals(1,graph.getVertexByValue(3).getKey());
		assertEquals(2,graph.getVertexByValue(4).getKey());
		assertEquals(2,graph.getVertexByValue(5).getKey());
	}
	
	/*@Test
	public void testDfs() {
		Graph<Integer> graph = graphScenary1();
		
		graph.dfs();
		
	}*/
}


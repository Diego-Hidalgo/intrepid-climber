package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import structures.GraphInterface;
import structures.adj_graph.ListGraph;

public class MountainTest {

	private Mountain m;
	private GraphInterface<Integer> landmarks;
	private List<Integer> friends;

	public Mountain setupScenary1() {
		m = new Mountain();
		landmarks = new ListGraph<>();
        friends = new ArrayList<>();
        
        int [] f = {5,2};
        
        int [] lm = {1,2,2};
        int [] lm2 = {2,4,2};
        int [] lm3 = {1,3,3};
        int [] lm4 = {3,6,3};
        int [] lm5 = {3,5,1};
        
        m.insertLandMarks(5);
        m.insertLandMarks(lm);
        m.insertLandMarks(lm2);
        m.insertLandMarks(lm3);
        m.insertLandMarks(lm4);
        m.insertLandMarks(lm5);
        
        m.addFriends(f);
        
        return m;
	}
	
	@Test
	public void testInsertLandmark() {
		Mountain m = setupScenary1();
		landmarks = new ListGraph<>();
		
		int [] lm = {6,7,2};
		m.insertLandMarks(lm);
		
	}

	
	@Test
	public void testAddFriends() {
		Mountain m = setupScenary1();
		friends = new ArrayList<>();
		
		 int [] f = {3,6};
		 m.addFriends(f);
		 assertFalse(friends.contains(2));
		 
	}
}
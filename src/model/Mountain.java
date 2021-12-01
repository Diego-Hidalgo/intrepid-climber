package model;

import structures.GraphInterface;
import structures.adj_graph.ListGraph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mountain {

    private GraphInterface<Integer> landmarks;
    private List<Integer> friends;

    public Mountain() {
        landmarks = new ListGraph<>();
        friends = new ArrayList<>();
    }

    public int[] parseStringArray(String[] array) {
        int[] parsed = new int[array.length];
        for(int i = 0; i < parsed.length; i ++)
            parsed[i] = Integer.parseInt(array[i]);
        return parsed;
    }

    public void insertLandMarks(int N) {
        for(int i = 1; i <= N; i ++)
            landmarks.insert(i);
    }

    public void insertLandMarks(int[] array) {
        if(array.length != 3)
            return;
        int A, B, C, up, down;
        A = array[0];
        B = array[1];
        C = array[2];
        up = Integer.min(A, B);
        down = Integer.max(A, B);
        landmarks.insert(up, down, 0);
        landmarks.insert(down, up, C);
    }

    public void addFriends(int[] array) {
        if(array.length >= landmarks.size())
            return;
        for(int j : array) friends.add(j);
        Collections.sort(friends);
    }

    public int calcMinEnergy() {
        int[][] m = landmarks.floyd();
        return 0;
    }

}

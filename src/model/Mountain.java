package model;

import structures.GraphInterface;
import structures.adj_graph.ListGraph;
import java.util.ArrayList;
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

    public void insertLandMarks(int[] content) {
        if(content.length != 3)
            return;
        int A, B, C;
        A = content[0];
        B = content[1];
        C = content[2];
        landmarks.insert(A, B, C);
    }

    public void addFriends(int[] content) {
        if(content.length >= landmarks.size())
            return;
        for(int j : content) friends.add(j);
    }

    public int calcMinEnergy() {
        return 0;
    }

}

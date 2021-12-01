package model;

import structures.GraphInterface;
import structures.adj_graph.ListGraph;
import structures.matrix_graph.MatrixGraph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mountain {

    private GraphInterface<Integer> landmarks;
    private List<Integer> friends;

    public Mountain() {
        landmarks = new MatrixGraph<>();
        friends = new ArrayList<>();
    }

    public void clear() {
        landmarks = new MatrixGraph<>();
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
        int A, B, C;
        A = array[0];
        B = array[1];
        C = array[2];
        landmarks.insert(B, A, C);
        landmarks.insert(A, B, 0);
    }

    public void addFriends(int[] array) {
        if(array.length >= landmarks.size())
            return;
        for(int j : array) friends.add(j);
        Collections.sort(friends);
    }

    public int calcMinEnergy() {
        GraphInterface<Integer> auxGraph = new MatrixGraph<>();
        int[][] m = landmarks.floyd();
        int size = friends.size();
        int i = 0;
        while(i < size) {
            for(int j = 1; j < size; j ++) {
                int A, B, C;
                A = friends.get(0);
                B = friends.get(j);
                C = m[landmarks.indexOf(A)][landmarks.indexOf(B)];
                auxGraph.insert(A, B, C);
            }
            int removed = friends.remove(0);
            friends.add(removed);
            ++ i;
        }
        return auxGraph.prim(friends.get(0));
    }

    private void print(int[][] m, int size) {
        String msg = "";
        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j ++) {
                if(m[i][j] == Integer.MAX_VALUE)
                    msg += "∞ ";
                else
                    msg += m[i][j] + " ";
            }
            msg += "\n";
        }
        System.out.println(msg);
    }

}

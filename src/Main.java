import model.Mountain;

public class Main {

    public static void main(String[] args) {
        Mountain m = new Mountain();
        m.insertLandMarks(6);
        m.insertLandMarks(new int[]{1, 4, 75});
        m.insertLandMarks(new int[]{1, 3, 43});
        m.insertLandMarks(new int[]{3, 2, 86});
        m.insertLandMarks(new int[]{3, 5, 50});
        m.insertLandMarks(new int[]{1, 6, 25});
        m.addFriends(new int[]{5, 4, 6});
        System.out.println(m.calcMinEnergy());
    }

}

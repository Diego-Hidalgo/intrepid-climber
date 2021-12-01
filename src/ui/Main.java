package ui;

import model.Mountain;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data_test.txt"));
        File f = new File("output.txt");
        if(!f.exists())
            f.createNewFile();
        PrintWriter pw = new PrintWriter(f);
        Mountain m = new Mountain();
        String line;
        while((line = br.readLine()) != null) {
            int N = Integer.parseInt(line.split(" ")[0]);
            m.insertLandMarks(N);
            int[] content;
            for (int i = 0; i < N - 1; i++) {
                content = m.parseStringArray(br.readLine().split(" "));
                m.insertLandMarks(content);
            }
            content = m.parseStringArray(br.readLine().split(" "));
            m.addFriends(content);
            int a = m.calcMinEnergy();
            pw.write(a + "\n");
            m.clear();
            pw.flush();
        }
        pw.close();
        br.close();
    }

}

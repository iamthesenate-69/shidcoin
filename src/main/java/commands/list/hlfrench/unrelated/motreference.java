package commands.list.hlfrench.unrelated;

import java.util.Scanner;

public class motreference {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = ",";
        for (int i = 0; i < 6; i++) {
            String s1[] = sc.nextLine().split("\t");
            s += s1[s1.length-1] + ",";
            if (i == 2 || i == 5)
                s+= s1[s1.length-1] + ",";
        }
        System.out.println(s);
    }

}

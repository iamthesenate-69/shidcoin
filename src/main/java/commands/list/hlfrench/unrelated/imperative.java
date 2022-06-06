package commands.list.hlfrench.unrelated;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class imperative {
    static HashMap<String, String> toProunoun ;
    static HashMap<String, Integer> toIndex;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // trolled

        init();
        for (int i = 0; i < n; i++) {
            solve(sc.nextLine());
        }
    }

    private static void init() {
        toProunoun = new HashMap<>();
        toIndex = new HashMap<>();

        put("le", "le", 0);
        put("la", "la", 0);
        put("les", "les", 0);

        put("me", "moi", 1);
        put("te", "toi", 1);
        put("nous", "nous", 1);
        put("vous", "vous", 1);
        put("lui", "lui", 1);
        put("leur", "leur", 1);

        put("en", "en", 3);
        put("y", "y", 2);
    }

    static void put(String s1, String s2, int index) {
        toProunoun.put(s1, s2);
        toIndex.put(s1, index);
    }

    private static void solve(String nextLine) {
        //promettre: Tu m'y promets.

        nextLine = nextLine.replace("'", "e ").replace(":", "").replace(".", "").toLowerCase();
        //promettre tu me y promets


//        System.out.println(nextLine);

        String ele[] = nextLine.split(" ");

        String pronouns[] = new String[4];
        Arrays.fill(pronouns, "");

        int firstpronoun = 3;

        for (int i = 2; i < ele.length-1; i++) {
            String target = ele[i];
            pronouns[toIndex.get(target)] += toProunoun.get(target) + "-";
            if (toIndex.get(target) < firstpronoun) {
                firstpronoun = toIndex.get(target);
            }
        }

        String result = ele[ele.length-1];
        if (ele[0].endsWith("er") &&
                ele[1].equals("tu") && !(
                pronouns[firstpronoun].startsWith("a") ||
                pronouns[firstpronoun].startsWith("e") ||
                pronouns[firstpronoun].startsWith("i") ||
                pronouns[firstpronoun].startsWith("o") ||
                pronouns[firstpronoun].startsWith("u") ||
                pronouns[firstpronoun].startsWith("y")) && result.endsWith("s")) {
            result = result.substring(0, result.length()-1);     //removes s
        }

        result += "-";


        for (int i = 0; i < pronouns.length; i++) {
//            System.out.println(pronouns[i]);
            result += pronouns[i];
        }

        result = result.substring(0,result.length()-1);


        // ending: e a
        // starting: e y
        result = join(result);

        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        result += " !";


        System.out.println(result);
    }

    static String join(String s) {
        String contracts[] = "le la me te".split(" ");
        String vowels[] = "a e i o u y".split(" ");

        String trollcontracts[] = "moi toi".split(" ");


        for (String contract : contracts) {
            for (String vowel: vowels) {
                s = s.replace(contract + "-" + vowel, contract.substring(0,contract.length()-1) + "'" + vowel);
            }
        }

        for (String contract : trollcontracts) {
            for (String vowel: vowels) {
                s = s.replace(contract + "-" + vowel, contract.substring(0,contract.length()-2) + "'" + vowel);
            }
        }

        return s;

    }
}

package commands.list.hlfrench;


import net.dv8tion.jda.api.entities.MessageChannel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {

    static int currentIndex;
    static ArrayList<VerbData> verbBank = new ArrayList<>();

    public Tester() throws IOException {

//        Read("présent.csv");
//        Read("subjonctif.csv");
        Read("passécomposé.csv");
//        Read("conditionnel.csv");
        Read("imparfait.csv");
//        Read("futursimple.csv");

//        verbBank.add(new Verbs("présent", "", "je", "'ai", "avoir"));
//        verbBank.add(new Verbs("présent", "", "tu", "as", "avoir"));
//        verbBank.add(new Verbs("présent", "", "il", "a", "avoir"));
//        verbBank.add(new Verbs("présent", "", "nous", "avons", "avoir"));
//        verbBank.add(new Verbs("présent", "", "vous", "avez", "avoir"));
//        verbBank.add(new Verbs("présent", "", "elles", "ont", "avoir"));

    }

    private void Read(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String tense = br.readLine();

        String s[] = br.readLine().split(",");
        int length = Integer.parseInt(s[0]);
        String preconj = s[1];

        String[] pronouns = new String[length];
        for (int i = 0; i < length; i++) {
            pronouns[i] = s[i+2];
        }

        String line;
        while ((line = br.readLine()) != null)  {
            String[] parsedline = line.split(",");    // use comma as separator
            for (int i = 0; i < length; i++) {
                verbBank.add(new VerbData(tense, preconj, pronouns[i], parsedline[i+1], parsedline[0]));
            }
        }
}

    public static String prompt(MessageChannel channel, String prevResponse) {

        VerbData verbs = randomVerb();

        String tense = verbs.tense;
        String preconj = verbs.preconj;
        String pronoun = verbs.pronoun;
        String verb = verbs.verb;
        String infinitive = verbs.infinitive;

        String joint = joinApostrophe(preconj, pronoun);


        channel.sendMessage(String.format("%s\n%s: %s (%s)", prevResponse, tense, joint, infinitive)).queue();

        return joinApostrophe(joint, verb);
    }

    public static String joinApostrophe(String s1, String s2) {

        String joint = s1 + " " + s2;

        //french wtf
        if (s1.equals("elle") || s1.equals("a")) return joint.trim();

        //thinking is bad
        joint = replace(joint, "e", "a");
        joint = replace(joint, "e", "h");
        joint = replace(joint, "e", "i");
        joint = replace(joint, "e", "o");
        joint = replace(joint, "e", "e");
        joint = replace(joint, "e", "ê");
        joint = replace(joint, "e", "é");
        joint = replace(joint, "a", "a");
        joint = replace(joint, "a", "h");
        joint = replace(joint, "a", "i");
        joint = replace(joint, "a", "e");
        joint = replace(joint, "a", "ê");
        joint = replace(joint, "a", "é");

        return joint.trim();
    }

    public static String replace(String target, String s1, String s2) {
        return target.replace(s1 + " " + s2, "'" + s2);
    }




    public static VerbData randomVerb() {
        if (currentIndex <= 0) {
            currentIndex = verbBank.size();
            Collections.shuffle(verbBank);
        }
        currentIndex--;
        return verbBank.get(currentIndex);

    }
}

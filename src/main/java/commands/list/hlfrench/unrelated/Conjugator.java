package commands.list.hlfrench.unrelated;

public class Conjugator {
    public static void main(String args[]) {
        System.out.println("futur simple\n" +
                "8,,je,tu,il,elle,nous,vous,ils,elles");

//        avoir aur
//        etre ser
//        aller ir
//        venir viendr
//        faire fer
//        savoir saur
//        pouvoir pourr
//        devoir devr
//        vouloir voudr
//        falloir faudr
//        pleuvoir pleuvr
//        appeler appeller
//        s'ennuyer ennuier
//        jeter jetter
//        payer paier
//        envoyer enverr
//        acheter achèter
//        se promener promèner
//        se lever lèver
//        geler gèler
//        enlever enlèver
//        amener amèner
//        emmener emmèner
//        mener
//                semer
//        peser are like amener and emmener
//        mourir mourr
//        tenir tiendr
//        voir verr
//        valoir vaudr
//        recevoir recevr
//        s'apercevoir apercevr
//        s'asseoir assoir
        String raw = "avoir aur\n" +
                " être ser\n" +
                "        aller ir\n" +
                "        venir viendr\n" +
                "        faire fer\n" +
                "        savoir saur\n" +
                "        pouvoir pourr\n" +
                "        devoir devr\n" +
                "        vouloir voudr\n" +
                "        falloir faudr\n" +
                "        pleuvoir pleuvr\n" +
                "        appeler appeller\n" +
                "        ennuyer ennuier\n" +
                "        jeter jetter\n" +
                "        payer paier\n" +
                "        envoyer enverr\n" +
                "        acheter achèter\n" +
                "        promener promèner\n" +
                "        lever lèver\n" +
                "        geler gèler\n" +
                "        enlever enlèver\n" +
                "        amener amèner\n" +
                "        emmener emmèner\n" +
                "        mener mèner\n" +
                "        semer sèmer\n" +
                "        peser pèser\n" +
                "        mourir mourr\n" +
                "        tenir tiendr\n" +
                "        voir verr\n" +
                "        valoir vaudr\n" +
                "        recevoir recevr\n" +
                "        apercevoir apercevr\n" +
                "        asseoir assoir".replace("\n"," ");


        for (int i = 0; i < 100; i++) {
            raw = raw.replace("  ", " "); //get rid of double spaces
        }

        String raw2[] = raw.split(" ");

        String infinitive[] = new String[raw2.length/2];
        String stem[] = new String[raw2.length/2];

        System.out.println(raw2.length);

        for (int i = 0; i < raw2.length; i++) {
            if (i%2 == 0) {
                infinitive[i/2] = raw2[i].replace("\n"," ");;
            } else {
                stem[i/2] = raw2[i].replace("\n"," ");;
            }
        }






        for (int i = 0; i < infinitive.length; i++) {
            String stems = stem[i].trim();
            System.out.print(infinitive[i].trim()+",");
            System.out.print(stems + "ai,");
            System.out.print(stems + "as,");
            System.out.print(stems + "a,");
            System.out.print(stems + "a,");
            System.out.print(stems + "ons,");
            System.out.print(stems + "ez,");
            System.out.print(stems + "ont,");
            System.out.println(stems + "ont");

        }
    }
}

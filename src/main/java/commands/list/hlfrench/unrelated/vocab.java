package commands.list.hlfrench.unrelated;

import java.util.*;

public class vocab {
    //

    public static void main(String args[]) {
        String[] s = ("volonté\n" +
                "willpower\n" +
                "chute\n" +
                "fall\n" +
                "baril\n" +
                "barrel\n" +
                "pourtant\n" +
                "yet\n" +
                "légion \n" +
                "army\n" +
                "piscine\n" +
                "pool\n" +
                "wallou\n" +
                "nothing\n" +
                "verre\n" +
                "glass\n" +
                "chenille \n" +
                "caterpillar track\n" +
                "marrant\n" +
                "funny\n" +
                "piste\n" +
                "track\n" +
                "aménagé\n" +
                "convert\n" +
                "voile\n" +
                "sail\n" +
                "voire\n" +
                "indeed\n" +
                "balade\n" +
                "walk\n" +
                "pirogue\n" +
                "dugout\n" +
                "dingue\n" +
                "crazy\n" +
                "fansfans\n" +
                "enfants\n" +
                "bousculer\n" +
                "shove\n" +
                "portillon\n" +
                "barrier\n" +
                "doigt\n" +
                "finger\n" +
                "pire \n" +
                "worse\n" +
                "piger\n" +
                "understand\n" +
                "sinistrée\n" +
                "victim\n" +
                "contrée\n" +
                "region\n" +
                "détente \n" +
                "relaxation\n" +
                "farniente\n" +
                "idleness\n" +
                "vierge\n" +
                "virgin\n" +
                "bord\n" +
                "edge\n" +
                "mer\n" +
                "sea\n" +
                "barque\n" +
                "boat\n" +
                "écran\n" +
                "screen\n" +
                "manège\n" +
                "merry go round\n" +
                "boulot\n" +
                "job\n" +
                "chômage\n" +
                "unemployed\n" +
                "émule\n" +
                "imitator\n" +
                "insolite\n" +
                "bizarre\n" +
                "crapahuter\n" +
                "explore\n" +
                "grotte\n" +
                "cave\n" +
                "aguerri\n" +
                "veteran/seasoned\n" +
                "hebdomadaire\n" +
                "weekly\n" +
                "defi\n" +
                "challenge\n" +
                "clôturer\n" +
                "enclosed\n" +
                "franchir\n" +
                "cross/breakthrough\n" +
                "téméraire\n" +
                "audacious\n" +
                "saut\n" +
                "drop\n" +
                "toboggan\n" +
                "slide\n" +
                "arpenter\n" +
                "stride across\n" +
                "accéder\n" +
                "reach\n" +
                "bouillonnant\n" +
                "bubble\n" +
                "pote\n" +
                "mate\n" +
                "barreur\n" +
                "helmsman\n" +
                "rigolades\n" +
                "laughs\n" +
                "jambon\n" +
                "ham\n" +
                "lessiveuse\n" +
                "wash boiler\n" +
                "glisse\n" +
                "board sports\n" +
                "melange\n" +
                "mixing\n" +
                "cerf-volant\n" +
                "kite\n" +
                "\n" +
                "\n" +
                "douter\n" +
                "doubt\n" +
                "tumultueux\n" +
                "turbulent\n" +
                "barouder\n" +
                "See action\n" +
                "souhaiter\n" +
                "want\n" +
                "baliser\n" +
                "mark\n" +
                "sacré\n" +
                "sacred\n" +
                "jadis\n" +
                "formerly\n" +
                "enfouir\n" +
                "bury\n" +
                "cordière\n" +
                "tailpiece\n" +
                "attrister\n" +
                "sadden\n" +
                "maigre\n" +
                "skinny\n" +
                "tôle\n" +
                "Sheet metal\n" +
                "tenue\n" +
                "outfit\n" +
                "nourrir\n" +
                "feed\n" +
                "roulette\n" +
                "wheel\n" +
                "thon\n" +
                "tuna\n" +
                "ferveur\n" +
                "enthusiastic\n" +
                "panne\n" +
                "failure\n" +
                "insulaire\n" +
                "island\n" +
                "barboter\n" +
                "splash around\n" +
                "fêtard\n" +
                "party-animal\n" +
                "invétéré\n" +
                "engrained\n" +
                "itinéraire\n" +
                "path\n" +
                "routard\n" +
                "backpacker\n" +
                "revendication\n" +
                "demand\n" +
                "attirail\n" +
                "gear\n" +
                "imprévu\n" +
                "unseen\n" +
                "boutique\n" +
                "shop\n" +
                "tresors\n" +
                "treasure\n" +
                "cesser\n" +
                "stop\n" +
                "craquer\n" +
                "split\n" +
                "horaire\n" +
                "time\n" +
                "côtière\n" +
                "coastal\n" +
                "maraes\n" +
                "sacred place\n" +
                "idem\n" +
                "likewise\n" +
                "ad hoc\n" +
                "suitable\n" +
                "paysage\n" +
                "landscape").split("\n");

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < s.length/2; i++) {
            map.put(s[i*2], s[i*2+1]);
        }

        Collections.shuffle(Arrays.asList(map));
        Collections.shuffle(Arrays.asList(map));
        Collections.shuffle(Arrays.asList(map));
        Collections.shuffle(Arrays.asList(map));

       for (String key : map.keySet()) {
           System.out.println(key + "\t\t\t\t\t\t\t\t" + map.get(key));

//               System.out.println();

       }



    }
}

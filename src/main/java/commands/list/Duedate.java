package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Duedate extends CommandManager {

    @Override
    public String name() {
        return "duedate";
    }

    @Override
    public String description() {
        return "due date of important IB submissions";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[]{new Duedate.OptionID()};
    }

    HashMap<String, Long> dates = new HashMap<String, Long>();

    public Duedate() {
        dates.put("mathia", 1644641700000l);
        dates.put("physicsia", 1646888100000l);
        dates.put("tax", 0l);
        dates.put("hlessay", 1643810400000l);
        dates.put("ee", 1669956900000l);
        dates.put("chemia", 1649130900000l);
        dates.put("exhibition", 1649304000000l);
//        dates.put("")

    }

    @Override
    public void execute(SlashCommandEvent event) {
        String key = event.getOption(options()[0].name()).getAsString();
        String s = toStr();

        if (!dates.containsKey(key)) {
            event.reply(s).queue();
            return;
        }

        long l = dates.get(key) - System.currentTimeMillis();
        event.reply(key + toTime(l)).queue();

    }

    private String toTime(long l) {
        String S = "";
        if (l < 0) S = " is overdue by ";
        else S = " is due in ";

        l = Math.abs(l);

        int d = (int) (l/86400000);
        l = l%86400000;
        int h = (int) (l/3600000);
        l = l%3600000;
        int m = (int) (l/60000);
        l = l%60000;
        int s = (int) (l/1000);
        l = l%1000;

        boolean pastDigit = false;
        if (d != 0) {
            S += d+" day" + (d!=1?"s ":" ");
            pastDigit = true;
        }
        if (h != 0 || pastDigit) {
            S += h+" hour" + (h!=1?"s ":" ");
            pastDigit = true;
        }
        if (m != 0 || pastDigit) {
            S += m+" minute" + (m!=1?"s ":" ");
            pastDigit = true;
        }
        if (s != 0 || pastDigit) {
            S += s+" second" + (s!=1?"s ":" ");
            pastDigit = true;
        }
        if (l != 0 || pastDigit) {
            S += l+" ± 727 milliseconds";
        }

        return S;
    }

    public String toStr() {
        String S = "";
        for (String s : dates.keySet()) {
            S += s + "; ";
        }
        return S;
    }



    public class OptionID extends OptionDataManager {
        @Override
        public String name() {
            return "assignment";
        }

        @Override
        public String description() {
            return "IB assignments/exams";
        }

        @Override
        public OptionType type() {
            return STRING;
        }

        @Override
        public boolean isRequired() {
            return true;
        }
    }

}

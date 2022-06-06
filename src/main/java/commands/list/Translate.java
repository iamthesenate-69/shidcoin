package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Translate extends CommandManager {

    @Override
    public String name() {
        return "translate";
    }

    @Override
    public String description() {
        return "google translate but based";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[]{new LangFrom(), new LangTo(), new Text()};
    }

    @Override
    public void execute(SlashCommandEvent event) throws IOException {
        String langFrom = event.getOption(options()[0].name()).getAsString();
        String langTo = event.getOption(options()[1].name()).getAsString();
        String text = event.getOption(options()[2].name()).getAsString();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(String.format("Translated Text (%s):", langTo))
                .setDescription(translate(langFrom, langTo, text))
                .setFooter(String.format("Original (%s): %s", langFrom, text));
        event.replyEmbeds(eb.build()).queue();

    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // subjected to change
        String urlStr = "https://script.google.com/macros/s/AKfycbxfhSyYMlzesiMvHUDGTXpbzKKp4G_k1rNqu9puy-WUCFh14Qjn2XHU3szu8g6wBVhE/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString().replace("&#39;",  "'");
    }

    public class LangFrom extends OptionDataManager {
        @Override
        public String name() {
            return "langfrom";
        }

        @Override
        public String description() {
            return "The language it is currently in";
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

    public class LangTo extends OptionDataManager {
        @Override
        public String name() {
            return "langto";
        }

        @Override
        public String description() {
            return "The language to be translated to";
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

    public class Text extends OptionDataManager {
        @Override
        public String name() {
            return "text";
        }

        @Override
        public String description() {
            return "The text to be translated";
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

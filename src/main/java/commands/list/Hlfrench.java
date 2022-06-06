package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import commands.list.hlfrench.UserData;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.io.IOException;
import java.util.HashMap;

public class Hlfrench extends CommandManager {

    public static HashMap<String, UserData> hlfrenchParticipants = new HashMap<>();

    @Override
    public String name() {
        return "hlfrench";
    }

    @Override
    public String description() {
        return "Bienvenue chez vous au Canada!";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[]{new Toggle()};
    }

    @Override
    public void execute(SlashCommandEvent event) throws IOException {
        String userid = event.getMember().getId();
        boolean toggle = event.getOption(options()[0].name()).getAsBoolean();
        if (toggle) {
            if (!hlfrenchParticipants.containsKey(userid)) {
                regenPrompt(userid, event.getChannel(), "");
                event.reply("Session started").queue();
            } else {
                event.reply("You are already in a session").queue();
            }
        } else {
            if (hlfrenchParticipants.containsKey(userid)) {
                hlfrenchParticipants.remove(userid);
                event.reply("Session ended").queue();
            } else {
                event.reply("You are not in a session").queue();
            }
        }

    }

    //might be slow due to reinstancing testdata every message
    public static void regenPrompt(String userid, MessageChannel channel, String prevResponse) {
        hlfrenchParticipants.put(userid, new UserData(channel, prevResponse));
    }

    public class Toggle extends OptionDataManager {

        @Override
        public String name() {
            return "event";
        }

        @Override
        public String description() {
            return "true/false";
        }

        @Override
        public OptionType type() {
            return OptionType.BOOLEAN;
        }

        @Override
        public boolean isRequired() {
            return true;
        }
    }
}

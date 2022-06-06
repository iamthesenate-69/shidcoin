package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.text.SimpleDateFormat;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class FindUser extends CommandManager {

    @Override
    public String name() {
        return "userinfo";
    }

    @Override
    public String description() {
        return "Gathers data about the user";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[]{new OptionID()};
    }

    @Override
    public void execute(SlashCommandEvent event) {
        String id = event.getOption(options()[0].name()).getAsString();
        User target = event.getJDA().getUserById(id);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(target.getName())
                .setImage(target.getEffectiveAvatarUrl())
                .setDescription(getMutualGuilds(target))
                .setFooter(target.getTimeCreated().toString(), target.isBot()?"https://cdn.emojidex.com/emoji/seal/dbottag.png?1543873142"
                        :"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F0%2F02%2FTransparent_square.svg%2F1024px-Transparent_square.svg.png&f=1&nofb=1");

        event.replyEmbeds(eb.build()).queue();
    }

    private CharSequence getMutualGuilds(User target) {
        String s = "";
        for (Guild g : target.getMutualGuilds())
            s += g.getName() + ", ";
        return s.substring(0, s.length()-2);
    }

    private String getDate(User target) {
        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z").format(target.getTimeCreated());
    }

    public class OptionID extends OptionDataManager {
        @Override
        public String name() {
            return "userid";
        }

        @Override
        public String description() {
            return "The Discord ID of the User";
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



package commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;

public abstract class CommandManager {
    public abstract String name();
    public abstract String description();
    public abstract OptionDataManager[] options();
    public abstract void execute(SlashCommandEvent event) throws IOException;



}

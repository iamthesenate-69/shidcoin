package commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class OptionDataManager {
    public abstract String name();
    public abstract String description();
    public abstract OptionType type();
    public abstract boolean isRequired();

}
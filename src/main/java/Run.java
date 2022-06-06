import commands.CommandManager;
import commands.list.*;
import commands.OptionDataManager;
import commands.list.Hlfrench;
import commands.list.hlfrench.Tester;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

public class Run {
    //bottoken
    private static String token = "NzMwMTA3MTc5MzUyMTk1MTM0.XwSrTA.BxmqvWjYnJTsR0780IqtWtnMoA8";
    //guild id
    private static String guildid = "699278323896811551";

    public static HashMap<String, CommandManager> commandManagerTreeMap = new HashMap<>();

    //telescreen
    public static HashMap<String, Integer> telescreenTreeMap = new HashMap<>();
    public static String[] response = {"we are the dead", "it was behind the picture", "now they can see us", "the house is surrounded", "i suppose we may as well say goodbye"};

    public static JDA jda;
    
    public static JDA getJDA() {
        return jda;
    }

    public static void main(String args[]) throws LoginException, InterruptedException, IOException {
        //so it doesnt error on start up
        BasicConfigurator.configure();

        jda = JDABuilder.create(token, EnumSet.allOf(GatewayIntent.class))
                .addEventListeners(new EventManager())
                .setActivity(Activity.playing("amogOS"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
        


//        CONFETTI("ODU0NTM3NzIzMTQ3MDU5MjAw.YMlYRQ.uOAXpUEhEFMDFBiQ86oE_nh-D60");
//        CONFETTI("ODU0NTM3Nzg3NDY1MTM4MTk2.YMlYVQ.hSHAQbBdN6T5W6KMIyASq8-ynF0");
//        CONFETTI("ODU0NTM3ODM1MzE4MjE0Njc3.YMlYYA.rlrEAqCb6g-NKtSc6P1tltFOYaI");
//        CONFETTI("ODU0NTM3OTA0ODE0MDMwODg3.YMlYcQ.cubVZ3HKzd6mjPAWrwurhgS30Z8");
//        CONFETTI("ODU0NTM3OTQ5Mjg1NTgwODAw.YMlYew.OMisyIG2RDN1U3pa4PlpG-4xvkU");
//        CONFETTI("ODU0NTM4MDAyMjMxMDY2Njg0.YMlYiA.g5s01odvJvTG5d0NU-FMDz0Z4yk");

        jda.awaitReady();

        CommandListUpdateAction commands = jda.getGuildById(guildid).updateCommands();
        CommandManager[] commandManager = {new FindUser(), new SocialCredit(), new Translate(), new KD(), new Duedate(), new Hlfrench()};
        for (CommandManager cm : commandManager) {
            commandManagerTreeMap.put(cm.name(), cm);
            CommandData cd = new CommandData(cm.name(),cm.description());
            for (OptionDataManager odm : cm.options()) {
                cd.addOptions(new OptionData(odm.type(), odm.name(), odm.description()).setRequired(odm.isRequired()));
            }
            commands.addCommands(cd);
        }
        commands.queue();


//        new ConsolePanel();


       createConsole();
        new Tester();


    }



    private static void createConsole() {

    }


}

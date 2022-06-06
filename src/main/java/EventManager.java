import commands.list.Hlfrench;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EventManager extends ListenerAdapter {

    class Helper extends TimerTask {
        List<TextChannel> tc;
        Helper(List<TextChannel> textChannels) {
            tc = textChannels;
        }
        public void run() {
            for (TextChannel channel : tc) {
                channel.sendTyping().queue();
            }
        }
    }
    @Override
    public void onReady(ReadyEvent event) {




//                Timer timer = new Timer();
//
//                // Helper class extends TimerTask
//                TimerTask task = new Helper( event.getJDA().getGuildById("699278323896811551").getTextChannels());
//
//                /*
//                 *  Schedule() method calls for timer class.
//                 *  void schedule(TimerTask task, Date firstTime, long period)
//                 */
//
//                timer.schedule(task, 1, 10100);
//
//        }

//        while (true) {
//            Member m = event.getJDA().getGuildById("699278323896811551").getMemberById("376857210485080064");
////            System.out.println(m.getOnlineStatus());
//            if (!m.getOnlineStatus().equals(OnlineStatus.OFFLINE)) {
//                event.getJDA().getGuildById("699278323896811551").getTextChannelById("876646221228433458").sendMessage("ANDY IS ONLINE <@376857210485080064>").queue();
//            }
        }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

    System.out.println(event.getMessage().getContentRaw());



        String userid = event.getAuthor().getId();
        if (Hlfrench.hlfrenchParticipants.containsKey(userid)) {
            String correct = Hlfrench.hlfrenchParticipants.get(userid).getCorrect();
            String response;
            if (event.getMessage().getContentRaw().equals(correct)) {
                response = ":white_check_mark: Vous pensez, donc vous avez raison.";

            } else {
                response = ":negative_squared_cross_mark: IB Niveau 1 - Bonne Réponse: "+correct;
            }
            Hlfrench.regenPrompt(userid, event.getChannel(), response);
        }
//        if (event.isFromGuild() == true) return;
//
//        if (event.getMessage())


//           if (ConstoleFrame.LoggingEnabled)
//               ConsoleFrame.console.append("\n"+event.getMessage().getContentRaw());
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String command = event.getName();

//        execute(event);

//        if (Run.commandManagerTreeMap.containsKey(command) && ConsoleFrame.SlashCommandsEnabled) {
        if (Run.commandManagerTreeMap.containsKey(command)) {
            try {
                Run.commandManagerTreeMap.get(command).execute(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}

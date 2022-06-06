package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import java.util.Random;



public class SocialCredit extends CommandManager {
    @Override
    public String name() {
        return "socialcredit";
    }

    @Override
    public String description() {
        return "Your social credit score (社会积分)";
    }

    @Override
    public OptionDataManager[] options() {
        // no args
        return new OptionDataManager[0];
    }

    @Override
    public void execute(SlashCommandEvent event) {
        int n = new Random().nextInt(1000);
        if (event.getMember().getId().equals("372123318167273502")) n = 0;
        if (n == 0) {
            event.reply("\uD83D\uDD34 ATTENTION CITIZEN! 市民请注意! \uD83D\uDD34 \n" +
                    "\n" +
                    "This is the Central Intelligentsia of the Chinese Communist Party. (\uD83C\uDDE8\uD83C\uDDF3)\n" +
                    "您的 Internet 浏览器历史记录和活动引起了我们的注意。\n" +
                    "因此，您的个人资料中的 6.022 x 10^23 ( -6.022 x 10^23 Social Credits) 个社会积分将打折。\n" +
                    "\uD83D\uDD34 DO NOT DO THIS AGAIN! 不要再这样做! \uD83D\uDD34\n" +
                    "If you not hesitate, more Social Credits ( - Social Credits )will be subtracted from your profile, resulting in the subtraction of ration supplies. (由人民供应部重新分配 CCP)\n" +
                    "You'll also be sent into a re-education camp in the Xinjiang Uyghur Autonomous Zone.\n" +
                    "如果您毫不犹豫，更多的社会信用将从您的个人资料中打折，从而导致口粮供应减少。\n" +
                    "您还将被送到新疆维吾尔自治区的再教育营。\n" +
                    "\n" +
                    "为党争光! Glory to the PRC!").queue(message -> message.retrieveOriginal().queue(message1 -> message1.pin().queue()));


            event.getMember().ban(0).queue();
        }
        event.reply("Your social credit score : " + n).queue();

    }





}

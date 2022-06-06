package commands.list.hlfrench;

import net.dv8tion.jda.api.entities.MessageChannel;

public class UserData {
    MessageChannel channel;
    String correctVerb;
    public UserData(MessageChannel _channel, String prevResponse) {
        channel = _channel;
        correctVerb = Tester.prompt(channel, prevResponse);
    }

    public String getCorrect() {
        return correctVerb;
    }
}

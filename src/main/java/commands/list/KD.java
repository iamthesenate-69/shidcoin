package commands.list;

import commands.CommandManager;
import commands.OptionDataManager;
import commands.lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.io.IOException;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;


public class KD extends CommandManager {

    @Override
    public String name() {
        return "kd";
    }

    @Override
    public String description() {
        return "kd more like dead";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[]{new Song()};
    }


    public KD() {
        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);

    }



    @Override
    public void execute(SlashCommandEvent event) throws IOException {
        String s = event.getOption(options()[0].name()).getAsString();
        AudioManager manager = event.getGuild().getAudioManager();

        if (s.equals("stop")) {
            event.getChannel().sendMessage("Stopped and cleared the track").queue();
            getGuildAudioPlayer(event.getGuild()).scheduler.nextTrack();
            manager.closeAudioConnection();
        }
        else if (s.equals("skip")) {
            skipTrack(event);
        }
        else {
            manager.openAudioConnection(event.getMember().getVoiceState().getChannel());
            loadAndPlay(event, event.getOption(options()[0].name()).getAsString());
        }



    }


    public class Song extends OptionDataManager {
        @Override
        public String name() {
            return "songid";
        }

        @Override
        public String description() {
            return "The song to play";
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


        private AudioPlayerManager playerManager;
        private Map<Long, GuildMusicManager> musicManagers;


        private synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
            long guildId = Long.parseLong(guild.getId());
            GuildMusicManager musicManager = musicManagers.get(guildId);

            if (musicManager == null) {
                musicManager = new GuildMusicManager(playerManager);
                musicManagers.put(guildId, musicManager);
            }

            guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

            return musicManager;
        }


        private void loadAndPlay(SlashCommandEvent event, final String trackUrl) {
            GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild());

            playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
                @Override
                public void trackLoaded(AudioTrack track) {
                    event.reply("Adding to queue " + track.getInfo().title).queue();

                    play(event.getGuild(), musicManager, track);
                }

                @Override
                public void playlistLoaded(AudioPlaylist playlist) {
                    AudioTrack firstTrack = playlist.getSelectedTrack();

                    if (firstTrack == null) {
                        firstTrack = playlist.getTracks().get(0);
                    }

                    event.reply("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();

                    play(event.getGuild(), musicManager, firstTrack);
                }

                @Override
                public void noMatches() {
                    event.reply("No matches)").queue();
//
//                    AudioManager manager = event.getGuild().getAudioManager();
//                    manager.openAudioConnection(event.getMember().getVoiceState().getChannel());
//                    loadAndPlay(event, "ytsearch:" + event.getOption(options()[0].name()).getAsString());


                }

                @Override
                public void loadFailed(FriendlyException exception) {
                    event.reply("Could not play: " + exception.getMessage()).queue();
                }
            });
        }

        private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
            musicManager.scheduler.queue(track);
        }

        private void skipTrack(SlashCommandEvent event) {
            GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild());
            musicManager.scheduler.nextTrack();

            event.reply("Skipped to next track.").queue();
        }

    }


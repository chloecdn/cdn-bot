package de.chloedev.cdnbot.discord.command.impl;

import de.chloedev.cdnbot.discord.command.Command;
import kotlin.text.Charsets;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class DoggoCommand implements Command {
    @Override
    public String getName() {
        return "doggo";
    }

    @Override
    public void onReceive(String[] args, MessageChannelUnion channel, User author) {
        if (args.length != 0) {
            channel.sendMessage("Invalid Arguments.").queue();
        }
        try {
            URL url = new URI("https://api.thedogapi.com/v1/images/search").toURL();
            JSONArray o = new JSONArray(new String(url.openStream().readAllBytes(), Charsets.UTF_8));
            channel.sendMessage(o.getJSONObject(0).getString("url")).queue();
        } catch (URISyntaxException | IOException e) {
            channel.sendMessage(Arrays.toString(e.getStackTrace()).substring(0, 1999)).queue();
        }
    }
}

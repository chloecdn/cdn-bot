package de.chloedev.cdnbot.discord.command;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public interface Command {

    String getName();

    void onReceive(String[] args, MessageChannelUnion channel, User author);
}

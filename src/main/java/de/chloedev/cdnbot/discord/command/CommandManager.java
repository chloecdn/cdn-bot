package de.chloedev.cdnbot.discord.command;

import de.chloedev.cdnbot.discord.command.impl.CatCommand;
import de.chloedev.cdnbot.discord.command.impl.DoggoCommand;
import de.chloedev.cdnbot.discord.command.impl.FabricDocsCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    private final List<Command> commands;

    public CommandManager() {
        this.commands = new ArrayList<>() {{
            this.addAll(List.of(new FabricDocsCommand(), new CatCommand(), new DoggoCommand()));
        }};
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getMessage().getContentRaw().startsWith(";")) return;
        String message = e.getMessage().getContentRaw();
        String[] args = message.substring(1).split(" ");
        for (Command c : this.commands) {
            if (!args[0].equals(c.getName())) continue;
            args = ArrayUtils.remove(args, 0);
            c.onReceive(args, e.getChannel(), e.getAuthor());
            break;
        }
    }

    public List<Command> getCommands() {
        return commands;
    }
}

package de.chloedev.cdnbot.discord;

import de.chloedev.cdnbot.discord.command.CommandManager;
import de.chloedev.cdnbot.io.FileConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {

    private JDA jda;
    private FileConfiguration config;

    public Bot() {
        this.config = new FileConfiguration("./config/config.properties");
        try {
            JDABuilder builder = JDABuilder.createDefault(config.get("application_token", "null"))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(
                            new CommandManager()
                    );
            this.jda = builder.build().awaitReady();
            System.out.println("Bot initialized.");
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.jda = null;
        }
    }

    public boolean isRunning() {
        return this.jda != null;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public JDA getJDA() {
        return jda;
    }
}

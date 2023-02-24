package de.chloedev.cdnbot;

import de.chloedev.cdnbot.discord.Bot;

public class Main {

    private Bot bot;
    private static Main instance;

    public Main() {
        instance = this;
        Thread t = new Thread(() -> {
            this.bot = new Bot();
        }, "[Bot] Main-Thread");
        t.start();
    }

    public static void main(String[] args) {
        new Main();
    }

    public Bot getBot() {
        return bot;
    }

    public static Main getInstance() {
        return instance;
    }
}
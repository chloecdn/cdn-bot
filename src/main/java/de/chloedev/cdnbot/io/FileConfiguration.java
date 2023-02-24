package de.chloedev.cdnbot.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileConfiguration {

    private Properties properties;
    private File file;

    public FileConfiguration(String path) {
        try {
            this.file = new File(path);
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            FileInputStream in = new FileInputStream(this.file);
            this.properties = new Properties();
            this.properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key, String def) {
        return this.properties.getProperty(key, def);
    }

    public String get(String key) {
        return this.get(key, null);
    }

    public void set(String key, String value) {
        try {
            this.properties.setProperty(key, value);
            this.properties.store(new FileOutputStream(this.file), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}

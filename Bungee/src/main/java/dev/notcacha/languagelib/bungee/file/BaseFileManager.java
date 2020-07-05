package dev.notcacha.languagelib.bungee.file;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BaseFileManager implements FileManager {
    private File file;
    private Configuration configuration;
    private final Plugin plugin;
    private final String outputFile;
    private String inputFile;

    public BaseFileManager(Plugin plugin,
                           String outputFile) {
        this.plugin = plugin;
        this.outputFile = outputFile;
    }

    public BaseFileManager(Plugin plugin,
                           String outputFile,
                           String inputFile) {
        this.plugin = plugin;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
    }

    @Override
    public Configuration getFile() {
        if (configuration == null){
            reloadFile();
        }

        return configuration;
    }

    @Override
    public void reloadFile() {
        if (configuration == null){
            file = new File(plugin.getDataFolder(), outputFile);
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFile() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDefaultFile(String archive, boolean silent) {
        if (file == null) {
            file = new File(plugin.getDataFolder(), outputFile);
        }

        if (!file.exists()) {
            try {
                if (!silent) {
                    plugin.getLogger().info(ChatColor.RED + archive + ChatColor.GRAY + " is not founded, creating archive...");
                }

                if (inputFile == null) {
                    file.createNewFile();
                    return;
                }


                InputStream inputStream = plugin.getResourceAsStream(inputFile);
                if (inputStream == null) {
                    throw new FileNotFoundException("The file " + inputFile + "was not founded in plugin files");
                }

                Configuration inputConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(inputStream);

                if(file.isDirectory()){
                    file.delete();
                }

                if(!file.exists()){
                    file.createNewFile();
                }

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(inputConfig, file);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        reloadFile();
    }

    @Override
    public void loadDefaultFile(boolean silent) {
        loadDefaultFile(outputFile, silent);
    }
}

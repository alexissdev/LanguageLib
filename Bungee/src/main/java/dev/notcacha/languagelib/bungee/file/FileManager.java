package dev.notcacha.languagelib.bungee.file;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public interface FileManager {

    /**
     * Get the file {@link Configuration}
     *
     * @return the file object.
     */
    Configuration getFile();

    /**
     * Reload the config file.
     */
    void reloadFile();

    /**
     * Save changes in the file.
     */
    void saveFile();

    /**
     * Create file in the directory if not exist.
     *
     * @param archive the nickname of file.
     * @param silent  set to false if yo seen the message
     */
    void loadDefaultFile(String archive, boolean silent);

    /**
     * Create the file without nickname if not exist in the directory.
     *
     * @param silent set to false if yo seen the message
     */
    void loadDefaultFile(boolean silent);


    /*
     * Set the default data to file
     */
    //void setDefaultData(Map<String, String> data);

    /*
     * Get default data of the config
     * @return the default data
     */
    //@NotNull Map<String, String> getDefaultData();
    /**
     * Strip all colors in the provided String
     * @param path the path of the String in the config to strip colors
     * @return the value of path without colors
     */
    default String getStripedString(String path) {
        return ChatColor.stripColor(getColouredString(path));
    }

    /**
     * Add color to the string provided. See {@link ChatColor}
     * @param path the path of the String in the config to add colors
     * @return the value of the path with colors
     */
    default String getColouredString(String path){
        return ChatColor.translateAlternateColorCodes('&', getString(path));
    }

    /**
     * Get the value of the path provided
     * @param path the path to obtain the String
     * @return the value of path in the config
     */
    default String getString(String path){
        return getFile().contains(path) ? getFile().getString(path) : "";
    }

}

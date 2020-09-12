package dev.notcacha.languagelib.file;

import java.util.List;

public interface LanguageFile {

    /**
     * @return a simple string obtained from a file, using reference {@param path} to identify it
     */

    String getString(String path);

    /**
     * @return a list of strings obtained from a file, using reference {@param path} to identify it
     */

    List<String> getList(String path);

}

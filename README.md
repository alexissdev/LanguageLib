# LanguageLib

LanguageLib is a simple language system library, what is sought is to facilitate the use of multi-languages ​​in the plugins of the programmer who wants to use this library.

# Important
to be able to use this library you will have to count the project and install it on your pc with 'mvn install'

# Example of how to use the library
## Dependencies

```pom
    /* BUKKIT */
    <dependency>
       <groupId>dev.notcacha</groupId>
       <artifactId>LanguageLib-Bukkit</artifactId>
       <version>1.0-SNAPSHOT</version>
    </dependency>

    /* BungeeCord */
    <dependency>
       <groupId>dev.notcacha</groupId>
       <artifactId>LanguageLib-Bungee</artifactId>
       <version>1.0-SNAPSHOT</version>
    </dependency>
```

I would like to say that this library depends on whether we want to use it in Bukkit or BungeeCord it will depend on a class, on the part of "Bukkit" they have to use a Class that extends from "YamlConfiguration",
and on the part of BungeeCord a class "Configuration" that is of BungeeCord!

If you do not have any classes of this styles, here is one for Bukkit and BungeeCord

Bukkit Configuration Class:
https://pastebin.com/aQptr4DJ

BungeeCord Configuration Class:
https://pastebin.com/U2qsLNQv


# Bukkit Example

```java
    public class Main extends JavaPlugin {

        private Configuration en_language;
        private Configuration es_language;
        private LanguageLib languageLib;
        
        @Override
        public void onEnable() {
            this.en_language = new Configuration(this, "en_language.yml");
            this.es_language = new Configuration(this, "es_language.yml");
            /* *
            * The "LanguageLib" parameters is the default language
            * The first parameter is the name of the language
            * The second parameter is the class that extends "Yaml Configuration"
            */
            languageLib = new LanguageLib("EN", en_language);
            languageLib.getTranslateManager().addFile("ES", es_language);
            languageLib.getTranslateManager().getTranslate("messages.test").ifPresent(message -> {
                getLogger().info(setVariable("%test%", "testing set variable").getMessage("EN"));
            });
        }       
    }   
```

# BungeeCord Example

```java
    public class Main extends Plugin {
        
        private FileManager en_language;
        private FileManager es_language;

        @Override
        public void onEnable() {
            registerFiles();
            /* *
            * The "LanguageLib" parameters is the default language
            * The first parameter is the name of the language
            * The second parameter has to be a class "Configuration" of BungeeCord
            */
            languageLib = new LanguageLib("EN", en_language.getFile());
            languageLib.getTranslateManager().addFile("ES", es_language.getFile());
            languageLib.getTranslateManager().getTranslate("messages.test").ifPresent(message -> {
                getLogger().info(setVariable("%test%", "testing set variable").getMessage("EN"));
            });
        }
    
        private void registerFiles() {
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdirs();
            }
            this.en_language = new BaseFileManager(this, "en_lang.yml", "en_lang.yml");
            this.en_language.loadDefaultFile(false);
            this.es_language = new BaseFileManager(this, "es_lang.yml", "es_lang.yml");
            this.es_language.loadDefaultFile(false);
        }   

    }
```


# LanguageLib

LanguageLib is a simple language system library, what is sought is to facilitate the use of multi-languages ​​in the plugins of the programmer who wants to use this library.

# Important
to be able to use this library you will have to count the project and install it on your pc with 'mvn install'

# Dependencies

```pom
    /* BUKKIT */
    <dependency>
       <groupId>dev.notcacha</groupId>
       <artifactId>LanguageLib-Bukkit</artifactId>
       <version>1.2-SNAPSHOT</version>
    </dependency>

    /* BungeeCord */
    <dependency>
       <groupId>dev.notcacha</groupId>
       <artifactId>LanguageLib-Bungee</artifactId>
       <version>1.2-SNAPSHOT</version>
    </dependency>
```

# Data to emphasize
Depending on the module they use, it brings its respective class to create its YAML files, obviously they are optional.
The class in the Bukkit module is called "Configuration" and in the Bungee module they are called "FileManager" and "BaseFileManager".

# Important fact
in case you want to use your own classes, the bukkit module depends on a class that extends "YamlConfiguration",
and the Bungee module depends on a "Configuration" class that defaults to BungeeCord

# Bukkit Example

```java
    public class Main extends JavaPlugin {

        private Configuration en_language;
        private Configuration es_language;
        private BukkitLanguageLib<YamlConfiguration> bukkitLanguageLib;
        
        @Override
        public void onEnable() {
            this.en_language = new Configuration(this, "en_language.yml");
            this.es_language = new Configuration(this, "es_language.yml");
            /* *
            * The "BukkitLanguageLib" parameters is the default language
            * The first parameter is the name of the language
            * The second parameter is the class that extends "Yaml Configuration"
            */
            bukkitLanguageLib = new BukkitLanguageLib<>("EN", en_language);
            bukkitLanguageLib.getTranslateManager().addFile("ES", es_language);
            /* *
            * This is a simple example for a simple path that is 1 single string
            */
            bukkitLanguageLib.getTranslateManager().getTranslate("messages.test").ifPresent(message -> {
                getLogger().info(message.setVariable("%test%", "testing set variable").getMessage("EN"));
            });
            /* *
            * This is an example in any case we want to use a list
            */
            bukkitLanguageLib.getTranslateManager().getTranslate("Messages.apagando-list").ifPresent(message -> {
                message.setVariable("%test%", "testing set variable").getMessages("ES").forEach(resultMessage -> getLogger().info(resultMessage));
            });
        }       
    }   
```

# BungeeCord Example

```java
    public class Main extends Plugin {
        
        private FileManager en_language;
        private FileManager es_language;
        private BungeeLanguageLib<Configuration> bungeeLanguageLib;

        @Override
        public void onEnable() {
            registerFiles();
            /* *
            * The "BungeeLanguageLib" parameters is the default language
            * The first parameter is the name of the language
            * The second parameter has to be a class "Configuration" of BungeeCord
            */
            bungeeLanguageLib = new BungeeLanguageLib<>("EN", en_language.getFile());
            bungeeLanguageLib.getTranslateManager().addFile("ES", es_language.getFile());
            /* *
            * This is a simple example for a simple path that is 1 single string
            */
            bungeeLanguageLib.getTranslateManager().getTranslate("messages.test").ifPresent(message -> {
                getLogger().info(setVariable("%test%", "testing set variable").getMessage("EN"));
            });
            /* *
            * This is an example in any case we want to use a list
            */
            bungeeLanguageLib.getTranslateManager().getTranslate("Messages.apagando-list").ifPresent(message -> {
                message.setVariable("%test%", "testing set variable").getMessages("ES").forEach(resultMessage -> getLogger().info(resultMessage));
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
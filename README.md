# LanguageLib

LanguageLab is a simple language system library, what is sought is to facilitate the use of multi-languages ​​in the plugins of the programmer who wants to use this library.

# Example of how to use the library

I would like to emphasize that this library depends on a class that extends "YamlConfiguration" to create files if they do not have a class and I leave them one

class: https://pastebin.com/aQptr4DJ

```java
    public class Main extends JavaPlugin {

        private Configuration config;
        private LanguageLib languageLib;
        
        @Override
        public void onEnable() {
            this.config = new Configuration(this, "config.yml");
            languageLib = new LanguageLib();
            languageLib.getTranslateManager().addFile("ES", config);
            getTranslateManager().getTranslate().ifPresent(message -> {
                getLogger().info(message.getMessage("messages.test", "es"));
            });
        }       
    }   
```
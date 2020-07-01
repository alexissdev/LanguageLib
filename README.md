# LanguageLib

LanguageLab is a simple language system library, what is sought is to facilitate the use of multi-languages ​​in the plugins of the programmer who wants to use this library.

# Example of how to use the library

I would like to emphasize that this library depends on a class that extends "YamlConfiguration" to create files if they do not have a class and I leave them one

class: https://pastebin.com/aQptr4DJ

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
            * The "LanguageLab" parameters is the default language
            * The first parameter is the name of the language
            * The second parameter is the class that extends "Yaml Configuration"
            */
            languageLib = new LanguageLib("EN", en_language);
            languageLib.getTranslateManager().addFile("ES", es_language);
            getTranslateManager().getTranslate().ifPresent(message -> {
                getLogger().info(message.getMessage("messages.test", "es").setVariable("%test%", "testing set variable"));
            });
        }       
    }   
```
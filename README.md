# LanguageLib [![Codacy Badge](https://app.codacy.com/project/badge/Grade/7d0836959bc8471a913a5c0b698a9790)](https://www.codacy.com/manual/NotCacha/LanguageLib?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NotCacha/LanguageLib&amp;utm_campaign=Badge_Grade) [![](https://jitpack.io/v/cassha/LanguageLib.svg)](https://jitpack.io/#cassha/LanguageLib)

## Information
LanguageLib is a simple library to make it easier to handle multi languages

### Repository
````xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.cassha.LanguageLib</groupId>
	    <artifactId>languagelib</artifactId> <!-- languagelib-universal, languagelib-bukkit, languagelib-bungee -->
	    <version>2.0.6-SNAPSHOT</version>
	</dependency>
````

#### Usage

##### Create instance

Available implementations is BungeeLanguageLib and BukkitLanguageLib

````java
LanguageLib languageLib = BukkitLanguageLib.builder(your plugin, "language").build();
````

In any case, those who want to put their own implementations can use
````java
LanguageLib languageLib = BukkitLanguageLib.builder(plugin, "en")
                                    .setFileLoader(fileLoader)
                                    .setFilesManageable(filesManageable)
                                    .setTranslationManager(translationManager)
                                    .setI18n(i18n)
                                    .build();
````

##### Get simple message
````java
public void sendMessage(Player player) {
    TranslatableMessage translateMessage = languageLib.getTranslationManager().getTranslation("path");
    
    // Set variable from message
    translateMessage.setVariable("%player_name%", player.getName());

    // Set color from message
    translateMessage.colorize();
    
    //Get message
    String message = translateMessage.getMessage("language");

    player.sendMessage(message);
}
````

##### Get list messages
````java
public void sendMessages(Player player) {
    TranslatableMessage translateMessage = languageLib.getTranslationManager().getTranslation("path");
    
    // Set variable from messages
    translateMessage.setVariable("%player_name%", player.getName());

    // Set color from messages
    translateMessage.colorize();
    
    //Get messages in List format
    List<String> message = translateMessage.getMessages("language");

    message.forEach(player::sendMessage);
}
````

#### Placeholders

#### Create your placeholders

##### There are 1 ways available to create placeholders

````java
public class TestPlaceholderApplier implements PlaceholderApplier {

    public <T> String apply(T holder, String text) {
          if (!(holder instanceof Player)) {
              return text;
          }
          Player player = (Player) player;

          return text.replace("%player_name%", player.getName());
    }
}
````

#### Add your placeholders from message

##### In order to add them as a placeholder, they have to implement PlaceholderApplier

We can add 1 single placeholder

```java
translateMessage#addPlaceholder(holder, new YourPlaceholder())
```

or multiple

```java
translateMessage#addPlaceholders(holder, placeholders...);
```

#### Data to take into account
The default library generates the files in this way "language_% lang% .yml", we can change this in the FileLoader
%lang% will be replaced by the language that is being loaded
```java
fileLoader#setFormat("language_%lang%.yml")
```

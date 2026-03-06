# ModernTranslate

## Overview
ModernTranslate is a Nukkit-MOT plugin that provides a comprehensive translation system for Minecraft: Bedrock Edition servers. It supports multiple languages and allows plugins to register their own translations.

## Features
- 🌍 Multi-language support with 22+ languages
- 🔌 Plugin integration API
- 📝 Pattern-based translations
- ⚡ Player language auto-detection
- 🎯 Fallback strategy for missing translations

## Supported Languages
The plugin supports the following languages:

| Code | Language | Native Name |
|------|----------|-------------|
| eng | English | English |
| rus | Russian | Русский |
| ukr | Ukrainian | Українська |
| deu | German | Deutsch |
| fra | French | Français |
| spa | Spanish | Español |
| ita | Italian | Italiano |
| jpn | Japanese | 日本語 |
| kor | Korean | 한국어 |
| zho | Chinese | 中文 |
| pol | Polish | Polski |
| por | Portuguese | Português |
| tur | Turkish | Türkçe |
| vie | Vietnamese | Tiếng Việt |
| nld | Dutch | Nederlands |
| ces | Czech | Čeština |
| dan | Danish | Dansk |
| ell | Greek | Ελληνικά |
| fin | Finnish | Suomi |
| hun | Hungarian | Magyar |
| nor | Norwegian | Norsk |
| swe | Swedish | Svenska |

## API Usage

### Getting the API Instance

```java
import ru.labarjni.api.TranslationAPI;
```

### Register Plugin Translations
```java
File langFolder = new File(getDataFolder(), "translations");
TranslationAPI.registerPlugin("YourPlugin", langFolder);
```

### Translate Text
```java
// Translate for a player (auto-detects their language)
String message = TranslationAPI.translate("YourPlugin", player, "key.name");

// Translate with parameters
String message = TranslationAPI.translate("YourPlugin", player, "welcome.message", player.getName());

// Translate for specific language
String message = TranslationAPI.translate("YourPlugin", "rus", "key.name");
```

### Pattern Translations
```java
// Use global patterns
String pattern = TranslationAPI.pattern(player, "join.message", player.getName());

// Check if pattern exists
if (TranslationAPI.hasPattern("join.message")) {
    // Pattern exists
}
```
Patterns list available [here](https://github.com/labarjni/ModernTranslate/tree/master/src/main/resources/patterns), you can configure it personally

### Language Information
```java
// Get supported languages
Set<String> languages = TranslationAPI.getSupportedLanguages();

// Check if language is supported
boolean supported = TranslationAPI.isLanguageSupported("eng");

// Get language display name
String name = TranslationAPI.getLanguageName("eng"); // Returns "English"

// Get all languages with names
Map<String, String> languagesWithNames = TranslationAPI.getLanguagesWithNames();

// Resolve language from input (supports both "en_US" and "eng" formats)
LanguageCode lang = TranslationAPI.resolveLanguage("en_US");
```

package ru.labarjni.api;

import cn.nukkit.Player;

import ru.labarjni.ModernTranslate;
import ru.labarjni.utils.LanguageCode;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class TranslationAPI {

    private static ModernTranslate plugin() {
        var instance = ModernTranslate.getInstance();

        if (instance == null) throw new IllegalStateException("ModernTranslate not loaded!");

        return instance;
    }

    public static void registerPlugin(String name, File langFolder) {
        plugin().registerPluginTranslations(name, langFolder);
    }

    public static String translate(String plugin, Player player, String key, Object... params) {
        return plugin().translate(plugin, player, key, params);
    }

    public static String translate(String plugin, String lang, String key, Object... params) {
        return plugin().translate(plugin, lang, key, params);
    }

    public static String pattern(Player player, String key, Object... params) {
        return plugin().translatePattern(player, key, params);
    }

    public static String pattern(String lang, String key, Object... params) {
        return plugin().translatePattern(lang, key, params);
    }

    public static boolean hasPattern(String key) {
        return plugin().hasPattern(key);
    }

    public static Set<String> getSupportedLanguages() {
        return plugin().getSupportedLanguages();
    }

    public static boolean isLanguageSupported(String lang) {
        return plugin().isLanguageSupported(lang);
    }

    public static String getLanguageName(String langCode) {
        return LanguageCode.of(langCode).displayName();
    }

    public static Map<String, String> getLanguagesWithNames() {
        return plugin().getLanguagesWithNames();
    }

    public static LanguageCode resolveLanguage(String input) {
        return LanguageCode.of(input);
    }
}
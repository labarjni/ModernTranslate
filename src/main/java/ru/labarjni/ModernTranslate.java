package ru.labarjni;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import lombok.Getter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.core.context.BaseTranslationContext;
import org.densy.polyglot.core.language.SimpleLanguageStandard;
import org.densy.polyglot.core.provider.YamlFileProvider;

import ru.labarjni.utils.LanguageCode;
import ru.labarjni.utils.Languages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class ModernTranslate extends PluginBase {

    @Getter private static ModernTranslate instance;
    @Getter private Translation translation;

    @Getter private Translation patternsTranslation;
    @Getter private final Map<String, Translation> pluginTranslations = new ConcurrentHashMap<>();

    private final Set<String> supportedLanguages = ConcurrentHashMap.newKeySet();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        var config = new Config(getConfigFile(), Config.YAML);
        var languages = config.getStringList("supported-languages");
        supportedLanguages.addAll(languages.isEmpty() ? List.of("eng", "rus") : languages);

        initTranslations();
    }

    private void initTranslations() {
        var ctx = createContext();
        var langPath = getDataFolder().toPath().resolve("lang");
        var patternsPath = getDataFolder().toPath().resolve("patterns");

        createDirectories(langPath, patternsPath);
        translation = createTranslation(ctx, langPath);
        patternsTranslation = createTranslation(ctx, patternsPath);
    }

    private BaseTranslationContext createContext() {
        var ctx = new BaseTranslationContext();
        ctx.setLanguageStandard(new SimpleLanguageStandard());
        return ctx;
    }

    private Translation createTranslation(BaseTranslationContext ctx, Path path) {
        var t = ctx.createTranslation(new YamlFileProvider(path.toFile(), new SimpleLanguageStandard()));
        t.setDefaultLanguage(Languages.ENG.language());
        t.setFallbackStrategy(key -> key);
        return t;
    }

    private void createDirectories(Path... paths) {
        for (var path : paths)
            if (Files.notExists(path))
                try {
                    Files.createDirectories(path);
                }
                catch (Exception e) {
                    getLogger().error("Failed: " + path, e);
                }
    }

    public void registerPluginTranslations(String pluginName, File langFolder) {
        if (!langFolder.exists()) langFolder.mkdirs();
        pluginTranslations.put(pluginName, createContext()
                .createTranslation(new YamlFileProvider(langFolder, new SimpleLanguageStandard())));
        getLogger().info("Registered: " + pluginName);
    }

    public String translate(String pluginName, String langCode, String key, Object... params) {
        var lang = LanguageCode.of(langCode);
        var t = pluginTranslations.get(pluginName);
        return t == null ? key : translate(t, lang, key, params);
    }

    public String translate(String pluginName, Player player, String key, Object... params) {
        return translate(pluginName, getPlayerLang(player), key, params);
    }

    public String translatePattern(String langCode, String key, Object... params) {
        return patternsTranslation != null ? translate(patternsTranslation, LanguageCode.of(langCode), key, params) : key;
    }

    public String translatePattern(Player player, String key, Object... params) {
        return translatePattern(getPlayerLang(player), key, params);
    }

    public boolean hasPattern(String key) {
        return patternsTranslation != null && !patternsTranslation.translate(Languages.ENG.language(), key).equals(key);
    }

    private String translate(Translation t, LanguageCode lang, String key, Object... params) {
        return params.length == 0 ? t.translate(lang.language(), key) : t.translate(lang.language(), key, params);
    }

    private String getPlayerLang(Player player) {
        return Optional.ofNullable(player)
                .map(p -> p.getLoginChainData().getLanguageCode())
                .map(LanguageCode::of)
                .map(LanguageCode::code)
                .orElse("eng");
    }

    public Set<String> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguages);
    }

    public boolean isLanguageSupported(String langCode) {
        return supportedLanguages.contains(langCode);
    }

    public String getLanguageName(String langCode) {
        return LanguageCode.of(langCode).displayName();
    }

    public Map<String, String> getLanguagesWithNames() {
        var map = new LinkedHashMap<String, String>();

        for (var lang : supportedLanguages)
            map.put(lang, LanguageCode.of(lang).displayName());

        return map;
    }

    private File getConfigFile() {
        return new File(getDataFolder(), "config.yml");
    }
}
package ru.labarjni.utils;

import org.densy.polyglot.common.language.SimpleLanguage;

public final class Languages {

    private Languages() {}

    public static final LanguageCode ENG = new LanguageCode.Element("eng", SimpleLanguage.ENG, "English");
    public static final LanguageCode RUS = new LanguageCode.Element("rus", SimpleLanguage.RUS, "Русский");
    public static final LanguageCode UKR = new LanguageCode.Element("ukr", SimpleLanguage.UKR, "Українська");
    public static final LanguageCode DEU = new LanguageCode.Element("deu", SimpleLanguage.DEU, "Deutsch");
    public static final LanguageCode FRA = new LanguageCode.Element("fra", SimpleLanguage.FRA, "Français");
    public static final LanguageCode SPA = new LanguageCode.Element("spa", SimpleLanguage.SPA, "Español");
    public static final LanguageCode ITA = new LanguageCode.Element("ita", SimpleLanguage.ITA, "Italiano");
    public static final LanguageCode JPN = new LanguageCode.Element("jpn", SimpleLanguage.JPN, "日本語");
    public static final LanguageCode KOR = new LanguageCode.Element("kor", SimpleLanguage.KOR, "한국어");
    public static final LanguageCode ZHO = new LanguageCode.Element("zho", SimpleLanguage.ZHO, "中文");
    public static final LanguageCode POL = new LanguageCode.Element("pol", SimpleLanguage.POL, "Polski");
    public static final LanguageCode POR = new LanguageCode.Element("por", SimpleLanguage.POR, "Português");
    public static final LanguageCode TUR = new LanguageCode.Element("tur", SimpleLanguage.TUR, "Türkçe");
    public static final LanguageCode VIE = new LanguageCode.Element("vie", SimpleLanguage.VIE, "Tiếng Việt");
    public static final LanguageCode NLD = new LanguageCode.Element("nld", SimpleLanguage.NLD, "Nederlands");
    public static final LanguageCode CES = new LanguageCode.Element("ces", SimpleLanguage.CES, "Čeština");
    public static final LanguageCode DAN = new LanguageCode.Element("dan", SimpleLanguage.DAN, "Dansk");
    public static final LanguageCode ELL = new LanguageCode.Element("ell", SimpleLanguage.ELL, "Ελληνικά");
    public static final LanguageCode FIN = new LanguageCode.Element("fin", SimpleLanguage.FIN, "Suomi");
    public static final LanguageCode HUN = new LanguageCode.Element("hun", SimpleLanguage.HUN, "Magyar");
    public static final LanguageCode NOR = new LanguageCode.Element("nor", SimpleLanguage.NOR, "Norsk");
    public static final LanguageCode SWE = new LanguageCode.Element("swe", SimpleLanguage.SWE, "Svenska");

    public static final LanguageCode[] ALL = {
            ENG, // English (eng: en_US, en_GB)
            RUS, // Русский (rus: ru_RU)
            UKR, // Українська (ukr: uk_UA)
            DEU, // Deutsch (deu: de_DE)
            FRA, // Français (fra: fr_FR)
            SPA, // Español (spa: es_ES, es_MX)
            ITA, // Italiano (ita: it_IT)
            JPN, // 日本語 (jpn: ja_JP)
            KOR, // 한국어 (kor: ko_KR)
            ZHO, // 中文 (zho: zh_CN, zh_TW, zh_HK)
            POL, // Polski (pol: pl_PL)
            POR, // Português (por: pt_PT, pt_BR)
            TUR, // Türkçe (tur: tr_TR)
            VIE, // Tiếng Việt (vie: vi_VN)
            NLD, // Nederlands (nld: nl_NL)
            CES, // Čeština (ces: cs_CZ)
            DAN, // Dansk (dan: da_DK)
            ELL, // Ελληνικά (ell: el_GR)
            FIN, // Suomi (fin: fi_FI)
            HUN, // Magyar (hun: hu_HU)
            NOR, // Norsk (nor: nb_NO, nn_NO)
            SWE  // Svenska (swe: sv_SE)
    };

    public static LanguageCode getByCode(String code) {
        if (code == null) return null;
        for (LanguageCode lang : ALL) {
            if (lang.code().equalsIgnoreCase(code)) return lang;
        }
        return null;
    }

    public static String[] getAllCodes() {
        String[] codes = new String[ALL.length];
        for (int i = 0; i < ALL.length; i++) {
            codes[i] = ALL[i].code();
        }
        return codes;
    }
}
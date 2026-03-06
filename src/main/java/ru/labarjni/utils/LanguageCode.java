package ru.labarjni.utils;

import org.densy.polyglot.core.language.BaseLanguage;

public sealed interface LanguageCode permits LanguageCode.Element {

    String code();
    BaseLanguage language();
    String displayName();

    static LanguageCode of(String input) {
        if (input == null || input.isBlank()) return Languages.ENG;

        return switch (input) {
            case "ru_RU", "rus" -> Languages.RUS;
            case "uk_UA", "ukr" -> Languages.UKR;
            case "de_DE", "deu" -> Languages.DEU;
            case "fr_FR", "fra" -> Languages.FRA;
            case "es_ES", "es_MX", "spa" -> Languages.SPA;
            case "it_IT", "ita" -> Languages.ITA;
            case "ja_JP", "jpn" -> Languages.JPN;
            case "ko_KR", "kor" -> Languages.KOR;
            case "zh_CN", "zh_TW", "zh_HK", "zho" -> Languages.ZHO;
            case "pl_PL", "pol" -> Languages.POL;
            case "pt_PT", "pt_BR", "por" -> Languages.POR;
            case "tr_TR", "tur" -> Languages.TUR;
            case "vi_VN", "vie" -> Languages.VIE;
            case "nl_NL", "nld" -> Languages.NLD;
            case "cs_CZ", "ces" -> Languages.CES;
            case "da_DK", "dan" -> Languages.DAN;
            case "el_GR", "ell" -> Languages.ELL;
            case "fi_FI", "fin" -> Languages.FIN;
            case "hu_HU", "hun" -> Languages.HUN;
            case "nb_NO", "nn_NO", "nor" -> Languages.NOR;
            case "sv_SE", "swe" -> Languages.SWE;
            default -> Languages.ENG;
        };
    }

    record Element(String code, BaseLanguage language, String displayName) implements LanguageCode {}
}
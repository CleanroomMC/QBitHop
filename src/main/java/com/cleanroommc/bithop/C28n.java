package com.cleanroommc.bithop;

import java.util.List;
import java.util.Optional;

public class C28n {

    public static String format(String key, Object... format) {
        return BitHop.proxy.i18nFormat(key, format);
    }

    public static boolean keyExists(String key) {
        return BitHop.proxy.i18nContains(key);
    }

    public static Optional<String> formatOptional(String key, Object... format) {
        return keyExists(key) ? Optional.of(BitHop.proxy.i18nFormat(key, format)) : Optional.empty();
    }

    public static void formatList(List<String> out, String key, Object... format) {
        int i = 0;
        while (keyExists(key + "." + i)) {
            out.add(C28n.format(key + "." + i, format));
            i++;
        }
    }

    // Hook point for JustEnoughCharacters for pinyin support
    // Which is to say: this method is designed as an ASM hook point. If you're
    // developing an IME mod, feel free to hack this method to bits.
    public static boolean contains(String haystack, String needle) {
        return haystack != null && haystack.contains(needle);
    }

}

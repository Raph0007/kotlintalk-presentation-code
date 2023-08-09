package com.rtarita.presentation;

public final class StringUtil {
    private StringUtil() {
    }

    public static int charFrequency(String str, char c) {
        return (int) str.chars()
            .filter(it -> it == c)
            .count();
    }
}


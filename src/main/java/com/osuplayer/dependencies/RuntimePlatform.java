package com.osuplayer.dependencies;

import javafx.util.Pair;

import java.util.List;

public enum RuntimePlatform {
    WINDOWS,
    LINUX,
    OSX;

    public static final RuntimePlatform CURRENT = current();

    private static RuntimePlatform current() {
        var platform = System.getProperty("os.name").toLowerCase();

        for (var context : getPlatformsPatterns()) {
            if (platform.contains(context.getKey())) {
                return context.getValue();
            }
        }

        throw new UnsupportedOperationException("Unsupported operating system: " + platform);
    }

    private static List<Pair<String, RuntimePlatform>> getPlatformsPatterns() {
        return List.of(
            new Pair<>("win", WINDOWS),
            new Pair<>("mac", OSX),
            new Pair<>("nix", LINUX),
            new Pair<>("nux", LINUX),
            new Pair<>("aix", LINUX)
        );
    }
}

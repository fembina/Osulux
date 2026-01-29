package com.osuplayer.dependencies;

import javafx.util.Pair;

import java.util.List;

public enum RuntimeArchitecture {
    X86,
    X64,
    ARM64;

    public static final RuntimeArchitecture CURRENT = current();

    private static RuntimeArchitecture current() {
        var architecture = System.getProperty("os.arch").toLowerCase();

        for (var context : getArchitecturesPatterns()) {
            if (architecture.contains(context.getKey())) {
                return context.getValue();
            }
        }

        throw new UnsupportedOperationException("Unsupported architecture: " + architecture);
    }

    private static List<Pair<String, RuntimeArchitecture>> getArchitecturesPatterns() {
        return List.of(
            new Pair<>("amd64", X64),
            new Pair<>("x86_64", X64),
            new Pair<>("aarch64", ARM64),
            new Pair<>("arm64", ARM64),
            new Pair<>("x86", X86),
            new Pair<>("i386", X86),
            new Pair<>("i486", X86),
            new Pair<>("i586", X86),
            new Pair<>("i686", X86)
        );
    }
}

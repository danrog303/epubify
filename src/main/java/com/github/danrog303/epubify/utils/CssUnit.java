package com.github.danrog303.epubify.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssUnit {
    private final @NotNull String unit;

    private static final Set<String> allowedUnits = Set.of(
            "cm", "mm", "in", "px", "pt", "pc", "em", "ex", "ch", "rem", "vw", "vh", "min", "vmax", "%"
    );

    private static boolean cssUnitExists(String unit) {
        return CssUnit.allowedUnits.contains(unit);
    }

    private static String parseCssUnit(String cssUnit) {
        // Remove all whitespaces
        cssUnit = cssUnit.replaceAll("\\s+", "");

        // Replace commas with points
        cssUnit = cssUnit.replace(",", ".");

        // Make cssUnit lowercase
        cssUnit = cssUnit.toLowerCase(Locale.ROOT);

        // Get unit name (i.e. "px" by using regular expression)
        Pattern regexPattern = Pattern.compile("^(\\d+\\.?\\d*)([a-z]+)$");
        Matcher regexMatcher = regexPattern.matcher(cssUnit);

        if (regexMatcher.find() && regexMatcher.groupCount() == 2) {
            String cssUnitName = regexMatcher.group(2);

            if (CssUnit.cssUnitExists(cssUnitName)) {
                return cssUnit;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public CssUnit(@NotNull String cssUnit) {
        this.unit = CssUnit.parseCssUnit(cssUnit);
    }

    public @NotNull String getUnit() {
        return this.unit;
    }

    @Override
    public String toString() {
        return this.unit;
    }
}

package com.github.danrog303.epubify.models;

import java.util.EnumSet;

public enum ParagraphSeparation {
    MARGINS,
    INDENTS;

    public static final EnumSet<ParagraphSeparation> ALL_OPTS = EnumSet.allOf(ParagraphSeparation.class);
}

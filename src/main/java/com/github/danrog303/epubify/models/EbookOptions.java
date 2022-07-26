package com.github.danrog303.epubify.models;

import com.github.danrog303.epubify.utils.CssUnit;

import java.util.EnumSet;
import java.util.Objects;

public class EbookOptions {
    private CssUnit marginSize = new CssUnit("0.83em");
    private CssUnit paragraphIndentationSize = new CssUnit("0.5em");
    private EnumSet<ParagraphSeparation> paragraphSeparationMethod = EnumSet.of(ParagraphSeparation.MARGINS);

    public EbookOptions() {
    }

    public void setMarginSize(CssUnit marginSize) {
        Objects.requireNonNull(marginSize);
        this.marginSize = marginSize;
    }

    public CssUnit getMarginSize() {
        return this.marginSize;
    }

    public void setParagraphIndentationSize(CssUnit paragraphIndentationSize) {
        Objects.requireNonNull(paragraphIndentationSize);
        this.paragraphIndentationSize = paragraphIndentationSize;
    }

    public CssUnit getParagraphIndentationSize() {
        return this.paragraphIndentationSize;
    }

    public void setParagraphSeparationMethod(EnumSet<ParagraphSeparation> paragraphSeparationMethod) {
        Objects.requireNonNull(paragraphSeparationMethod);
        if (paragraphSeparationMethod.size() < 1) {
            throw new IllegalArgumentException("Paragraph separation method enum set must not be empty.");
        }
        this.paragraphSeparationMethod = paragraphSeparationMethod;
    }

    public EnumSet<ParagraphSeparation> getParagraphSeparationMethod() {
        return this.paragraphSeparationMethod;
    }
}

package com.github.danrog303.epubify.models;

import org.jetbrains.annotations.NotNull;

public class EbookChapter {
    private @NotNull String name = "";
    private @NotNull String htmlContent = "";

    public EbookChapter(@NotNull String name, @NotNull String htmlContent) {
        this.setName(name);
        this.setHtmlContent(htmlContent);
    }

    public EbookChapter() {
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public void setHtmlContent(@NotNull String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public @NotNull String getHtmlContent() {
        return this.htmlContent;
    }
}

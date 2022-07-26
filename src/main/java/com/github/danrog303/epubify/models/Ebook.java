package com.github.danrog303.epubify.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Ebook {
    @NotNull private String name = "";
    @NotNull private String author = "";
    @NotNull private final List<EbookChapter> chaptersList = new ArrayList<EbookChapter>();

    @Nullable private String coverImagePath = "";
    @Nullable private String description = null;

    public Ebook(@NotNull String name, @NotNull String author, @NotNull String coverImagePath) {
        this.setName(name);
        this.setAuthor(author);
        this.setCoverImage(coverImagePath);
    }

    public Ebook() {
    }

    public @NotNull List<EbookChapter> getChaptersList() {
        return this.chaptersList;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public void setAuthor(@NotNull String author) {
        this.author = author;
    }

    public @NotNull String getAuthor() {
        return this.author;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public @Nullable String getDescription() {
        return this.description;
    }

    public void setCoverImage(@Nullable String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public @Nullable String getCoverImage() {
        return this.coverImagePath;
    }

    public void addChapter(@NotNull EbookChapter chapter) {
        this.chaptersList.add(chapter);
    }

    public void addChapter(@NotNull String chapterName, @NotNull String chapterHtmlContent) {
        EbookChapter chapter = new EbookChapter(chapterName, chapterHtmlContent);
        addChapter(chapter);
    }
}

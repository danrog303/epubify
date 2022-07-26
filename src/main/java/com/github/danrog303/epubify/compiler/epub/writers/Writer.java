package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;

import java.io.File;
import java.io.IOException;

/**
 * Base class for a writer. Writer is basically a class, which takes epub directory
 * and performs some modification on it (for example changing metadata, cover, downloading images etc).
 */
public abstract class Writer {
    private final File epubDirectory;
    private final Ebook ebook;
    private final EbookOptions ebookOptions;

    protected File getEpubDirectory() {
        return this.epubDirectory;
    }

    protected Ebook getEbookInstance() {
        return this.ebook;
    }

    protected EbookOptions getEbookOptions() {
        return this.ebookOptions;
    }

    public Writer(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        this.ebook = ebook;
        this.epubDirectory = epubDirectory;
        this.ebookOptions = ebookOptions;
    }

    public abstract void run() throws IOException;
}

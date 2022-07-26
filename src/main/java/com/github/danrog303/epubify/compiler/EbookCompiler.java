package com.github.danrog303.epubify.compiler;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;

import java.io.IOException;

public abstract class EbookCompiler {
    private EbookOptions ebookOptions;

    public EbookCompiler(EbookOptions ebookOptions) {
        this.setEbookOptions(ebookOptions);
    }

    public abstract void compile(Ebook ebook, String outPath) throws IOException;

    public EbookOptions getEbookOptions() {
        return this.ebookOptions;
    }

    public void setEbookOptions(EbookOptions ebookOptions) {
        this.ebookOptions = ebookOptions;
    }
}

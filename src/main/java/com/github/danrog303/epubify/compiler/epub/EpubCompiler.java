package com.github.danrog303.epubify.compiler.epub;

import com.github.danrog303.epubify.compiler.EbookCompiler;
import com.github.danrog303.epubify.compiler.epub.writers.*;
import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.utils.TemporaryDirectory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EpubCompiler extends EbookCompiler {
    public EpubCompiler(EbookOptions ebookOptions) {
        super(ebookOptions);
    }

    private void runAllWriters(Ebook ebook, File epubDirectory) throws IOException {
        var writers = List.of(
                new MetadataWriter(ebook, epubDirectory, this.getEbookOptions()),
                new ChapterWriter(ebook, epubDirectory, this.getEbookOptions()),
                new ImageWriter(ebook, epubDirectory, this.getEbookOptions()),
                new StyleWriter(ebook, epubDirectory, this.getEbookOptions()),
                new CoverWriter(ebook, epubDirectory, this.getEbookOptions()),
                new CleanupWriter(ebook, epubDirectory, this.getEbookOptions())
        );

        for (Writer writer : writers) {
            writer.run();
        }
    }

    @Override
    public void compile(Ebook ebook, String outPath) throws IOException {
        try(var tmp = new TemporaryDirectory()) {
            var packer = new EpubPacker();
            var skeleton = new EpubSkeleton(tmp.getAbsolutePath());

            skeleton.create();
            this.runAllWriters(ebook, tmp.getFile());
            packer.createEpub(tmp.getAbsolutePath(), outPath);
        }
    }
}

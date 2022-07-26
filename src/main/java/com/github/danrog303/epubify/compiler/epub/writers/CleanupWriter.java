package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

/**
 * Performs cleanup and removes all unnecessary data from the epub directory.
 */
public class CleanupWriter extends Writer {
    public CleanupWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    @Override
    public void run() {
        // Remove chapter template file
        var chapterTemplatePath = Path.of(this.getEpubDirectory().getAbsolutePath(), "page-template.html");
        FileUtils.deleteQuietly(chapterTemplatePath.toFile());

        // Remove corrupted image template file
        var errorImagePath = Path.of(this.getEpubDirectory().getAbsolutePath(), "image-error.jpeg");
        FileUtils.deleteQuietly(errorImagePath.toFile());
    }
}

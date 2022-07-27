package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.TextStringBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Writes some basic epub metadata (like author and e-book title).
 */
public class MetadataWriter extends Writer {
    public MetadataWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    public void run() throws IOException {
        File contentOpfFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "content.opf").toFile();
        File tocNcxFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "toc.ncx").toFile();

        var contentOpfContent = new TextStringBuilder(FileUtils.readFileToString(contentOpfFile, "UTF-8"));
        var tocNcxContent = new TextStringBuilder(FileUtils.readFileToString(tocNcxFile, "UTF-8"));

        tocNcxContent.replaceAll("!BOOK_TITLE!", this.getEbookInstance().getName());
        contentOpfContent.replaceAll("!BOOK_TITLE!", this.getEbookInstance().getName());
        contentOpfContent.replaceAll("!BOOK_AUTHOR!", this.getEbookInstance().getAuthor());
        contentOpfContent.replaceAll("!BOOK_DESCRIPTION!", this.getEbookInstance().getDescription());

        FileUtils.writeStringToFile(contentOpfFile, contentOpfContent.build(), "UTF-8", false);
        FileUtils.writeStringToFile(tocNcxFile, tocNcxContent.build(), "UTF-8", false);
    }
}

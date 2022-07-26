package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.models.ParagraphSeparation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Changes CSS styling of an e-book. Generated styling is based on EbookOptions object.
 */
public class StyleWriter extends Writer {

    public StyleWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    public void run() throws IOException {
        var mainCssFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "main.css").toFile();
        var mainCssContent = FileUtils.readFileToString(mainCssFile, "UTF-8");
        var paragraphSeparationCss = new StringBuilder(mainCssContent);

        if (this.getEbookOptions().getParagraphSeparationMethod().contains(ParagraphSeparation.INDENTS)) {
            var indentSize = this.getEbookOptions().getParagraphIndentationSize().getUnit();
            paragraphSeparationCss.append("text-indent: %s; margin: 0;".formatted(indentSize));
        }

        if (this.getEbookOptions().getParagraphSeparationMethod().contains(ParagraphSeparation.MARGINS)) {
            var marginSize = this.getEbookOptions().getMarginSize().getUnit();
            paragraphSeparationCss.append("margin-top: %s; margin-bottom: %s;".formatted(marginSize, marginSize));
        }

        FileUtils.writeStringToFile(mainCssFile, paragraphSeparationCss.toString(), "UTF-8", false);
    }
}

package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.utils.JpegImageSave;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.TextStringBuilder;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Downloads cover image and registers it in the epub metadata.
 */
public class CoverWriter extends Writer {

    public CoverWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    public void run() throws IOException {
        var workingDir = this.getEpubDirectory().getAbsolutePath();
        var coverFile = Path.of(workingDir, "cover.jpeg").toFile();
        var titlePageFile = Path.of(workingDir, "titlepage.xhtml").toFile();
        var titlePageFileContent = new TextStringBuilder(FileUtils.readFileToString(titlePageFile, "UTF-8"));

        // If cover present, copy or download it
        // Else just stick with default cover.jpeg from epub template
        if (this.getEbookInstance().getCoverImage() != null && !this.getEbookInstance().getCoverImage().isEmpty()) {
            JpegImageSave.save(this.getEbookInstance().getCoverImage(), coverFile);
        }

        var coverImage = ImageIO.read(coverFile);
        titlePageFileContent.replaceAll("!COVER_WIDTH!", Integer.toString(coverImage.getWidth()));
        titlePageFileContent.replaceAll("!COVER_HEIGHT!", Integer.toString(coverImage.getHeight()));
        FileUtils.writeStringToFile(titlePageFile, titlePageFileContent.build(), "UTF-8", false);
    }
}
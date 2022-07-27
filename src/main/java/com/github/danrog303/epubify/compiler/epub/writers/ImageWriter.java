package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.utils.JpegImageSave;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.TextStringBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Downloads images that are present in ebook and registers them in the epub metadata.
 */
public class ImageWriter extends Writer {
    public ImageWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    public void run() throws IOException {
        int imageCounter = 0;
        var workingDir = this.getEpubDirectory().getAbsolutePath();
        var chaptersDir = Path.of(workingDir, "text").toFile();

        for (File chapterFile : Objects.requireNonNull(chaptersDir.listFiles())) {
            var chapterHtml = new TextStringBuilder(FileUtils.readFileToString(chapterFile, "UTF-8"));

            // Find <img src="?"> attribute values
            var regexPattern = Pattern.compile("<img src=[\"']([^\"']*)[\"']");
            var regexMatcher = regexPattern.matcher(chapterHtml.build());

            while (regexMatcher.find()) {
                var requestedImageSrc = regexMatcher.group(1);
                var imageFile = Path.of(workingDir, "images", "image-%d.jpeg".formatted(imageCounter)).toFile();

                try {
                    JpegImageSave.save(requestedImageSrc, imageFile);
                } catch(IOException e) {
                    var templateErrorImageFile = Path.of(workingDir, "image-error.jpeg").toFile();
                    FileUtils.copyFile(templateErrorImageFile, imageFile);
                } finally {
                    chapterHtml.replaceAll(requestedImageSrc, "../images/image-%d.jpeg".formatted(imageCounter));
                    imageCounter++;
                }
            }

            FileUtils.writeStringToFile(chapterFile, chapterHtml.build(), "UTF-8", false);
        }

        File contentOpfFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "content.opf").toFile();
        var contentOpfContent = new TextStringBuilder(FileUtils.readFileToString(contentOpfFile, "UTF-8"));
        var imageDeclarations = new StringBuilder();
        final var imageDeclarationTemplate = "<item href='images/image-%d.jpeg' id='image-%d' media-type='image/jpeg'/>\n\t\t";

        for (int i = 0; i < imageCounter; i++) {
            imageDeclarations.append(imageDeclarationTemplate.formatted(i, i));
        }

        contentOpfContent.replaceAll("!IMAGE_DECLARATIONS!", imageDeclarations.toString());
        FileUtils.writeStringToFile(contentOpfFile, contentOpfContent.build(), "UTF-8", false);
    }
}

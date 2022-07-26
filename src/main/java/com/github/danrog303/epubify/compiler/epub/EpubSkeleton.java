package com.github.danrog303.epubify.compiler.epub;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class EpubSkeleton {
    private final String directoryPath;

    EpubSkeleton(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void create() throws IOException {
        var imagesDir = Path.of(directoryPath, "images").toFile();
        var metaInfDir = Path.of(directoryPath, "META-INF").toFile();
        var textDir = Path.of(directoryPath, "text").toFile();

        for (File file : List.of(imagesDir, metaInfDir, textDir)) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdir();
        }

        var filesToCopy = List.of(
                "content.opf", "cover.jpeg","image-error.jpeg", "main.css", "mimetype",
                "page-template.html", "titlepage.xhtml", "toc.ncx", "META-INF/container.xml"
        );

        for (String filename : filesToCopy) {
            try(var resStream = this.getClass().getResourceAsStream("/epub_template/" + filename)) {
                assert resStream != null;
                var templateTmpFile = Path.of(directoryPath, filename).toFile();
                FileUtils.copyInputStreamToFile(resStream, templateTmpFile);
            }
        }
    }
}

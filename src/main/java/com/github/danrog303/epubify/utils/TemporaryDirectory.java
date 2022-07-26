package com.github.danrog303.epubify.utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Creates temporary directory. Directory is deleted after calling {@link #close()} method.
 * Class is designed to be used in try-resource blocks.
 */
public class TemporaryDirectory implements AutoCloseable {
    private final String tempDirPath;
    private final File tempDir;

    public TemporaryDirectory() throws IOException {
        this.tempDirPath = Files.createTempDirectory("epubify").toFile().getAbsolutePath();
        this.tempDir = new File(this.tempDirPath);
    }

    public String getAbsolutePath() {
        return this.tempDirPath;
    }

    public File getFile() {
        return this.tempDir;
    }

    @Override
    public void close() throws IOException {
        FileUtils.deleteDirectory(this.tempDir);
    }
}

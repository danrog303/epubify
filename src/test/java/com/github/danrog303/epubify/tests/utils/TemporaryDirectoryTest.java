package com.github.danrog303.epubify.tests.utils;

import com.github.danrog303.epubify.utils.TemporaryDirectory;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class TemporaryDirectoryTest {
    @Test
    public void TemporaryDirectory_tryToCreateTemporaryDir_temporaryDirCreatedSuccessfully() {
        Assertions.assertDoesNotThrow(() -> {
            try (TemporaryDirectory dir = new TemporaryDirectory()) {
                Assertions.assertTrue(dir.getFile().isDirectory());
                var exampleFile = Path.of(dir.getAbsolutePath(), "file.txt").toFile();
                var fileCreated = exampleFile.createNewFile();

                Assertions.assertTrue(fileCreated);

                FileUtils.writeStringToFile(exampleFile, "example data", "UTF-8", false);
                Assertions.assertEquals("example data", FileUtils.readFileToString(exampleFile, "UTF-8"));
            }
        });
    }
}

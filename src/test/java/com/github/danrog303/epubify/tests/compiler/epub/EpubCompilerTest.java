package com.github.danrog303.epubify.tests.compiler.epub;

import com.github.danrog303.epubify.compiler.epub.EpubCompiler;
import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookChapter;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.models.ParagraphSeparation;
import com.github.danrog303.epubify.utils.CssUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.EnumSet;

public class EpubCompilerTest {
    @Test
    public void compile_basicEbookCompilation_notThrowAnyExceptions() {
        Assertions.assertDoesNotThrow(() -> {
            File tempFile = File.createTempFile("epubify-", "-epub-compiler-test");
            var ebook = new Ebook();
            ebook.setName("Some book");
            ebook.setAuthor("Some Author");
            ebook.setCoverImage(null);

            for(int i = 0; i < 15; i++) {
                var chapter = new EbookChapter("%d. Some chapter".formatted(i+1), "<p>Chapter content</p>");
                ebook.addChapter(chapter);
            }

            var opts = new EbookOptions();
            opts.setParagraphSeparationMethod(EnumSet.of(ParagraphSeparation.INDENTS));
            opts.setParagraphIndentationSize(new CssUnit("0.5em"));

            var compiler = new EpubCompiler(opts);
            compiler.compile(ebook, tempFile.getAbsolutePath());

            var fileDeleted = tempFile.delete();
            Assertions.assertTrue(fileDeleted);
        });
    }
}

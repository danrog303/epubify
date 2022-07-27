package com.github.danrog303.epubify.compiler.epub.writers;

import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookOptions;
import com.github.danrog303.epubify.utils.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.TextStringBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


/**
 * Writes all chapters to epub directory and adds them to the table of contents.
 */
public class ChapterWriter extends Writer {

    public ChapterWriter(Ebook ebook, File epubDirectory, EbookOptions ebookOptions) {
        super(ebook, epubDirectory, ebookOptions);
    }

    public void run() throws IOException {
        var workingDir = this.getEpubDirectory().getAbsolutePath();

        var textDeclarations = new StringBuilder();
        var textIdDeclarations = new StringBuilder();
        var navPointDeclarations = new StringBuilder();

        var pageTemplateFile = Path.of(workingDir, "page-template.html").toFile();
        var pageTemplateContent = FileUtils.readFileToString(pageTemplateFile, "UTF-8");

        final var textDeclarationTemplate = "<item href='%s' id='part-%d' media-type='application/xhtml+xml'/>\n\t\t";
        final var textIdDeclarationTemplate = "<itemref idref='part-%d'/>\n\t\t";
        final var navPointDeclarationTemplate = "\"<navPoint playOrder='%d' id='part-%d'><navLabel><text>%s</text></navLabel><content src='%s'/></navPoint>\\n\\t\\t\"";

        for (var chapter : ListUtils.enumerate(this.getEbookInstance().getChaptersList())) {
            var chapterEbookFilename = "text/part-%d.html".formatted(chapter.index);
            var chapterFilename = Path.of(workingDir, chapterEbookFilename);

            var chapterHtml = new TextStringBuilder(pageTemplateContent);
            chapterHtml.replaceAll("!CHAPTER_NAME!", chapter.item.getName());
            chapterHtml.replaceAll("!CHAPTER_HTML!", chapter.item.getHtmlContent());
            FileUtils.writeStringToFile(chapterFilename.toFile(), chapterHtml.build(), "UTF-8", false);

            textDeclarations.append(textDeclarationTemplate.formatted(chapterEbookFilename, chapter.index));
            textIdDeclarations.append(textIdDeclarationTemplate.formatted(chapter.index));
            navPointDeclarations.append(navPointDeclarationTemplate.formatted(chapter.index + 1, chapter.index, chapter.item.getName(), chapterEbookFilename));
        }

        File tocNcxFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "toc.ncx").toFile();
        File contentOpfFile = Path.of(this.getEpubDirectory().getAbsolutePath(), "content.opf").toFile();
        var contentOpfContent = new TextStringBuilder(FileUtils.readFileToString(contentOpfFile, "UTF-8"));
        var tocNcxContent = new TextStringBuilder(FileUtils.readFileToString(tocNcxFile, "UTF-8"));

        contentOpfContent.replaceAll("!TEXT_DECLARATIONS!", textDeclarations.toString());
        contentOpfContent.replaceAll("!TEXT_IDS!", textIdDeclarations.toString());
        tocNcxContent.replaceAll("!NAV_POINTS!", navPointDeclarations.toString());

        FileUtils.writeStringToFile(contentOpfFile, contentOpfContent.build(), "UTF-8", false);
        FileUtils.writeStringToFile(tocNcxFile, tocNcxContent.build(), "UTF-8", false);
    }
}

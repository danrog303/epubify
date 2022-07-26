package com.github.danrog303.epubify.compiler.epub;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

class EpubPacker {
    public void createEpub(String epubContentDirectoryPath, String outputFilePath) {
        File epubContentDirectory = new File(epubContentDirectoryPath);
        File outputFile = new File(outputFilePath);
        this.createEpub(epubContentDirectory, outputFile);
    }

    public void createEpub(File epubContentDirectory, File outputFile) {
        ZipUtil.pack(epubContentDirectory, outputFile);
    }
}

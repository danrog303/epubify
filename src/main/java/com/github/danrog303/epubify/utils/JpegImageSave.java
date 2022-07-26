package com.github.danrog303.epubify.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JpegImageSave {
    public static void save(String filePathOrHttpUrl, File out) throws IOException {
        BufferedImage image = null;

        // Load image from http url or from file
        if (filePathOrHttpUrl.startsWith("https") || filePathOrHttpUrl.startsWith("http")) {
            URL url = new URL(filePathOrHttpUrl);
            image = ImageIO.read(url);
        } else {
            File file = new File(filePathOrHttpUrl);
            image = ImageIO.read(file);
        }

        // Remove transparency
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
        newImage.createGraphics().drawImage(image, 0, 0, Color.white, null);

        // Save image as jpeg
        ImageIO.write(newImage, "jpeg", out);
    }

    private JpegImageSave() {}
}

package com.fatemorgan.imgspring.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageProcessor {
    public static BufferedImage streamToImage(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
        BufferedImage bufferedImage = ImageIO.read(bufferedStream);
        bufferedStream.close();
        return bufferedImage;
    }
}

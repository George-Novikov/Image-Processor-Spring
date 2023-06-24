package com.fatemorgan.imgspring.tools;

import com.fatemorgan.imgspring.entities.IntegerRGB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageProcessor {
    public static BufferedImage toBlackAndWhite(BufferedImage bufferedImage){
        for (int y = 0; y < bufferedImage.getHeight(); y++){
            for (int x = 0; x < bufferedImage.getWidth(); x++){
                IntegerRGB intRGB = new IntegerRGB(bufferedImage.getRGB(x, y));
                if (intRGB.isBrightPixel()){
                    bufferedImage.setRGB(x, y, IntegerRGB.WHITE);
                } else {
                    bufferedImage.setRGB(x, y, IntegerRGB.BLACK);
                }
            }
        }
        return bufferedImage;
    }

    public static BufferedImage toGrayscale(BufferedImage bufferedImage, int threshold){
        ImageFilter grayFilter = new GrayFilter(true, threshold);
        ImageProducer imgProducer = new FilteredImageSource(bufferedImage.getSource(), grayFilter);
        Image image = Toolkit.getDefaultToolkit().createImage(imgProducer);
        BufferedImage outputImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(image, 0, 0, null);
        return outputImage;
    }

    public static BufferedImage streamToImage(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
        BufferedImage bufferedImage = ImageIO.read(bufferedStream);
        bufferedStream.close();
        return bufferedImage;
    }

    public static byte[] imageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] output = baos.toByteArray();
        baos.close();
        return output;
    }
}

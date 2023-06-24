package com.fatemorgan.imgspring.services;

import com.fatemorgan.imgspring.tools.ImageProcessor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component("imageService")
public class ImageService {

    public byte[] getBlackAndWhite(InputStream inStream) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inStream);
        bufferedImage = ImageProcessor.toBlackAndWhite(bufferedImage);
        return ImageProcessor.imageToByteArray(bufferedImage);
    }

    public byte[] getGrayscale(InputStream inStream, int threshold) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inStream);
        bufferedImage = ImageProcessor.toGrayscale(bufferedImage, threshold);
        return ImageProcessor.imageToByteArray(bufferedImage);
    }
}

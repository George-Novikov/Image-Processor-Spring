package com.fatemorgan.imgspring.services;

import com.fatemorgan.imgspring.tools.ImageProcessor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component("imageService")
public class ImageService {

    public byte[] getBlackAndWhite(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inputStream);
        bufferedImage = ImageProcessor.toBlackAndWhite(bufferedImage);
        return ImageProcessor.imageToByteArray(bufferedImage);
    }
}

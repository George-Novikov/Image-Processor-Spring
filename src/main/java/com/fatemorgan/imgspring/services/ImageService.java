package com.fatemorgan.imgspring.services;

import com.fatemorgan.imgspring.tools.ImageProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageService {
    public byte[] getGrayscale(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inputStream);

    }
}

package com.fatemorgan.imgspring.services;

import com.fatemorgan.imgspring.entities.Coordinates;
import com.fatemorgan.imgspring.entities.Segment;
import com.fatemorgan.imgspring.tools.ImageProcessor;
import org.springframework.stereotype.Component;

import java.awt.*;
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

    public byte[] getXYSegment(InputStream inStream, int x, int y, int size) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inStream);
        Segment segment = ImageProcessor.imageToSegment(bufferedImage, x, y, size);
        BufferedImage outputImage = ImageProcessor.segmentToImage(segment);
        return ImageProcessor.imageToByteArray(outputImage);
    }

    public Coordinates getSegmentationLimits(InputStream inStream, int segmentSize) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inStream);
        return ImageProcessor.getSegmentationLimits(bufferedImage, segmentSize);
    }

    public byte[] resize(InputStream inStream, float multiplier) throws IOException {
        BufferedImage bufferedImage = ImageProcessor.streamToImage(inStream);
        bufferedImage = ImageProcessor.resize(bufferedImage, multiplier);
        return ImageProcessor.imageToByteArray(bufferedImage);
    }

}

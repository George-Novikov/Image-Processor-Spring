package com.fatemorgan.imgspring.tools;

import com.fatemorgan.imgspring.entities.Coordinates;
import com.fatemorgan.imgspring.entities.IntegerRGB;
import com.fatemorgan.imgspring.entities.Segment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalDouble;

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

    public static Segment imageToSegment(BufferedImage bufferedImage, int x, int y, int size){
        Segment segment = new Segment(x, y, size);
        for (int iy = 0; iy < segment.getSize(); iy++){
            for (int ix = 0; ix < segment.getSize(); ix++){
                int intColor = bufferedImage.getRGB(segment.dX(ix), segment.dY(iy));
                segment.setPixel(ix, iy, intColor);
            }
        }
        return segment;
    }

    public static BufferedImage segmentToImage(Segment segment){
        BufferedImage outputImage = new BufferedImage(segment.getSize(), segment.getSize(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < segment.getSize(); y++){
            for (int x = 0; x < segment.getSize(); x++){
                outputImage.setRGB(x, y, segment.getPixel(x, y));
            }
        }
        return outputImage;
    }

    public static Coordinates getSegmentationLimits(BufferedImage bi, int segmentSize){
        return Coordinates.of(bi.getWidth()/segmentSize - 1, bi.getHeight()/segmentSize - 1);
    }

    public static BufferedImage resize(BufferedImage originalImage, float multiplier){
        int targetWidth = Math.round(originalImage.getWidth() * multiplier);
        int targetHeight = Math.round(originalImage.getHeight() * multiplier);

        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return outputImage;
    }

    public static BufferedImage sharpen(BufferedImage bufferedImage){
        float[] kernelMatrix = new float[] {
                0, -1, 0,
                -1, 5, -1,
                0, -1, 0 };
        Kernel kernel = new Kernel(3, 3, kernelMatrix);
        BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(bufferedImage, null);
    }

    public static float measureBrightness(BufferedImage bufferedImage){
        java.util.List<Float> brightnessList = new ArrayList<>();
        for (int y = 0; y < bufferedImage.getHeight(); y++){
            for (int x = 0; x < bufferedImage.getWidth(); x++){
                IntegerRGB rgb = new IntegerRGB(bufferedImage.getRGB(x, y));
                brightnessList.add(Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null)[2]);
            }
        }
        OptionalDouble averageBrightness = brightnessList.stream().mapToDouble(b -> b).average();
        return (float) averageBrightness.getAsDouble();
    }

    public static BufferedImage changeBrightness(BufferedImage bufferedImage,
                                                 float brightenFactor,
                                                 float offset){
        RescaleOp rescaleOP = new RescaleOp(brightenFactor, offset, null);
        return rescaleOP.filter(bufferedImage, bufferedImage);
    }

    public static BufferedImage changeContrast(BufferedImage bufferedImage, float contrastFactor){
        if (contrastFactor != 1){
            for (int y = 0; y < bufferedImage.getHeight(); y++){
                for (int x = 0; x < bufferedImage.getWidth(); x++){
                    IntegerRGB intRGB = new IntegerRGB(bufferedImage.getRGB(x, y));
                    intRGB.setBlue(changeColorContrast(intRGB.getBlue(), contrastFactor, intRGB.isBlueBright()));
                    intRGB.setGreen(changeColorContrast(intRGB.getGreen(), contrastFactor, intRGB.isGreenBright()));
                    intRGB.setRed(changeColorContrast(intRGB.getRed(), contrastFactor, intRGB.isRedBright()));
                    intRGB.normalize();
                    bufferedImage.setRGB(x, y, intRGB.getIntFromRGB());
                }
            }
        }
        return bufferedImage;
    }

    private static int changeColorContrast(int color, float contr, boolean isIncreased){
        return isIncreased ? Math.round(color + color * (contr - (contr * 128 / color)) / 10) : Math.round(color - color * (contr - (contr * color / 127)) / 10);
    }
}

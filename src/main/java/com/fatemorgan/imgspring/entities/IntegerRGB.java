package com.fatemorgan.imgspring.entities;

import java.awt.*;

public class IntegerRGB {
    private static final int BLUEMODIFIER = 0xff;
    private static final int GREENMODIFIER = 0xff00;
    private static final int REDMODIFIER = 0xff0000;
    public static final int WHITE = -1;
    public static final int BLACK = -16777216;
    private int blue;
    private int green;
    private int red;

    public IntegerRGB(){}
    public IntegerRGB(int color){
        this.blue = (color & BLUEMODIFIER);
        this.green = (color & GREENMODIFIER) >> 8;
        this.red = (color & REDMODIFIER) >> 16;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) { this.green = green; }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public boolean isBlueBright(){ return blue >= 128; }

    public boolean isGreenBright(){ return green >= 128; }

    public boolean isRedBright(){ return red >= 128; }

    public boolean isBrightPixel(){ return ((blue + green + red) / 3) >= 128; }

    public void normalize(){
        blue = blue > 255 ? 255 : blue;
        blue = blue < 0 ? 0 : blue;

        green = green > 255 ? 255 : green;
        green = green < 0 ? 0 : green;

        red = red > 255 ? 255 : red;
        red = red < 0 ? 0 : red;
    }

    public int getIntFromRGB(){ return new Color(red, green, blue).getRGB(); }

    public static String toHexColor(int intColor){
        return String.format("#%06X", (0xFFFFFF & intColor));
    }
}

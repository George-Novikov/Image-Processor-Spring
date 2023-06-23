package com.fatemorgan.imgspring.entities;

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

    public boolean isBrightPixel(){ return ((blue + green + red) / 3) >= 128; }

    public static String toHexColor(int intColor){
        return String.format("#%06X", (0xFFFFFF & intColor));
    }
}

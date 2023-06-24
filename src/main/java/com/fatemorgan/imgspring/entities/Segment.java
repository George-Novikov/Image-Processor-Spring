package com.fatemorgan.imgspring.entities;

public class Segment {
    private int x;
    private int y;
    private int size;
    private int[][] matrix;

    public Segment(){}
    public Segment(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        matrix = new int[size][size];
    }

    public int getPixel(int x, int y){
        return matrix[x][y];
    }

    public void setPixel(int x, int y, int value){
        matrix[x][y] = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int dX(int originalX){
        return originalX + size * x;
    }

    public int dY(int originalY){
        return originalY + size * y;
    }
}

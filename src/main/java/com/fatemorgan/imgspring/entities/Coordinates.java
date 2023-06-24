package com.fatemorgan.imgspring.entities;

public class Coordinates<X,Y> {
    private X x;
    private Y y;

    private Coordinates(X x, Y y){
        this.x = x;
        this.y = y;
    }

    public static <X, Y> Coordinates<X, Y> of(X x, Y y){
        return new Coordinates<>(x, y);
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }
}

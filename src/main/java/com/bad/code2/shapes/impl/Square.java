package com.bad.code2.shapes.impl;

import com.bad.code2.shapes.Shape2D;

public class Square implements Shape2D {
    private Double x;
    private Double y;
    private Double edgeSize;

    public Square(Double x, Double y, Double edgeSize) {
        this.x = x;
        this.y = y;
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getEdgeSize() {
        return edgeSize;
    }

    @Override
    public void setX(Double x) {
        this.x = x;
    }

    @Override
    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public void setEdgeSize(Double edgeSize) {
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getPerimeter() {
        return edgeSize * 4;
    }
}

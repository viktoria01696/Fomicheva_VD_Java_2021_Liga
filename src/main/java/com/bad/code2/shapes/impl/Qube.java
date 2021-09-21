package com.bad.code2.shapes.impl;

import com.bad.code2.shapes.Shape3D;

public class Qube implements Shape3D {
    private Double x;
    private Double y;
    private Double z;
    private Double edgeSize;

    public Qube(Double x, Double y, Double z, Double edgeSize) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    public Double getZ() {
        return z;
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
    public void setZ(Double z) {
        this.z = z;
    }

    @Override
    public void setEdgeSize(Double edgeSize) {
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}

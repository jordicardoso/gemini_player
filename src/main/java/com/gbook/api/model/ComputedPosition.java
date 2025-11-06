package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComputedPosition {
    private double x;
    private double y;
    private int z;

    public ComputedPosition() {}

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public int getZ() { return z; }
    public void setZ(int z) { this.z = z; }
}
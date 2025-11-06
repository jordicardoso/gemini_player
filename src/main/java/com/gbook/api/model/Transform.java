package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transform {
    private double k;
    private double x;
    private double y;

    public Transform() {}

    public double getK() { return k; }
    public void setK(double k) { this.k = k; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}
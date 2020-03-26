package com.company;

import java.awt.*;

public class RegularPolygon extends Polygon {
    public boolean isOval;
    public int centerx, centery;

    public boolean isOval() {
        return isOval;
    }

    public void setOval(boolean oval) {
        isOval = oval;
    }

    public int getCenterx() {
        return centerx;
    }

    public int getCentery() {
        return centery;
    }

    public RegularPolygon(int x, int y) {
        this.addPoint(x + 15, y);
        this.addPoint(x, y + 20);
        this.addPoint(x - 15, y);
        this.addPoint(x, y - 20);
        centerx = x;
        centery = y;
        isOval = true;
    }

    public RegularPolygon(int x0, int y0, int radius, int sides) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
}
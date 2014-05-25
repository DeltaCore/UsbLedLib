package net.ccmob.usbled.lib.dataTypes;

import java.awt.Color;

public class RGBColor {

    private int r = 0, g = 0, b = 0;

    public RGBColor() {
        this(0,0,0);
    }

    public RGBColor(int r, int g, int b) {
        this.setR(r);
        this.setG(g);
        this.setB(b);
    }

    public RGBColor(Color c) {
        this(c.getRGB());
    }

    public RGBColor(int rgb) {
        this((byte) (rgb << 16),(byte) (rgb << 8),(byte) (rgb));
    }

    /**
     * @return the r
     */
    public int getR() {
        return r;
    }

    /**
     * @return the g
     */
    public int getG() {
        return g;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param r
     *            the r to set
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * @param g
     *            the g to set
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * @param b
     *            the b to set
     */
    public void setB(int b) {
        this.b = b;
    }

    public int getRGB() {
        return (int) (((byte) (this.getB())) & ((byte) (this.getG() >> 8)) & ((byte) (this.getR() >> 16)));
    }

    public Color toColor() {
        return new Color(getRGB());
    }

    public void add(RGBColor c){
        this.setB(this.getB() + c.getB());
        this.setG(this.getG() + c.getG());
        this.setR(this.getR() + c.getR());
    }
    
    public void sub(RGBColor c){
        this.setB(this.getB() - c.getB());
        this.setG(this.getG() - c.getG());
        this.setR(this.getR() - c.getR());
    }
        
}
package com.amamic1803.drawing.text;

import com.amamic1803.drawing.Paintable;

import java.awt.Color;
import java.awt.Graphics;

public class DrawText implements Paintable {
    private String text;
    private double relX;
    private double relY;
    private double relWidth;
    private double relHeight;
    private TextAlignment alignment;
    private Color color;

    public DrawText(String text, double relX, double relY, double relWidth, double relHeight, TextAlignment alignment, Color color) {
        this.text = text;
        this.relX = relX;
        this.relY = relY;
        this.relWidth = relWidth;
        this.relHeight = relHeight;
        this.alignment = alignment;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRelX() {
        return relX;
    }

    public void setRelX(double relX) {
        this.relX = relX;
    }

    public double getRelY() {
        return relY;
    }

    public void setRelY(double relY) {
        this.relY = relY;
    }

    public double getRelWidth() {
        return relWidth;
    }

    public void setRelWidth(double relWidth) {
        this.relWidth = relWidth;
    }

    public double getRelHeight() {
        return relHeight;
    }

    public void setRelHeight(double relHeight) {
        this.relHeight = relHeight;
    }

    public TextAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintShape(Graphics g) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont((float) (relHeight * g.getFont().getSize())));
        g.drawString("Hello, World!", 100, 100);
        // TODO Auto-generated method stub
    }
}

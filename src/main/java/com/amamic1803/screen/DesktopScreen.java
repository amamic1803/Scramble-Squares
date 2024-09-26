package com.amamic1803.screen;

import com.amamic1803.drawing.DrawingObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DesktopScreen extends JPanel implements Screen {
    private int FPS;
    private WeakReference<JFrame> window;
    private List<DrawingObject> drawingObjects = new ArrayList<>();

    public DesktopScreen(JFrame window, FPS fps) {
        this.window = new WeakReference<>(window);
        this.FPS = fps.getValue();
    }

    @Override
    public int getFPS() {
        return FPS;
    }

    @Override
    public void setFPS(FPS fps) {
        this.FPS = fps.getValue();
    }

    @Override
    public void enterFullScreen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void exitFullScreen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void redraw() {
        repaint();
    }

    @Override
    public List<DrawingObject> getDrawingObjects() {
        return drawingObjects;
    }

    @Override
    public void setDrawingObjects(List<DrawingObject> drawingObjects) {
        this.drawingObjects = drawingObjects;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

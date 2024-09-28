package com.amamic1803.screen;

import com.amamic1803.drawing.DrawingObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DesktopScreen extends JPanel implements Screen {
    private int FPS;
    private WeakReference<JFrame> window;
    private List<DrawingObject> drawingObjects = new ArrayList<>();
    private ScreenState screenState;
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public DesktopScreen(JFrame window, ScreenFPS fps, ScreenState screenState) {
        this.window = new WeakReference<>(window);
        this.FPS = fps.getValue();
        this.screenState = screenState;
    }

    @Override
    public int getFPS() {
        return FPS;
    }

    @Override
    public void setFPS(ScreenFPS fps) {
        this.FPS = fps.getValue();
    }

    @Override
    public ScreenState getScreenState() {
        return screenState;
    }

    @Override
    public void setScreenState(ScreenState screenState) {
        JFrame window = this.window.get();

        if (screenState == this.screenState || window == null) {
            return;
        }

        switch (screenState) {
            case Window -> {}
            case Borderless -> {
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.setUndecorated(true);
            }
            case FullScreen -> {
                window.get().setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.get().setUndecorated(true);
                window.get().add(this);
                window.get().setVisible(true);
            }
        }

        throw new UnsupportedOperationException("Not implemented yet");
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

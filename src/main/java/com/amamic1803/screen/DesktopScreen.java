package com.amamic1803.screen;

import com.amamic1803.drawing.DrawingObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GraphicsDevice;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DesktopScreen extends JPanel implements Screen {
    private int FPS;
    private final WeakReference<JFrame> window;
    private List<DrawingObject> drawingObjects = new ArrayList<>();
    private ScreenState screenState;
    private final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

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

        window.dispose();
        switch (screenState) {
            case Window -> {
                device.setFullScreenWindow(null);
                window.setUndecorated(false);
                window.setExtendedState(JFrame.NORMAL);
                window.setSize(1024, 576);
                window.setLocationRelativeTo(null);
            }
            case Borderless -> {
                window.setUndecorated(true);
                window.setSize(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            case FullScreen -> {
                window.setUndecorated(true);
                device.setFullScreenWindow(window);
            }
        }
        window.setVisible(true);

        this.screenState = screenState;
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

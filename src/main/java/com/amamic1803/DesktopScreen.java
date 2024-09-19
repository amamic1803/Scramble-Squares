package com.amamic1803;

import javax.swing.JPanel;
import java.awt.Graphics;

public class DesktopScreen extends JPanel implements Screen {
    private final JPanel panel;
    private int FPS = 60;

    public DesktopScreen(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public int getWidth() {
        return panel.getWidth();
    }

    @Override
    public int getHeight() {
        return panel.getHeight();
    }

    @Override
    public int getFPS() {
        return FPS;
    }

    @Override
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    @Override
    public void redraw() {
        panel.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

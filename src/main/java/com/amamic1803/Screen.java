package com.amamic1803;

public interface Screen {
    int getWidth();
    int getHeight();
    int getFPS();
    void setFPS(int FPS);
    void redraw();
}

package com.amamic1803.screen;

import com.amamic1803.drawing.Paintable;

import java.util.List;

public interface Screen {
    int getWidth();
    int getHeight();
    int getFPS();
    void setFPS(ScreenFPS fps);
    ScreenState getScreenState();
    void setScreenState(ScreenState screenState);
    List<Paintable> getDrawingObjects();
    void setDrawingObjects(List<Paintable> drawingObjects);
    void redraw();
}

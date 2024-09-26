package com.amamic1803.screen;

import com.amamic1803.drawing.DrawingObject;

import java.util.List;

public interface Screen {
    int getWidth();
    int getHeight();
    int getFPS();
    void setFPS(FPS fps);
    void enterFullScreen();
    void exitFullScreen();
    void redraw();
    List<DrawingObject> getDrawingObjects();
    void setDrawingObjects(List<DrawingObject> drawingObjects);
}

package com.amamic1803.drawing;

import com.amamic1803.drawing.text.DrawText;
import com.amamic1803.drawing.text.TextAlignment;

import java.util.List;

public class Scenes {
    public static void mainMenu(List<Paintable> drawingObjects) {
        drawingObjects.clear();
        drawingObjects.add(new DrawText("Scramble Squares", 0.5, 0.5, TextAlignment.CENTER));
    }
}

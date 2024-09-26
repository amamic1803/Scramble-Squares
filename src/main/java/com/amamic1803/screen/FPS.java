package com.amamic1803.screen;

public enum FPS {
    FPS30(30),
    FPS60(60),
    FPS90(90),
    FPS120(120);

    private final int value;

    FPS(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

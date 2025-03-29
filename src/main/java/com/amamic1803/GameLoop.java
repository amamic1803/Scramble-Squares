package com.amamic1803;

import com.amamic1803.screen.Screen;

import java.util.concurrent.atomic.AtomicBoolean;

public class GameLoop implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread gameThread;
    private final Screen screen;

    public GameLoop(Screen screen) {
        this.screen = screen;
    }

    public void startGame() {
        if (!running.getAndSet(true)) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stopGame() {
        if (running.getAndSet(false)) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean update() {
        System.out.println("Doing game logic...");
        System.out.println("Game loop running...");
        return true;
    }

    @Override
    public void run() {
        while (running.get()) {
            double startTime = System.nanoTime();

            if (update()) {
                screen.redraw();
            }

            double interval = (double) 1_000_000_000 / screen.getFPS();
            double elapsedTime = System.nanoTime() - startTime;
            double remainingTime = interval - elapsedTime;

            if (remainingTime > 0) {
                try {
                    Thread.sleep((long) (remainingTime / 1_000_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

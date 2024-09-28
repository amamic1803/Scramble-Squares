package com.amamic1803;

import com.amamic1803.screen.DesktopScreen;
import com.amamic1803.screen.ScreenFPS;
import com.amamic1803.screen.ScreenState;
import jico.Ico;
import jico.ImageReadException;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setTitle("Scramble Squares");
        window.setSize(1024, 576);
        window.setResizable(true);
        window.setLocationRelativeTo(null);

        try (InputStream inputStream = Main.class.getResourceAsStream("/favicon.ico")) {
            if (inputStream != null) {
                var icons = Ico.read(inputStream);
                window.setIconImages(icons);
            } else {
                throw new IOException("Icon resource not found.");
            }
        } catch (IOException | ImageReadException e) {
            throw new RuntimeException(e);
        }

        window.setLayout(new BorderLayout());

        DesktopScreen screen = new DesktopScreen(window, ScreenFPS.FPS60, ScreenState.Window);
        window.add(screen, BorderLayout.CENTER);

        GameLoop gameLoop = new GameLoop(screen);
        gameLoop.startGame();

        screen.setBackground(Color.BLUE);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                gameLoop.stopGame();
            }
        });

        window.setVisible(true);
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

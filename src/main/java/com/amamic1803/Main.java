package com.amamic1803;

import com.amamic1803.screen.DesktopScreen;
import com.amamic1803.screen.ScreenFPS;
import com.amamic1803.screen.ScreenState;
import jico.Ico;
import jico.ImageReadException;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setTitle("Scramble Squares");
        window.setSize(1024, 576);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setAutoRequestFocus(true);

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

        JButton btn1 = new JButton("Full-Screen");
        btn1.addActionListener(_ -> screen.setScreenState(ScreenState.FullScreen));
        JButton btn2 = new JButton("Normal");
        btn2.addActionListener(_ -> screen.setScreenState(ScreenState.Window));
        JButton btn3 = new JButton("Borderless");
        btn3.addActionListener(_ -> screen.setScreenState(ScreenState.Borderless));
        screen.add(btn1);
        screen.add(btn2);
        screen.add(btn3);
        screen.setBackground(Color.BLUE);

        window.add(screen, BorderLayout.CENTER);

        GameLoop gameLoop = new GameLoop(screen);
        gameLoop.startGame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

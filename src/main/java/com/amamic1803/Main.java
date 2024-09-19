package com.amamic1803;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Hello World");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        window.add(panel, BorderLayout.CENTER);

        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

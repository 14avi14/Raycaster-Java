package main;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2d Ray Caster");

        GamePanel gPanel = new GamePanel();
        window.add(gPanel);

        gPanel.startGameThread();

        // Pack the window; screen will shrink to fit all components(only gPanel)
        window.pack();

        window.setLocationRelativeTo(null); // Put window in center of screen
        window.setVisible(true);
    }
}

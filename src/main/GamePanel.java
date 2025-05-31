package main;

import entity.Player;
import rays.Rays;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Set screen constants
    public final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3;

    public final int tileSize = scale * originalTileSize; // 48x48 tile size
    public final int maxScreenCol = 20; // Tiles across on screen
    public final int maxScreenRow = 10; // Rows of tiles from top to bottom on screen
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;

    int FPS = 60;

    // Game Thread
    Thread gameThread;

    KeyHandler keyH = new KeyHandler();
    public TileManager tileM = new TileManager(this);

    Player player = new Player(this, this.keyH);
    Rays playerRays = new Rays(this, 60, 1.0472 , 800); // 1.0472 = 60 degrees


    public int[] camera = {0, 0};


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true); // Improves performance
        this.addKeyListener(keyH);
        this.setFocusable(true);

        playerRays.setRays();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {


        double frameTime = 1000000000.0/FPS; // Frame time in nanoseconds(billionth)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        long start = System.nanoTime();
        int frameCount = 0;
        // Game loop
        while (gameThread != null) {
            currTime = System.nanoTime();

            delta += (currTime - lastTime) / frameTime; // Fraction of frame that has passed
            lastTime = currTime;

            if (delta >= 1) {
                frameCount++;
                //UPDATE
                update();
                //PAINT
                repaint();
                delta--;
            }

            if (System.nanoTime() - start >= 1000000000) {
                System.out.println("FPS:" + frameCount);
                frameCount = 0;
                start = System.nanoTime();
            }

        }
    }

    public void update() {
        player.update();
        playerRays.updateRayPos(player.angle, player.x, player.y);
        playerRays.castRays(tileSize);

        camera[0] += (player.x - camera[0]) - screenWidth/2;
        camera[1] += (player.y - camera[1]) - screenHeight/2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int[] offset2D = camera.clone();
        offset2D[0] -= screenWidth/4;
        tileM.draw(g2, offset2D);
        player.draw(g2, offset2D);
        playerRays.draw2D(g2, offset2D);

        playerRays.draw3D(g2, screenHeight, screenWidth/2, new int[]{0, 0});




        g2.dispose();
    }


}

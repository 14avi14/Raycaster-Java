package tile;

import java.util.Arrays;
import main.GamePanel;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class TileManager {
    GamePanel gp;

    int[][] intTileMap;

    public int[][] tileDesigns;

    public TileManager(GamePanel gamePanel) {
        gp = gamePanel;
        intTileMap = new int[gp.maxWorldRow][gp.maxWorldCol];
        this.loadMap("C:\\Users\\Avi\\IdeaProjects\\RayCaster\\res\\Map1.txt");
        printMap();
        this.setTileDesigns();

    }

    public void printMap() {
        for (int[] row: intTileMap) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void setTileDesigns() {
        tileDesigns = new int[10][gp.originalTileSize*gp.originalTileSize];
        tileDesigns[0] = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,
                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 1, 1, 1, 0, 1, 1,    1, 0, 1, 1, 1, 0, 1, 1,
                1, 0, 1, 1, 1, 0, 1, 1,    1, 0, 1, 1, 1, 0, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,

                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 1, 1, 1, 0, 1, 1,    1, 0, 1, 1, 1, 0, 1, 1,
                1, 0, 1, 1, 1, 0, 1, 1,    1, 0, 1, 1, 1, 0, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,
                0, 1, 1, 1, 0, 1, 1, 1,    0, 1, 1, 1, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0};

        tileDesigns[1] = new int[] {
                1, 1, 1, 1, 1, 1, 1, 1,    1, 1, 1, 1, 1, 1, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1, 1, 1,    1, 1, 1, 1, 1, 1, 1, 1,

                1, 1, 1, 1, 1, 1, 1, 1,    1, 1, 1, 1, 1, 1, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 1,    1, 0, 0, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1, 1, 1,    1, 1, 1, 1, 1, 1, 1, 1,};
        tileDesigns[2] = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1, 1, 0,    0, 1, 1, 1, 1, 1, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0,    0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0,    0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0,    0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0,    0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0,    0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 1, 1, 0,    0, 1, 1, 1, 1, 1, 0, 0,

                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0,    0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0,    0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0};

        tileDesigns[3] = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,

                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0};


    }

    public int[][] getTileMapCopy() {
        int[][] copy = new int[intTileMap.length][intTileMap[0].length];
        for (int row = 0; row < intTileMap.length; row++) {
            for (int col = 0; col < intTileMap[0].length; col++) {
                copy[row][col] = intTileMap[row][col];
            }
        }
        return copy;
    }

    public void changeTileMap(int row, int col, int type) {
        if (row > 0 && row < intTileMap.length && col > 0 && col < intTileMap[0].length) {
            intTileMap[row][col] = type;
        }
    }

    public void loadMap(String fileName) {
        File file = new File(fileName);
        try {
            Scanner fileScan = new Scanner(file);
            int col;
            int row = 0;
            while (row < intTileMap.length) {
                col = 0;
                while (col < intTileMap[0].length) {
                    intTileMap[row][col] = fileScan.nextInt();
                    col += 1;
                }
                row += 1;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2, int[] offset) {


        int borderSize = 1;

        for (int row = 0; row < intTileMap.length; row++) {
            for (int col = 0; col < intTileMap[0].length; col++) {
                    g2.setColor(Color.BLACK);
                    if (intTileMap[row][col] != 0) {
                        g2.setColor(Color.GREEN);
                    }
                    int x, y;
                    x = col * gp.tileSize - offset[0] + borderSize;
                    y = row * gp.tileSize - offset[1] + borderSize;
                    g2.fillRect(x, y, gp.tileSize - 2 * borderSize, gp.tileSize - 2 * borderSize);

            }
        }
    }


}

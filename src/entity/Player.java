package entity;

import main.GamePanel;
import main.KeyHandler;
import rays.Rays;

import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    int collideType = 0;
    int[] collideLocation = new int[2];

    double angleSpeed;



    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        angle = 0;
        angleSpeed = 3 * (2 * Math.PI / 360);
        speed = 6;
    }

    public void update() {
        int vx = (int)(Math.cos(angle) * speed);
        int vy = (int)(Math.sin(angle) * speed);

        int actualVx, actualVy;
        if (keyH.upPressed) {
            actualVx = vx;
            actualVy = vy;
        } else if (keyH.downPressed) {
            actualVx = -1 * vx;
            actualVy = -1 * vy;
        } else {
            actualVx = 0;
            actualVy = 0;
        }
        collision(actualVx * 2, 0);
        int horizontalCollideType = collideType;
        if (collideType == 0) {
            x += actualVx;
        }
        collision(0, actualVy * 3);
        int verticalCollideType = collideType;
        if (collideType == 0) {
            y += actualVy;
        }

        if (keyH.ePressed) {
            if (horizontalCollideType == 3 || verticalCollideType == 3) {

                if (vx == actualVx && vy == actualVy) {
                    gp.tileM.changeTileMap(collideLocation[0], collideLocation[1], 0);
                }
            }
        }



        if (keyH.leftPressed) {
            angle -= angleSpeed;
        } else if (keyH.rightPressed) {
            angle += angleSpeed;
        }
        if (angle >= Math.PI * 2) {
            angle = 0;
        } else if (angle < 0) {
            angle = Math.PI * 2;
        }


    }

    public void collision(int xo, int yo) {
        int futureX = x + xo;
        int futureY = y + yo;
        int tileX = futureX / gp.tileSize;
        int tileY = futureY / gp.tileSize;
        collideType = 0;
        int[][] tileMap = gp.tileM.getTileMapCopy();
        if (tileX >= 0 && tileX < tileMap[0].length) {
            if (tileY >= 0 && tileY < tileMap.length) {
                collideType =  tileMap[tileY][tileX];
                if (collideType != 0) {
                    collideLocation = new int[]{tileY, tileX};
                }

            }
        }


    }

    public void draw(Graphics2D g2, int[] offset) {
        // Drawing player
        g2.setColor(Color.WHITE);


        int triWidth = 5;
        int triHeight = 20;
        int[] playerXs = {x + (int)(Math.cos(angle - Math.PI/2)*triWidth), x + (int)(Math.cos(angle + Math.PI/2)*triWidth), x + (int)(Math.cos(angle)*triHeight)};
        int[] playerYs = {y + (int)(Math.sin(angle - Math.PI/2)*triWidth), y + (int)(Math.sin(angle + Math.PI/2)*triWidth), y + (int)(Math.sin(angle)*triHeight)};


        for (int i = 0; i < playerXs.length; i++) {
            playerXs[i] -= offset[0];
        }
        for (int i = 0; i < playerYs.length; i++) {
            playerYs[i] -= offset[1];
        }
        g2.fillPolygon(playerXs, playerYs, 3);



    }

}

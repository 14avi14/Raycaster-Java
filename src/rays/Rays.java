package rays;

import main.GamePanel;

import java.awt.*;

public class Rays {
    int numRays;
    int maxLength;
    double FOV;
    double originalTheta;
    GamePanel gp;
    Ray[] rays;


    public Rays(GamePanel gameP, int nRays, double fov, int maxLen) {
        gp = gameP;
        numRays = nRays;
        FOV = fov;
        maxLength = maxLen;
        rays = new Ray[numRays];



    }

    public void setRays() {
        for (int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(maxLength);
        }
    }
    public void updateRayPos(double theta, int x, int y) {
        originalTheta = theta;
        double startTheta = theta - FOV/2;
        double thetaOffset = FOV / numRays;
        for (Ray ray : rays) {
            ray.angle = startTheta;
            ray.setPos(x, y);
            startTheta += thetaOffset;
        }
    }



    public boolean[] getHorizontalCollisions() {
        boolean[] collisionTypes = new boolean[numRays];
        for (int i = 0; i < numRays; i++) {
            collisionTypes[i] = rays[i].getHorizontalCollision();
        }
        return collisionTypes;
    }



    public void castRays(int tileSize) {
        for (Ray r: rays) {
            r.castRay(gp.tileM.getTileMapCopy(), tileSize);
        }
    }

    public void draw2D(Graphics2D g2, int[] offset) {
        g2.setColor(Color.CYAN);
        for (Ray r: rays) {
            r.draw(g2, offset);
        }
    }

    public void draw3D(Graphics2D g2, int screenHeight, int screenWidth, int[] offset) {
        int x = offset[0];
        int y;
        int width = screenWidth / numRays;
        int height;
        int defaultSize = 32;
        g2.setColor(Color.green);
        boolean[] collisionTypes = getHorizontalCollisions();

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, screenWidth, screenHeight);

        for (int i = 0; i < rays.length; i++) {
            double len = rays[i].getLength();
            if (len >= 0) {
                int row = rays[i].truCollidedPos[0];
                int col = rays[i].truCollidedPos[1];
                int type = gp.tileM.getTileMapCopy()[row][col];


                double angleDiff = originalTheta - rays[i].angle;
                double trueLen =  (Math.cos(angleDiff) * len);// Prevent "fish-eye" effect
                height = (int)(screenHeight * defaultSize / (trueLen + 1)); // Max height = screenHeight
                double startY = (screenHeight - height) / 2;

                //g2.fillRect(x, (int)startY, width, height);
                // Draw designs
                double ty = 0;
                double tx;
                if (collisionTypes[i]) {
                    tx = (int) (rays[i].truCollidedPos[2] / gp.scale) % gp.originalTileSize;
                    if (rays[i].angle > Math.PI) {
                        tx = gp.originalTileSize - 1 - tx;
                    }
                } else {
                    tx = (int) (rays[i].truCollidedPos[3] / gp.scale) % gp.originalTileSize;
                    if (rays[i].angle < Math.PI/2 || rays[i].angle > 3 * Math.PI / 2) {
                        tx = gp.originalTileSize - 1 - tx;
                    }
                }
                double stepY = (double)gp.originalTileSize / height; // height of each "pixel"


                for (int j = 0; j < height; j++) {
                    int[] colorArr = new int[]{0, 0, 0};
                    int c = gp.tileM.tileDesigns[type-1][(int)ty*gp.originalTileSize + (int)tx];

                    if (c == 1) {
                        colorArr = new int[]{255, 255, 255};
                    }

                    if (collisionTypes[i]) {
                        colorArr[0] = Math.max(0, colorArr[0] - 50);
                        colorArr[1] = Math.max(0, colorArr[1] - 50);
                        colorArr[2] = Math.max(0, colorArr[2] - 50);
                    }

                    g2.setColor(new Color(colorArr[0], colorArr[1], colorArr[2]));

                    g2.fillRect(x, j + (int)startY, width, 1);

                    ty += stepY;

                }

                x += width;
            }
        }
    }


}

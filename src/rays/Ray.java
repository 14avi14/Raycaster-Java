package rays;

import java.awt.*;

public class Ray {
    double[] startPos, endPos;
    public double angle;

    int[] horizontalCollidedPos = new int[2];
    int[] verticalCollidedPos = new int[2];
    public int[] truCollidedPos = new int[4];

    public int type = 0;
    double maxLen;

    boolean horizontalCollision = false;


    public Ray(double maxL) {
        startPos = new double[2];
        endPos = new double[2];
        maxLen = maxL;
    }

    public void setPos(int x, int y) {
        startPos[0] = x;
        startPos[1] = y;
        endPos[0] = x;
        endPos[1] = y;

    }

    public double[] horizontalCast(int[][] tileMap, int tileSize, double[] dirVector) {
        double[] currPos = startPos.clone();
        double dx, dy;
        double distance = 0;
        // First step
        if (currPos[1] % tileSize != 0) {
            dy = currPos[1] % tileSize;
            if (dirVector[1] > 0) {
                dy = tileSize - dy;
            } else {
                dy *= -1;
            }
            dx = dy/Math.tan(angle);
            currPos[0] += dx;
            currPos[1] += dy;
            distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        }
        dy = tileSize * Math.signum(dirVector[1]);
        dx = dy/Math.tan(angle);

        while (distance < maxLen) {
            int tileX, tileY;
            tileX = (int)currPos[0] / tileSize;
            tileY = (int)currPos[1] / tileSize;
            if (dy < 0) {
                tileY -= 1;
            }
            if (tileX >= 0 && tileX < tileMap[0].length) {
                if (tileY >= 0 && tileY < tileMap.length) {
                    if (tileMap[tileY][tileX] != 0) {
                        horizontalCollidedPos[0] = tileY;
                        horizontalCollidedPos[1] = tileX;
                        return currPos;
                    }
                }
            }
            currPos[0] += dx;
            currPos[1] += dy;
            distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        }
        return currPos;


    }

    public double[] verticalCast(int[][] tileMap, int tileSize, double[] dirVector) {
        // First step
        double[] currPos = startPos.clone();
        double distance = 0;
        double dx, dy;

        dx = currPos[0] % tileSize;
        if (dirVector[0] > 0) {
        dx = tileSize - dx;
        } else {
            dx *= -1;
        }
        dy = dx * Math.tan(angle);
        currPos[0] += dx;
        currPos[1] += dy;
        distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        dx = tileSize * Math.signum(dirVector[0]);
        dy = dx * Math.tan(angle);

        while (distance < maxLen) {
            int tileX, tileY;
            tileX = (int)currPos[0] / tileSize;
            tileY = (int)currPos[1] / tileSize;
            if (dx < 0) {
                tileX -= 1;
            }
            if (tileX >= 0 && tileX < tileMap[0].length) {
                if (tileY >= 0 && tileY < tileMap.length) {
                    if (tileMap[tileY][tileX] != 0) {
                        verticalCollidedPos[0] = tileY;
                        verticalCollidedPos[1] = tileX;
                        return currPos;
                    }
                }
            }

            currPos[0] += dx;
            currPos[1] += dy;
            distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        }

        return currPos;

        // REMEMBER: X and Y -> more than OR equal to(this screwed me over for hours)

    }


    public void castRay(int[][] tileMap, int tileSize) {
        // Need to do to DDA algorithm
        while (angle > Math.PI * 2) {
            angle -= Math.PI * 2;
        }
        while (angle < 0) {
            angle += Math.PI * 2;
        }

        double[] dirVector = {Math.cos(angle), Math.sin(angle)};
        double[] horPos = horizontalCast(tileMap, tileSize, dirVector);
        double horDist = Math.sqrt(Math.pow(horPos[0] - startPos[0], 2) + Math.pow(horPos[1] - startPos[1], 2));
        double[] vertPos = verticalCast(tileMap, tileSize, dirVector);
        double vertDist = Math.sqrt(Math.pow(vertPos[0] - startPos[0], 2) + Math.pow(vertPos[1] - startPos[1], 2));

        if (horDist < vertDist) {
            endPos = horPos;
            horizontalCollision = true;
            truCollidedPos[0] = horizontalCollidedPos[0];
            truCollidedPos[1] = horizontalCollidedPos[1];
            truCollidedPos[2] = (int)horPos[0];
            truCollidedPos[3] = (int)horPos[1];

        } else if (horDist > vertDist) {
            endPos = vertPos;
            horizontalCollision = false;
            truCollidedPos[0] = verticalCollidedPos[0];
            truCollidedPos[1] = verticalCollidedPos[1];
            truCollidedPos[2] = (int)vertPos[0];
            truCollidedPos[3] = (int)vertPos[1];
        }

    }

    public boolean getHorizontalCollision() {
        return horizontalCollision;
    }

    public double getLength() {
        double len = Math.sqrt(Math.pow(endPos[0] - startPos[0], 2) + Math.pow(endPos[1] - startPos[1], 2));
        if (len >= maxLen) {
            return -1; // make nearly invisible
        } else {
            return len;
        }
    }


    public void draw(Graphics2D g2, int[] offset) {
        double[] drawStart = startPos.clone();
        drawStart[0] -= offset[0];
        drawStart[1] -= offset[1];
        double[] drawEnd = endPos.clone();
        drawEnd[0] -= offset[0];
        drawEnd[1] -= offset[1];

        g2.drawLine((int)drawStart[0], (int)drawStart[1], (int)drawEnd[0], (int)drawEnd[1]);
    }
}

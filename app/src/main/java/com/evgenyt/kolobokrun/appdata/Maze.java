package com.evgenyt.kolobokrun.appdata;

import java.util.concurrent.ThreadLocalRandom;

public class Maze {
    private final int width;
    private final int height;
    private final MazeCell[][] cells;
    private int playerX = 0;
    private int playerY = 0;

    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new MazeCell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new MazeCell(x, y);
            }
        }
        for (int x = 0; x < width; x++) {
            cells[x][0].setWallNorth(true);
            cells[x][height - 1].setWallSouth(true);
        }
        for (int y = 0; y < height; y++) {
            cells[0][y].setWallWest(true);
            cells[width - 1][y].setWallEast(true);
        }
        divideGenerator(0, 0, width - 1, height - 1);
    }

    public boolean movePlayer(Direction direction) {
        boolean canGo = false;
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case EAST:
                canGo = !cells[playerX][playerY].isWallEast();
                dx = 1;
                break;
            case WEST:
                canGo = !cells[playerX][playerY].isWallWest();
                dx = -1;
                break;
            case NORTH:
                canGo = !cells[playerX][playerY].isWallNorth();
                dy = -1;
                break;
            case SOUTH:
                canGo = !cells[playerX][playerY].isWallSouth();
                dy = 1;
                break;
        }
        if (canGo) {
            playerX += dx;
            playerY += dy;
        }
        return canGo;
    }

    private void divideGenerator(int x1, int y1, int x2, int y2) {
        if (x2 - x1 < 1 || y2 - y1 < 1)
            return;
        int direction = ThreadLocalRandom.current().nextInt(2);
        if (direction == 0) { // vertical divide
            int x = ThreadLocalRandom.current().nextInt(x1 + 1, x2 + 1);
            setVerticalWall(y1, y2, x, true);
            int holeY = ThreadLocalRandom.current().nextInt(y1 + 1, y2 + 1);
            setWallWestAt(x, holeY, false);
            divideGenerator(x1, y1, x - 1, y2);
            divideGenerator(x, y1, x2, y2);
        } else { // horizontal divide
            int y = ThreadLocalRandom.current().nextInt(y1 + 1, y2 + 1);
            setHorizontalWall(x1, x2, y, true);
            int holeX = ThreadLocalRandom.current().nextInt(x1 + 1, x2 + 1);
            setWallNorthAt(holeX, y, false);
            divideGenerator(x1, y1, x2, y - 1);
            divideGenerator(x1, y, x2, y2);
        }
    }

    public void setVerticalWall(int y1, int y2, int x, boolean wall) {
        if (y1 > y2 || y1 < 0 || y2 >= height || x < 0 || x >= width)
            return;
        for (int y = y1; y <= y2; y++) {
            setWallWestAt(x, y, wall);
        }
    }

    public void setHorizontalWall(int x1, int x2, int y, boolean wall) {
        if (x1 > x2 || x1 < 0 || x2 >= width || y < 0 || y >= height)
            return;
        for (int x = x1; x <= x2; x++) {
            setWallNorthAt(x, y, wall);
        }
    }

    public void setWallNorthAt(int x, int y, boolean wall) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        cells[x][y].setWallNorth(wall);
        if (y > 0)
            cells[x][y - 1].setWallSouth(wall);
    }

    public void setWallSouthAt(int x, int y, boolean wall) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        cells[x][y].setWallSouth(wall);
        if (y < height - 1)
            cells[x][y + 1].setWallNorth(wall);
    }

    public void setWallEastAt(int x, int y, boolean wall) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        cells[x][y].setWallEast(wall);
        if (x < width - 1)
            cells[x + 1][y].setWallWest(wall);
    }

    public void setWallWestAt(int x, int y, boolean wall) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        cells[x][y].setWallWest(wall);
        if (x > 0)
            cells[x - 1][y].setWallEast(wall);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MazeCell[][] getCells() {
        return cells;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}

package com.evgenyt.kolobokrun.appdata;

public class Maze {
    private final int width;
    private final int height;
    private final MazeCell[][] cells;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new MazeCell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new MazeCell();
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
}

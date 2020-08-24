package com.evgenyt.kolobokrun.appdata;

import java.util.Objects;

public class MazeCell {
    private boolean wallNorth;
    private boolean wallEast;
    private boolean wallSouth;
    private boolean wallWest;
    private final int x,y;

    public MazeCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWallNorth() {
        return wallNorth;
    }

    public void setWallNorth(boolean wallNorth) {
        this.wallNorth = wallNorth;
    }

    public boolean isWallEast() {
        return wallEast;
    }

    public void setWallEast(boolean wallEast) {
        this.wallEast = wallEast;
    }

    public boolean isWallSouth() {
        return wallSouth;
    }

    public void setWallSouth(boolean wallSouth) {
        this.wallSouth = wallSouth;
    }

    public boolean isWallWest() {
        return wallWest;
    }

    public void setWallWest(boolean wallWest) {
        this.wallWest = wallWest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCell cell = (MazeCell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

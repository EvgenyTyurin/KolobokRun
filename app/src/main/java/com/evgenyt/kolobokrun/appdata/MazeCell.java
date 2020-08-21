package com.evgenyt.kolobokrun.appdata;

public class MazeCell {
    private boolean wallNorth;
    private boolean wallEast;
    private boolean wallSouth;
    private boolean wallWest;

    public MazeCell(boolean wallNorth, boolean wallEast, boolean wallSouth, boolean wallWest) {
        this.wallNorth = wallNorth;
        this.wallEast = wallEast;
        this.wallSouth = wallSouth;
        this.wallWest = wallWest;
    }

    public MazeCell() {

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
}

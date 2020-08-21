package com.evgenyt.kolobokrun.appdata;

public class AppData {

    private static AppData appData;
    private Maze maze;

    private AppData(){}

    public static AppData getInstance() {
        if (appData == null)
            appData = new AppData();
        return appData;
    }

    public void createMaze(int width, int height) {
        maze = new Maze(width, height);
    }

    public Maze getMaze() {
        return maze;
    }
}

package com.evgenyt.kolobokrun.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.evgenyt.kolobokrun.appdata.AppData;
import com.evgenyt.kolobokrun.appdata.Maze;
import com.evgenyt.kolobokrun.appdata.MazeCell;

public class MazeView extends View {

    AppData appData;
    Maze maze;
    private Paint paintWall = new Paint();
    int scale;
    int padX = 10;
    int padY = 30;

    public MazeView(Context context) {
        super(context);
    }

    public MazeView(Context context, AppData appData, int screenWidth, int screenHeight) {
        super(context);
        this.appData = appData;
        maze = appData.getMaze();
        int scale1 = (screenWidth - (padX * 2)) / maze.getWidth();
        int scale2 = (screenHeight - (padY * 2)) / maze.getHeight();
        scale = Math.min(scale1, scale2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MazeCell[][] cells = maze.getCells();
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                if (cells[x][y].isWallNorth())
                    canvas.drawLine(x * scale + padX, y * scale + padY,
                            (x + 1) * scale + padX, y * scale + padY, paintWall);
                if (cells[x][y].isWallEast())
                    canvas.drawLine((x + 1) * scale + padX, y * scale + padY,
                            (x + 1) * scale + padX, (y + 1) * scale + padY, paintWall);
                if (cells[x][y].isWallSouth())
                    canvas.drawLine(x * scale + padX, (y + 1)  * scale + padY,
                            (x + 1) * scale + padX, (y + 1) * scale + padY, paintWall);
                if (cells[x][y].isWallWest())
                    canvas.drawLine(x * scale + padX, y * scale + padY,
                            x * scale + padX, (y + 1) * scale + padY, paintWall);
            }
        }
    }

}

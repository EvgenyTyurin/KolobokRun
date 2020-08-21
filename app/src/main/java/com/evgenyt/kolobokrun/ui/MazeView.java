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
    private Paint paintWall = new Paint();
    int scale = 100;
    int padX = 10;
    int padY = 10;

    public MazeView(Context context) {
        super(context);
    }

    public MazeView(Context context, AppData appData) {
        super(context);
        this.appData = appData;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Maze maze = appData.getMaze();
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

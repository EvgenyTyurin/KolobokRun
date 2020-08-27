package com.evgenyt.kolobokrun.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.evgenyt.kolobokrun.appdata.AppData;
import com.evgenyt.kolobokrun.appdata.Maze;
import com.evgenyt.kolobokrun.appdata.MazeCell;

public class MazeView extends View implements View.OnTouchListener {

    AppData appData;
    Maze maze;
    private Paint paintWall = new Paint();
    int scale;
    int padX = 10;
    int padY = 10;

    public MazeView(Context context) {
        super(context);
    }

    public MazeView(Context context, AppData appData, int screenWidth, int screenHeight) {
        super(context);
        this.appData = appData;
        maze = appData.getMaze();
        int scale1 = (screenWidth - (padX * 2)) / maze.getWidth();
        int scale2 = (screenHeight - (padY * 2) - 150) / maze.getHeight();
        scale = Math.min(scale1, scale2);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = getMazeX(event.getX());
        int y = getMazeY(event.getY());
        if (x < 0 || x >= maze.getWidth() || y < 0 || y >= maze.getHeight())
            return false;
        int deltaX = x - maze.getPlayerX();
        int deltaY = y - maze.getPlayerY();
        if (Math.abs(deltaX) + Math.abs(deltaY) != 1)
            return false;
        Maze.Direction direction = null;
        if (deltaX == 1)
            direction = Maze.Direction.EAST;
        if (deltaX == -1)
            direction = Maze.Direction.WEST;
        if (deltaY == 1)
            direction = Maze.Direction.SOUTH;
        if (deltaY == -1)
            direction = Maze.Direction.NORTH;
        if (maze.movePlayer(direction))
            invalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw maze
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
        // draw kolobok
        canvas.drawCircle(scrX(maze.getPlayerX()) + scale / 2,
                scrY(maze.getPlayerY()) + scale / 2, scale / 3, paintWall);
    }

    private int getMazeX(float x) {
        return Math.round((x - padX - scale / 2) / scale);
    }

    private int getMazeY(float y) {
        return Math.round((y - padY - scale / 2) / scale);
    }

    private int scrX(int x) {
        return x * scale + padX;
    }

    private int scrY(int y) {
        return y * scale + padY;
    }

}

package com.evgenyt.kolobokrun.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.evgenyt.kolobokrun.appdata.AppData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppData appData = AppData.getInstance();
        appData.createMaze(10, 14);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        MazeView mazeView = new MazeView(this, appData,
                metrics.widthPixels, metrics.heightPixels);
        setContentView(mazeView);
    }
}
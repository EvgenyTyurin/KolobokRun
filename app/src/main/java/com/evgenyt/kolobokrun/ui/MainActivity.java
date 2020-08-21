package com.evgenyt.kolobokrun.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evgenyt.kolobokrun.appdata.AppData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppData appData = AppData.getInstance();
        appData.createMaze(5, 10);
        MazeView mazeView = new MazeView(this, appData);
        setContentView(mazeView);
    }
}
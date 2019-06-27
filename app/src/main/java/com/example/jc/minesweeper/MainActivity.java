package com.example.jc.minesweeper;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jc.minesweeper.views.grid.Grid;

public class MainActivity extends AppCompatActivity {
    TextView bombsLeftTextView;
    TextView timerTextView;
    Handler timerHandler = new Handler();
    boolean isTimerStarted;
    int secondsPassed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when the Reset button is clicked
    public void reset(View v){
        Log.d( "MainActivity", "resetting game");
        MinesweeperGame.getInstance().resetGame();
    }

}

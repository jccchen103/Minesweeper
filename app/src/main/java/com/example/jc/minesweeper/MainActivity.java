package com.example.jc.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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

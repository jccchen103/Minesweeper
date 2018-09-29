package com.example.jc.minesweeper;

import android.content.Context;

import com.example.jc.minesweeper.util.Generator;

/**
 * Created by JC on 9/28/2018.
 */

public class GameEngine {
    private static GameEngine instance;

    private static final BOMB_NUMBER = 10;
    private static final WIDTH = 10;
    private static final HEIGHT = 10;

    private Context context;

    // private constructor
    private GameEngine() {}

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context) {
        this.context = context;
        // create and store the grid using the Generator
        int[][] gameGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);
    }
}

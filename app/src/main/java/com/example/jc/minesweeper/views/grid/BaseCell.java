package com.example.jc.minesweeper.views.grid;

import android.content.Context;
import android.view.View;

import com.example.jc.minesweeper.MinesweeperGame;

/**
 * Created by JC on 9/28/2018.
 * Abstract cell class that stores an integer, has an x-position and y-position, and can be flagged,
 * clicked, or revealed.
 */

public abstract class BaseCell extends View {
    private int value;      // use the integer -1 to represent a bomb
    private boolean isBomb;
    private boolean isClicked;
    private boolean isFlagged;
    private boolean isRevealed;

    private int x, y;
    private int position;

    public BaseCell(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = (value == -1);
        isClicked = false;
        isFlagged = false;
        isRevealed = false;

        this.value = value;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        isClicked = true;
        isRevealed = true;
        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.position = y * MinesweeperGame.COLUMNS + x;

        invalidate();
    }

}

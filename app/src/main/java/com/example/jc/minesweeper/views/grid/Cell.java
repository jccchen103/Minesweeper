package com.example.jc.minesweeper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.jc.minesweeper.MinesweeperGame;
import com.example.jc.minesweeper.R;

/**
 * Created by JC on 9/28/2018.
 * Clickable cell that make up the Minesweeper Grid.
 */

public class Cell extends BaseCell implements View.OnClickListener, View.OnLongClickListener {

    public Cell(Context context, int x, int y) {
        super(context);
        this.setPosition(x, y);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // make cell square based on the width
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);

        if (isFlagged()) {  // first check if the cell is flagged (if so, cannot click)
            drawFlag(canvas);
        } else if (isBomb() && isRevealed() && !isClicked()) {
            drawNormalBomb(canvas);     // show unexploded bomb
        } else {
            if (isClicked()) {
                if (this.getValue() == -1) {    // if player clicked this bomb cell
                    drawExplodedBomb(canvas);
                } else {                        // if player clicked this non-bomb cell
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);             // draw the unrevealed cell
            }
        }
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());    // bound image to this View
        drawable.draw(canvas);
    }

    private void drawNumber( Canvas canvas ){
        Drawable drawable;

        switch (this.getValue()){   // get corresponding image
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
            default:
                return; // value of cell that called this method should be between 0 and 8
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
        drawable.draw(canvas);
    }

    private void drawExplodedBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
        drawable.draw(canvas);
    }

    @Override
    public void onClick(View view) {
        if(!isFlagged()){
            MinesweeperGame.getInstance().click(this.getXPos(), this.getYPos());
        }
    }

    // flag cell on a long click
    @Override
    public boolean onLongClick(View view) {
        MinesweeperGame.getInstance().flag(this.getXPos(), this.getYPos());

        return true;
    }
}

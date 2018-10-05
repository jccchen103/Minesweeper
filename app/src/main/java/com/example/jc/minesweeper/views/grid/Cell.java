package com.example.jc.minesweeper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.jc.minesweeper.GameEngine;
import com.example.jc.minesweeper.R;

/**
 * Created by JC on 9/28/2018.
 */

public class Cell extends BaseCell implements View.OnClickListener {
    public Cell(Context context, int x, int y) {
        super(context);
        this.setPosition(x, y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // make every cell square
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
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

    @Override
    public void onClick(View view) {
        GameEngine.getInstance().click(this.getXPos(), this.getYPos());

    }

}

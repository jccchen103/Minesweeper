package com.example.jc.minesweeper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.jc.minesweeper.R;

/**
 * Created by JC on 9/28/2018.
 */

public class Cell extends BaseCell {
    public Cell(Context context, int position) {
        super(context);
        this.setPosition(position);
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
}

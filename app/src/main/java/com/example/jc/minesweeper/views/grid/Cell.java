package com.example.jc.minesweeper.views.grid;

import android.content.Context;

/**
 * Created by JC on 9/28/2018.
 */

public class Cell extends BaseCell {
    public Cell(Context context, int position) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // make every cell square
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}

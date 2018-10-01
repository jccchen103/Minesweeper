package com.example.jc.minesweeper.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.jc.minesweeper.GameEngine;

/**
 * Created by JC on 9/28/2018.
 */

public class Grid extends GridView {
    public Grid (Context context, AttributeSet attrSet) {
        super(context, attrSet);
        setNumColumns(GameEngine.WIDTH);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GameEngine.getInstance().WIDTH * GameEngine.getInstance().HEIGHT;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            return GameEngine.getInstance().getCell(position);
        }
    }
}

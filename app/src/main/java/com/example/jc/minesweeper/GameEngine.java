package com.example.jc.minesweeper;

import android.content.Context;
import android.view.View;

import com.example.jc.minesweeper.util.Generator;
import com.example.jc.minesweeper.views.grid.Cell;

/**
 * Created by JC on 9/28/2018.
 */

public class GameEngine {
    private static GameEngine instance;

    public static final int BOMB_NUMBER = 10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private Context context;
    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

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
        int[][] valuesGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);
        setGrid(context, valuesGrid);
    }

    /**
     * Number each Cell in the MinesweeperGrid with a integer position.
     */
    private void setGrid(final Context context, final int[][] grid) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (MinesweeperGrid[x][y] == null) {
                    MinesweeperGrid[x][y] = new Cell(context, x, y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCell(int position) {
        int x = position % GameEngine.WIDTH;
        int y = (int)(position / GameEngine.HEIGHT);
        return MinesweeperGrid[x][y];
    }

    public Cell getCell(int x, int y) {
        return MinesweeperGrid[x][y];
    }


    /**
     * Reveal cell (x,y). If the cell has a value of 0, "click" all neighboring cells. On the other
     * hand, if the cell contains a bomb, the bomb "explodes" and the game is over.
     */
    public void click(int x, int y) {
        Cell clickedCell = this.getCell(x, y);
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && !clickedCell.isClicked()) {
            // reveal the clicked cell
            clickedCell.setClicked();

            // if the cell has a value of 0, "click" all neighboring cells
            if (clickedCell.getValue() == 0) {
                for( int dx = -1 ; dx <= 1 ; dx++ ){
                    for( int dy = -1 ; dy <= 1 ; dy++){
                        if( dx != dy ){
                            this.click(x + dx , y + dy);
                        }
                    }
                }
            }

            // if a bomb was clicked
            if (clickedCell.isBomb()) {
                gameOver();
            }
        }
    }

    /**
     * Called on game lost (i.e. when a bomb was clicked on).
     * Reveals all the bombs on the minesweeper grid.
     */
    private void gameOver(){

    }
}

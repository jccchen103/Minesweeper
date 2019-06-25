package com.example.jc.minesweeper;

import android.content.Context;
import android.util.Log;

import com.example.jc.minesweeper.util.Generator;
import com.example.jc.minesweeper.views.grid.Cell;

/**
 * Created by JC on 9/28/2018.
 * Minesweeper game.
 */

public class MinesweeperGame {      // Singleton class
    private static MinesweeperGame instance = null;

    private static final int BOMB_NUMBER = 10;
    public static final int COLUMNS = 10;
    public static final int ROWS = 10;

    private Context context;
    private Cell[][] MinesweeperGrid = new Cell[COLUMNS][ROWS];

    // private constructor
    private MinesweeperGame() {}

    public static MinesweeperGame getInstance() {
        if (instance == null) {
            instance = new MinesweeperGame();
        }
        return instance;
    }

    public void createGrid(Context context) {
        this.context = context;

        // create the grid of values using the Generator and store
        int[][] valuesGrid = Generator.generate(BOMB_NUMBER, COLUMNS, ROWS);
        print(valuesGrid, COLUMNS, ROWS);
        setGrid(context, valuesGrid);
    }

    /**
     * Set each Cell in the MinesweeperGrid with the corresponding grid value.
     */
    private void setGrid(final Context context, final int[][] grid) {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                if (MinesweeperGrid[x][y] == null) {
                    MinesweeperGrid[x][y] = new Cell(context, x, y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCell(int position) {
        int x = position % MinesweeperGame.COLUMNS;
        int y = (int)(position / MinesweeperGame.ROWS);
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
        if (x >= 0 && x < COLUMNS && y >= 0 && y < ROWS){
            Cell clickedCell = getCell(x, y);
            if (!clickedCell.isClicked()) {
                // reveal the clicked cell
                clickedCell.setClicked();

                // if the cell has a value of 0, "click" all neighboring cells
                if (clickedCell.getValue() == 0){
                    for( int dx = -1; dx <= 1; dx++ ){
                        for( int dy = -1 ; dy <= 1 ; dy++){
                            if(dy != 0 || dx != 0){
                                this.click(x + dx , y + dy);
                            }
                        }
                    }
                }

                // if the clicked cell contained a bomb
                else if (clickedCell.isBomb()){
                    System.out.println("GAME LOST");
                    gameOver();
                }
            }

            // check if game has been won
            if(checkWin()){
                System.out.println("GAME WON");
            }
        }
    }

    public void flag(int x , int y){
        Cell flaggedCell = getCell(x, y);
        flaggedCell.setFlagged(!flaggedCell.isFlagged());
        flaggedCell.invalidate();
    }

    /**
     * Handles lost game (i.e. when a bomb was clicked on).
     * Reveal all the bombs on the minesweeper grid.
     */
    private void gameOver(){
        for (int x = 0; x < COLUMNS; x++ ){
            for (int y = 0; y < ROWS; y++){
                getCell(x,y).setRevealed(true);
            }
        }
    }

    /**
     * Check if the game has been won (i.e. if every non-bomb cell has been "clicked").
     */
    private boolean checkWin(){
        for ( int x = 0 ; x < COLUMNS ; x++ ){
            for( int y = 0 ; y < ROWS ; y++ ){
                if (!getCell(x,y).isBomb() && !getCell(x,y).isClicked()){
                    return false;
                }
            }
        }
        return true;
    }

    private static void print( final int[][] grid , final int columns , final int rows ){
        for( int y = 0 ; y < rows ; y++ ){
            String printStr = "| ";
            for( int x = 0 ; x < columns ; x++ ){
                printStr += String.valueOf(grid[x][y]).replace("-1", "B") + " | ";
            }
            Log.d("",printStr);
        }
    }
}

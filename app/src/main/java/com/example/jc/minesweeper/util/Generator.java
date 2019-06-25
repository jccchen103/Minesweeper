package com.example.jc.minesweeper.util;

import java.util.Random;

/**
 * Created by JC on 9/28/2018.
 * Grid generator.
 */

public class Generator {
    /**
     * Returns a 'columns' by 'rows' grid with 'bombnum' bombs (i.e. -1) placed in randoms cells
     * within the grid.
     */
    public static int[][] generate(int bombnum, final int columns, final int rows) {
        // Random for generating where the bombs will be
        Random r = new Random();

        int[][] grid = new int[columns][rows];
        for (int x = 0; x < columns; x++) {
            grid[x] = new int[rows];
        }

        // randomly place bombnum bombs (represented by -1) at empty cells of grid
        while (bombnum > 0) {
            int x = r.nextInt(columns);
            int y = r.nextInt(rows);
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                bombnum--;
            }
        }

        grid = calculateNumbers(grid, columns, rows);   // add numbers to rest of grid

        return grid;
    }

    /**
     * Given a columns by rows grid with bombs (represented by -1), the grid is returned with the
     * count of every non-bomb cell.
     */
    private static int[][] calculateNumbers(int[][] grid, final int columns, final int rows) {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = getCellNum(grid, columns, rows, x, y);
            }
        }

        return grid;
    }

    /**
     * Returns the number of bombs that are neighbors of cell (x,y) in grid, or -1 if the cell
     * contains a bomb itself.
     */
    private static int getCellNum(final int[][] grid, final int columns, final int rows, final int x, final int y) {
        if (grid[x][y] == -1) {
            return -1;
        }

        int count = 0;
        // check all 8 neighbors, and increment count for each bomb found
        if (isBomb(grid, columns, rows, x-1, y+1)) count++;
        if (isBomb(grid, columns, rows, x  , y+1)) count++;
        if (isBomb(grid, columns, rows, x+1, y+1)) count++;
        if (isBomb(grid, columns, rows, x-1, y  )) count++;
        if (isBomb(grid, columns, rows, x+1, y  )) count++;
        if (isBomb(grid, columns, rows, x-1, y-1)) count++;
        if (isBomb(grid, columns, rows, x  , y-1)) count++;
        if (isBomb(grid, columns, rows, x+1, y-1)) count++;

        return count;
    }

    /**
     * Checks whether the passed in position (x,y) contains with a bomb.
     */
    private static boolean isBomb(final int[][] grid, final int columns, final int rows, final int x, final int y) {
        // check if (x,y) is inside of the grid and -1
        return x >= 0 && x < columns && y >= 0 && y < rows && grid[x][y] == -1;
    }
}

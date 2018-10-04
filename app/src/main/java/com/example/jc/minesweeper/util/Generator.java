package com.example.jc.minesweeper.util;

import java.util.Random;

/**
 * Created by JC on 9/28/2018.
 * Grid generator class.
 */

public class Generator {
    /**
     * Returns a 'width' by 'height' grid with bombnum of bombs (i.e. -1) placed in randoms cells
     * within the grid.
     */
    public static int[][] generate(int bombnum, final int width, final int height) {
        // Random for generating where the bombs will be
        Random r = new Random();

        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            grid[x] = new int[height];
        }

        // randomly place bombnum of bombs (represented by -1) at empty cells of grid
        while (bombnum > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                bombnum--;
            }
        }

        grid = calculateNumbers(grid, width, height);   // add numbers to rest of grid

        return grid;
    }

    /**
     * Given a width by height grid with bombs (represented by -1), the grid is returned with the
     * count of every non-bomb cell.
     */
    private static int[][] calculateNumbers(int[][] grid, final int width, final int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = getCellNum(grid, width, height, x, y);
            }
        }

        return grid;
    }

    /**
     * Returns the number of bombs that are neighbors of cell (x,y) in grid, or -1 if the cell
     * contains a bomb itself.
     */
    private static int getCellNum(final int[][] grid, final int width, final int height, final int x, final int y) {
        if (grid[x][y] == -1) {
            return -1;
        }

        int count = 0;
        // check all 8 neighbors, and increment count for each bomb found
        if (isBomb(grid, width, height, x-1, y+1)) count++;
        if (isBomb(grid, width, height, x  , y+1)) count++;
        if (isBomb(grid, width, height, x+1, y+1)) count++;
        if (isBomb(grid, width, height, x-1, y  )) count++;
        if (isBomb(grid, width, height, x+1, y  )) count++;
        if (isBomb(grid, width, height, x-1, y-1)) count++;
        if (isBomb(grid, width, height, x  , y-1)) count++;
        if (isBomb(grid, width, height, x+1, y-1)) count++;

        return count;
    }

    /**
     * Checks whether the passed in position (x,y) contains with a bomb.
     */
    private static boolean isBomb(final int[][] grid, final int width, final int height, final int x, final int y) {
        // check if (x,y) is inside of the grid and -1
        if (x >= 0 && x < width && y >= 0 && y < height && grid[x][y] == -1) {
            return true;
        }
        return false;
    }
}

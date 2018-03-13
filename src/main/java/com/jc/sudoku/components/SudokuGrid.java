package com.jc.sudoku.components;

import com.jc.sudoku.exception.SudokuException;

/**
 * 
 * @author d3m0n
 *
 */
public class SudokuGrid {

    public static final int[] boxRange1 = { 0, 1, 2 };
    public static final int[] boxRange2 = { 3, 4, 5 };
    public static final int[] boxRange3 = { 6, 7, 8 };

    private final Cell[][] grid;
    private int cellsPopulatedCounter = 0;
    public boolean isSolved = false;

    public SudokuGrid() {
        grid = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public SudokuGrid(SudokuGrid originalGrid) {
        grid = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = originalGrid.getCell(i, j).cloneCell();
                this.cellsPopulatedCounter = originalGrid.cellsPopulatedCounter;
                this.isSolved = originalGrid.isSolved;
            }
        }
    }

    public boolean setValue(int x, int y, int value, boolean isInput) {
        
        boolean success = grid[x][y].setValue(value, isInput);
        if (success) {
            removePossibilityFromRowCells(x, y, value);
            removePossibilityFromColumnCells(x, y, value);
            removePossibilityFromBoxCells(x, y, value);
            cellsPopulatedCounter++;
            if (cellsPopulatedCounter == 81) {
                isSolved = true;
            }
            Cell singlePossibilityCell = findSinglePossibilityCell();
            if (singlePossibilityCell != null) {
                return this.setValue(singlePossibilityCell.getX(), singlePossibilityCell.getY(),
                        singlePossibilityCell.getPossibleValues().iterator().next(), false);
            }
            return true;
        } else {
            if(grid[x][y].getValue()==null || grid[x][y].getValue()!=value){
                if(isInput){
                    throw new SudokuException(x, y, "Not a valid value " + value + " for [" + x + "][" + y + "].. existing value + "+grid[x][y].getValue(), SudokuException.INVALID_VALUE);
                }else{
                    throw new SudokuException(x, y, "Might be a Bad Grid. Contradiction encountered while trying to set value: " + value + " for [" + x + "][" + y + "]. Existing value + "+grid[x][y].getValue() +" Possibilities : "+grid[x][y].getPossibleValues(), SudokuException.CONTRADICTION);
                }
            }else{
                return false;
            }
        }

    }

    private Cell findSinglePossibilityCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].getValue() == null && grid[i][j].getPossibleValues().size() == 1) {
                    return grid[i][j];
                }
            }
        }
        return null;
    }

    private void removePossibilityFromRowCells(int x, int y, int value) {
        for (int j = 0; j < 9; j++) {
            if (j != y) {
                grid[x][j].removePossibility(value);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            throw new SudokuException(x, y, "No cell in grid for indexes : x[" + x + "] and y[" + y + "]", null);
        }
        return grid[x][y];
    }

    private void removePossibilityFromColumnCells(int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (i != x) {
                grid[i][y].removePossibility(value);
            }
        }
    }

    private void removePossibilityFromBoxCells(int x, int y, int value) {
        int[] xRange = getBoxRangeByCellIndex(x);
        int[] yRange = getBoxRangeByCellIndex(y);
        for (int i : xRange) {
            for (int j : yRange) {
                if (!(x == i && y == j)) {
                    grid[i][j].removePossibility(value);
                }
            }
        }
    }

    public static int[] getBoxRangeByCellIndex(int index) {
        if (index < 3) {
            return boxRange1;
        } else if (index < 6) {
            return boxRange2;
        } else {
            return boxRange3;
        }
    }

    public static int[] getBoxRangeByBoxIndex(int index) {
        if (index == 0) {
            return boxRange1;
        } else if (index == 1) {
            return boxRange2;
        } else {
            return boxRange3;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(grid[i][j].getValue()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

package com.jc.sudoku;

import java.util.Optional;

import com.jc.sudoku.components.Cell;
import com.jc.sudoku.components.SudokuGrid;
import com.jc.sudoku.exception.SudokuException;

/**
 * 
 * @author d3m0n
 *
 */
public class SudokuSolver {

    protected SudokuGrid preAssumptionSnapshotGrid;

    private int assumedCellCounter = 0;
    private int assumedValueCounter = 0;

    public SudokuGrid solveSudoku(SudokuGrid grid) {
        if (grid == null) {
            throw new SudokuException("Could not solve - perhaps its a bad grid");
        }
        simpleSolve(grid);
        //System.out.println(grid);
        if (grid.isSolved) {
            System.out.println("Yay! Sudoku is solved!");
            return grid;
        } else {
            if (preAssumptionSnapshotGrid == null) {
                preAssumptionSnapshotGrid = grid;
            }
            SudokuGrid assumptionGrid = prepareAssumptionGrid();
            return solveSudoku(assumptionGrid);
        }
    }

    public void simpleSolve(SudokuGrid grid) {
        boolean changesMade = false;
        for (int num = 1; num < 10; num++) {
            changesMade = checkRowCells(grid, num) | changesMade;
            changesMade = checkColumnCells(grid, num) | changesMade;
            changesMade = checkBoxCells(grid, num) | changesMade;
        }
        if (changesMade) {
            simpleSolve(grid);
        }
    }

    private boolean checkRowCells(SudokuGrid grid, int num) {
        boolean changesMade = false;
        for (int i = 0; i < 9; i++) {
            int possibilityCounter = 0;
            int possibleXIndex = -1;
            int possibleYIndex = -1;
            for (int j = 0; j < 9; j++) {
                if (grid.getCell(i, j).getPossibleValues().contains(num)) {
                    possibilityCounter++;
                    possibleXIndex = i;
                    possibleYIndex = j;
                }
            }
            if (possibilityCounter == 1) {
                changesMade = grid.setValue(possibleXIndex, possibleYIndex, num, false);
            }
        }
        return changesMade;
    }

    private boolean checkColumnCells(SudokuGrid grid, int num) {
        boolean changesMade = false;
        for (int j = 0; j < 9; j++) {
            int possibilityCounter = 0;
            int possibleXIndex = -1;
            int possibleYIndex = -1;
            for (int i = 0; i < 9; i++) {
                if (grid.getCell(i, j).getPossibleValues().contains(num)) {
                    possibilityCounter++;
                    possibleXIndex = i;
                    possibleYIndex = j;
                }
            }
            if (possibilityCounter == 1) {
                changesMade = grid.setValue(possibleXIndex, possibleYIndex, num, false);
            }
        }
        return changesMade;
    }

    private boolean checkBoxCells(SudokuGrid grid, int num) {
        boolean changesMade = false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int[] xRange = SudokuGrid.getBoxRangeByBoxIndex(x);
                int[] yRange = SudokuGrid.getBoxRangeByBoxIndex(y);
                int possibilityCounter = 0;
                int possibleXIndex = -1;
                int possibleYIndex = -1;
                for (int i : xRange) {
                    for (int j : yRange) {
                        if (grid.getCell(i, j).getPossibleValues().contains(num)) {
                            possibilityCounter++;
                            possibleXIndex = i;
                            possibleYIndex = j;
                        }
                    }
                }
                if (possibilityCounter == 1) {
                    changesMade = grid.setValue(possibleXIndex, possibleYIndex, num, false);
                }
            }
        }
        return changesMade;
    }

    public SudokuGrid prepareAssumptionGrid() {
        SudokuGrid assumptionGrid = new SudokuGrid(preAssumptionSnapshotGrid);
        Cell assumptionCell = getAssumptionCell(assumptionGrid);
        int assumptionValue = getAssumptionValue(assumptionCell);
        assumptionGrid.setValue(assumptionCell.getX(), assumptionCell.getY(), assumptionValue, false);
        return assumptionGrid;
    }

    private int getAssumptionValue(Cell assumptionCell) {
        Optional<Integer> value = assumptionCell.getPossibleValues().stream().skip(assumedValueCounter++).findFirst();
        if (value.isPresent()) {
//            System.out.println(
//                    "Assuming value " + value.get() + " for grid cell [" + assumptionCell.getX() + "][" + assumptionCell.getY() + "]. "
//                            + "Possible Values : "+assumptionCell.getPossibleValues() );
            if (assumedValueCounter == assumptionCell.getPossibleValues().size()) {
                assumedCellCounter++;
                assumedValueCounter = 0;
            }
            return value.get();
        } else {
            throw new SudokuException(assumptionCell.getX(), assumptionCell.getY(),
                    "Something went wrong, possibleValues is inconsistent " + assumptionCell.getPossibleValues(), SudokuException.CONTRADICTION);
        }
    }

    private Cell getAssumptionCell(SudokuGrid assumptionGrid) {
        int cellCounter = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (assumptionGrid.getCell(i, j).getValue() == null) {
                    if (cellCounter++ == assumedCellCounter) {
                        return assumptionGrid.getCell(i, j);
                    }
                }
            }
        }
        throw new SudokuException(
                "Insufficient or Incorrect Sudoku : Reached assumedCellCounter : " + assumedCellCounter);
    }

}

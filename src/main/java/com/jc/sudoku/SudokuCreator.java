/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.sudoku;

import java.util.Random;

import com.jc.sudoku.components.Cell;
import com.jc.sudoku.components.SudokuGrid;
import com.jc.sudoku.exception.SudokuException;

/**
 *
 * @author d3m0n
 */
public class SudokuCreator {

    public SudokuGrid createSudoku() {
        SudokuGrid grid = new SudokuGrid();
        int inputCellCount = 0;
        while (true) {
            int x = new Random().nextInt(9);
            int y = new Random().nextInt(9);
            Cell cell = grid.getCell(x, y);
            if (cell.getValue() != null) {
                continue;
            }
            int size = cell.getPossibleValues().size();
            // System.out.println("x : "+x + " y: "+y + " cell.getPossibleValues() :
            // "+cell.getPossibleValues());
            if (size == 0) {
                System.err.println("Bad Grid was being created, so restarting the creation");
                // System.err.println(grid);
                return createSudoku();
            }

            int randomIndex = new Random().nextInt(size);
            int randomPossibleValue = (Integer) cell.getPossibleValues().toArray()[randomIndex];
            grid.setValue(x, y, randomPossibleValue, true);
            inputCellCount++;

            if (inputCellCount > 14) {
                SudokuSolver solver = new SudokuSolver();
                SudokuGrid solvedGrid = null;
                try {
                    solvedGrid = solver.solveSudoku(grid);
                } catch (SudokuException se) {
                    // Add another input value and try again
                    System.err.println("For inputCellCount : " + inputCellCount + " Got SudokuException : " + se.getMessage()
                            + " So adding another value and retrying.");
                    continue;
                }
                if (solvedGrid != null) {
                    System.out.println(solvedGrid);
                    break;
                }
            }

        }
        return grid;
    }
}

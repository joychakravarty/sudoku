/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.sudoku;

import java.util.Random;

import com.jc.sudoku.components.Cell;
import com.jc.sudoku.components.CellValueType;
import com.jc.sudoku.components.SudokuGrid;
import com.jc.sudoku.exception.SudokuException;

/**
 * This class creates a solvable SudokuGrid
 * @author d3m0n
 */
public class SudokuCreator {
	
	public static final int EASY_LEVEL = 0;
	public static final int HARD_LEVEL = 1;
	
	private final int difficultyLevel;
	private final boolean solverAssumptionParam;
	public SudokuCreator(int level) {
		difficultyLevel = level;
		if(difficultyLevel == 0) {
			solverAssumptionParam = true;
		}else {
			solverAssumptionParam = false;
		}
		
	}

    public SudokuGrid createSudoku() {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid solvedGrid = null;
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
               // System.err.println("Bad Grid was being created, so restarting the creation");
                // System.err.println(grid);
                return createSudoku();
            }

            int randomIndex = new Random().nextInt(size);
            int randomPossibleValue = (Integer) cell.getPossibleValues().toArray()[randomIndex];
            grid.setValue(x, y, randomPossibleValue, CellValueType.GENERATED);
            inputCellCount++;

            if (inputCellCount > 14) {
                SudokuSolver solver = new SudokuSolver(solverAssumptionParam);
                try {
                    solvedGrid = solver.solveSudoku(grid);
                } catch (SudokuException se) {
                    // Add another input value and try again
//                    System.err.println("For inputCellCount : " + inputCellCount + " Got SudokuException : " + se.getMessage()
//                            + " So adding another value and retrying.");
                    continue;
                }
                if (solvedGrid != null) {
                    System.out.println(solvedGrid);
                    break;
                }
            }

        }
        return solvedGrid;
    }
}

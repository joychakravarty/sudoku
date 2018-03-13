package com.jc.sudoku;

import org.junit.Test;

import com.jc.sudoku.SudokuCreator;
import com.jc.sudoku.SudokuSolver;
import com.jc.sudoku.components.SudokuGrid;

public class TestSudokuCreator {

	@Test
	public void testCreator() {
		SudokuCreator sudokuCreator = new SudokuCreator();
        SudokuGrid grid = sudokuCreator.createSudoku();
        System.out.println("Created Sudoku");
        System.out.println(grid);
        System.out.println("Solved Sudoku");
        System.out.println(new SudokuSolver().solveSudoku(grid));
	}

}

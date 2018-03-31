package com.jc.sudoku;

import org.junit.Test;

import com.jc.sudoku.SudokuCreator;
import com.jc.sudoku.SudokuSolver;
import com.jc.sudoku.components.SudokuGrid;

public class TestSudokuCreator {

	@Test
	public void testEasyCreator() {
		SudokuCreator sudokuCreator = new SudokuCreator(SudokuCreator.EASY_LEVEL);
        SudokuGrid grid = sudokuCreator.createSudoku();
        System.out.println("Created Easy Sudoku");
        System.out.println(grid);
        System.out.println("Solved Sudoku");
        System.out.println(new SudokuSolver(true).solveSudoku(grid));
	}
	
	@Test
	public void testHardCreator() {
		SudokuCreator sudokuCreator = new SudokuCreator(SudokuCreator.HARD_LEVEL);
        SudokuGrid grid = sudokuCreator.createSudoku();
        System.out.println("Created Hard Sudoku");
        System.out.println(grid);
        System.out.println("Solved Sudoku");
        System.out.println(new SudokuSolver(false).solveSudoku(grid));
	}

}

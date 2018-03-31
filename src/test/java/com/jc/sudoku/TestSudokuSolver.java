package com.jc.sudoku;

import org.junit.Before;
import org.junit.Test;

import com.jc.sudoku.components.CellValueType;
import com.jc.sudoku.components.SudokuGrid;

public class TestSudokuSolver {
	
	@Before
	public void before() {
		System.out.println("------------------------------------");
	}

	@Test
	public void testEvil() {
        SudokuGrid grid = new SudokuGrid();

        grid.setValue(0, 0, 7, CellValueType.GENERATED);
        grid.setValue(0, 2, 2, CellValueType.GENERATED);
        grid.setValue(0, 5, 1, CellValueType.GENERATED);
        grid.setValue(0, 7, 5, CellValueType.GENERATED);

        grid.setValue(1, 1, 5, CellValueType.GENERATED);
        grid.setValue(1, 2, 4, CellValueType.GENERATED);

        grid.setValue(2, 4, 9, CellValueType.GENERATED);
        grid.setValue(2, 7, 3, CellValueType.GENERATED);

        grid.setValue(3, 0, 9, CellValueType.GENERATED);
        grid.setValue(3, 3, 1, CellValueType.GENERATED);
        grid.setValue(3, 4, 7, CellValueType.GENERATED);

        grid.setValue(4, 2, 6, CellValueType.GENERATED);
        grid.setValue(4, 4, 3, CellValueType.GENERATED);
        grid.setValue(4, 6, 8, CellValueType.GENERATED);

        grid.setValue(5, 4, 6, CellValueType.GENERATED);
        grid.setValue(5, 5, 2, CellValueType.GENERATED);
        grid.setValue(5, 8, 3, CellValueType.GENERATED);

        grid.setValue(6, 1, 7, CellValueType.GENERATED);
        grid.setValue(6, 4, 5, CellValueType.GENERATED);

        grid.setValue(7, 6, 3, CellValueType.GENERATED);
        grid.setValue(7, 7, 1, CellValueType.GENERATED);

        grid.setValue(8, 1, 2, CellValueType.GENERATED);
        grid.setValue(8, 3, 6, CellValueType.GENERATED);
        grid.setValue(8, 6, 4, CellValueType.GENERATED);
        grid.setValue(8, 8, 5, CellValueType.GENERATED);

        SudokuGrid solved = (new SudokuSolver(true)).solveSudoku(grid);
        System.out.println("testEvil");
        System.out.println(solved);

    }
	
	@Test
	public void testEasy() {
        SudokuGrid grid = new SudokuGrid();

        grid.setValue(0, 0, 4, CellValueType.GENERATED);
        grid.setValue(0, 2, 1, CellValueType.GENERATED);
        grid.setValue(0, 6, 8, CellValueType.GENERATED);
        grid.setValue(0, 7, 6, CellValueType.GENERATED);
        grid.setValue(0, 8, 7, CellValueType.GENERATED);

        grid.setValue(1, 3, 6, CellValueType.GENERATED);
        grid.setValue(1, 4, 7, CellValueType.GENERATED);
        grid.setValue(1, 5, 4, CellValueType.GENERATED);
        grid.setValue(1, 7, 2, CellValueType.GENERATED);
        grid.setValue(1, 8, 9, CellValueType.GENERATED);

        grid.setValue(2, 0, 6, CellValueType.GENERATED);
        grid.setValue(2, 3, 1, CellValueType.GENERATED);

        grid.setValue(3, 0, 1, CellValueType.GENERATED);
        grid.setValue(3, 5, 6, CellValueType.GENERATED);
        grid.setValue(3, 7, 9, CellValueType.GENERATED);
        grid.setValue(3, 8, 5, CellValueType.GENERATED);

        grid.setValue(4, 3, 7, CellValueType.GENERATED);
        grid.setValue(4, 4, 2, CellValueType.GENERATED);
        grid.setValue(4, 5, 1, CellValueType.GENERATED);

        grid.setValue(5, 0, 2, CellValueType.GENERATED);
        grid.setValue(5, 1, 4, CellValueType.GENERATED);
        grid.setValue(5, 3, 9, CellValueType.GENERATED);
        grid.setValue(5, 8, 1, CellValueType.GENERATED);

        grid.setValue(6, 5, 5, CellValueType.GENERATED);
        grid.setValue(6, 8, 6, CellValueType.GENERATED);

        grid.setValue(7, 0, 3, CellValueType.GENERATED);
        grid.setValue(7, 1, 1, CellValueType.GENERATED);
        grid.setValue(7, 3, 8, CellValueType.GENERATED);
        grid.setValue(7, 4, 6, CellValueType.GENERATED);
        grid.setValue(7, 5, 9, CellValueType.GENERATED);

        grid.setValue(8, 0, 9, CellValueType.GENERATED);
        grid.setValue(8, 1, 8, CellValueType.GENERATED);
        grid.setValue(8, 2, 6, CellValueType.GENERATED);
        grid.setValue(8, 6, 5, CellValueType.GENERATED);
        grid.setValue(8, 8, 3, CellValueType.GENERATED);

        SudokuGrid solved = (new SudokuSolver(false)).solveSudoku(grid);
        System.out.println("testEasy");
        System.out.println(solved);
    }
    
	@Test
    public void testEvil2649196178() {
        SudokuGrid grid = new SudokuGrid();

        grid.setValue(0, 2, 3, CellValueType.GENERATED);
        grid.setValue(0, 5, 9, CellValueType.GENERATED);
        grid.setValue(0, 8, 5, CellValueType.GENERATED);

        grid.setValue(1, 2, 4, CellValueType.GENERATED);
        grid.setValue(1, 5, 7, CellValueType.GENERATED);
        grid.setValue(1, 7, 1, CellValueType.GENERATED);

        grid.setValue(2, 1, 7, CellValueType.GENERATED);
        grid.setValue(2, 4, 4, CellValueType.GENERATED);
        grid.setValue(2, 8, 3, CellValueType.GENERATED);

        grid.setValue(3, 2, 8, CellValueType.GENERATED);
        grid.setValue(3, 5, 5, CellValueType.GENERATED);

        grid.setValue(4, 0, 2, CellValueType.GENERATED);
        grid.setValue(4, 4, 7, CellValueType.GENERATED);
        grid.setValue(4, 8, 8, CellValueType.GENERATED);

        grid.setValue(5, 3, 3, CellValueType.GENERATED);
        grid.setValue(5, 6, 6, CellValueType.GENERATED);

        grid.setValue(6, 0, 9, CellValueType.GENERATED);
        grid.setValue(6, 4, 2, CellValueType.GENERATED);
        grid.setValue(6, 7, 6, CellValueType.GENERATED);

        grid.setValue(7, 1, 1, CellValueType.GENERATED);
        grid.setValue(7, 3, 7, CellValueType.GENERATED);
        grid.setValue(7, 6, 9, CellValueType.GENERATED);

        grid.setValue(8, 0, 5, CellValueType.GENERATED);
        grid.setValue(8, 3, 6, CellValueType.GENERATED);
        grid.setValue(8, 6, 3, CellValueType.GENERATED);

        SudokuGrid solved = (new SudokuSolver(true)).solveSudoku(grid);
        System.out.println("testEvil2649196178");
        System.out.println(solved);
    }
    
    
    @Test
    public void testEvil30() {
        SudokuGrid grid = new SudokuGrid();

        grid.setValue(0, 2, 7, CellValueType.GENERATED);
        grid.setValue(0, 4, 9, CellValueType.GENERATED);
        grid.setValue(0, 6, 4, CellValueType.GENERATED);
        grid.setValue(0, 7, 3, CellValueType.GENERATED);

        grid.setValue(1, 0, 3, CellValueType.GENERATED);
        grid.setValue(1, 2, 8, CellValueType.GENERATED);
        grid.setValue(1, 5, 5, CellValueType.GENERATED);
        grid.setValue(1, 8, 2, CellValueType.GENERATED);

        grid.setValue(2, 5, 6, CellValueType.GENERATED);
        grid.setValue(2, 6, 1, CellValueType.GENERATED);

        grid.setValue(3, 1, 3, CellValueType.GENERATED);
        grid.setValue(3, 2, 9, CellValueType.GENERATED);
        grid.setValue(3, 5, 4, CellValueType.GENERATED);

        grid.setValue(4, 0, 2, CellValueType.GENERATED);
        grid.setValue(4, 3, 8, CellValueType.GENERATED);
        grid.setValue(4, 8, 5, CellValueType.GENERATED);

        grid.setValue(5, 1, 6, CellValueType.GENERATED);
        grid.setValue(5, 5, 1, CellValueType.GENERATED);
        grid.setValue(5, 7, 7, CellValueType.GENERATED);

        grid.setValue(6, 1, 8, CellValueType.GENERATED);
        grid.setValue(6, 4, 1, CellValueType.GENERATED);
        grid.setValue(6, 8, 6, CellValueType.GENERATED);

        grid.setValue(7, 0, 7, CellValueType.GENERATED);
        grid.setValue(7, 1, 2, CellValueType.GENERATED);
        grid.setValue(7, 6, 5, CellValueType.GENERATED);

        grid.setValue(8, 1, 9, CellValueType.GENERATED);
        grid.setValue(8, 5, 3, CellValueType.GENERATED);
        grid.setValue(8, 8, 4, CellValueType.GENERATED);

        SudokuGrid solved = (new SudokuSolver(true)).solveSudoku(grid);
        System.out.println("testEvil30");
        System.out.println(solved);

    }


}

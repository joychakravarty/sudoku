package com.jc.sudoku.exception;

/**
 * 
 * @author d3m0n
 *
 */
public class SudokuException extends RuntimeException {
    
        public static final String INVALID_VALUE = "Invalid input";
        public static final String CONTRADICTION = "Contradiction encountered. Check all values";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
        private String userMsg = "";
        
        private boolean isCritical = false;
	
	public SudokuException() {
		super();
	}
	
	public SudokuException(String msg) {
		super(msg);
	}
	
	public SudokuException(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public SudokuException(int x, int y, String msg, String userMsg) {
		super(msg);
		this.x = x;
		this.y = y;
                this.userMsg = userMsg;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
        
        public String getUserMsg(){
            return this.userMsg;
        }
        
        public boolean isCritical(){
            return this.isCritical;
        }
        
        public void setIsCritical(boolean isCritical){
            this.isCritical = isCritical;
        }
}

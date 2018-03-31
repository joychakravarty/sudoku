package com.jc.sudoku.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author d3m0n
 *
 */
public class Cell {

    static final List<Integer> allPossibleValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final int x, y;
    private final Set<Integer> possibleValues = new TreeSet<>(allPossibleValues);

    private Integer value = null;
    private CellValueType valueType = null;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected boolean removePossibility(int val) {
        return possibleValues.remove(val);
    }

    protected boolean setValue(int value, CellValueType valueType) {
        if (this.value == null && this.possibleValues.contains(value)) {
            this.value = value;
            this.valueType = valueType;
            removeOtherPossibilities(value);
            return true;
        } else {
            return false;
        }
    }

    private void removeOtherPossibilities(int value) {
        Iterator<Integer> itr = possibleValues.iterator();
        while (itr.hasNext()) {
            Integer currentVal = itr.next();
            if (currentVal != value) {
                itr.remove();
            }
        }
    }

    public Integer getValue() {
        return this.value;
    }

    public CellValueType getValueType() {
        return this.valueType;
    }
    
    public void setValueType(CellValueType valueType) {
    		this.valueType = valueType;
    }

    public Set<Integer> getPossibleValues() {
        return Collections.unmodifiableSet(possibleValues);
    }

    public Cell cloneCell() {
        Cell cell = new Cell(x, y);
        cell.value = this.value;
        cell.valueType = this.valueType;
        cell.possibleValues.removeAll(allPossibleValues);
        cell.possibleValues.addAll(this.possibleValues);
        return cell;
    }

}

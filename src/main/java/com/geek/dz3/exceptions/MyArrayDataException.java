package com.geek.dz3.exceptions;

public class MyArrayDataException extends NumberFormatException {
    private int col;
    private int row;

    public MyArrayDataException(String message, int col, int row) {
        super(message);
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}

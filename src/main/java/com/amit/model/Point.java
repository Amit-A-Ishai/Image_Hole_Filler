package com.amit.model;

public class Point {

    private int row;
    private int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point(Point point) {
        this.row = point.getRow();
        this.col = point.getCol();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point &&
                getRow() == ((Point)obj).getRow() &&
                getCol() == ((Point)obj).getCol();
    }
}

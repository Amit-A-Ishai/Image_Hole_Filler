package com.amit.model;

import java.util.ArrayList;

public class Hole {

    private final ArrayList<int[]> boundary;
    private final ArrayList<int[]> body;

    public Hole(ArrayList<int[]> boundary, ArrayList<int[]> body) {
        this.boundary = boundary;
        this.body = body;
    }

    public ArrayList<int[]> getBoundary() {
        return boundary;
    }

    public ArrayList<int[]> getBody() {
        return body;
    }
}

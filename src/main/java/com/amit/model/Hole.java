package com.amit.model;

import java.util.ArrayList;

/**
 * Model for holding hole information
 * */
public class Hole {

    // The outer boundary of the hole
    private final ArrayList<Point> boundary;

    // The hole body pixels
    private final ArrayList<Point> body;

    public Hole(ArrayList<Point> boundary, ArrayList<Point> body) {
        this.boundary = boundary;
        this.body = body;
    }

    public ArrayList<Point> getBoundary() {
        return boundary;
    }

    public ArrayList<Point> getBody() {
        return body;
    }
}

package com.amit.holedetection;

import com.amit.ImageProcessing;
import com.amit.NumericInnerBodyDefinition;
import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Finds holes using
 * 1. Bfs to find all the pixels belonging to the hole
 * 2. A relaxed version of FindContour to find the inner boundary of the hole
 * 3. Use the inner boundary to find the outer boundary
 * */
public class BFSAndFindContourHoleDetector extends HoleDetector {

    public BFSAndFindContourHoleDetector(float holeValue) {
        super(holeValue);
    }

    @Override
    public Hole detect(float [][] matrix, NeighbourFinder neighbourFinder) {

        ArrayList<Point> body = detectHoleBody(matrix, neighbourFinder);

        if(body == null){
            // TODO throw exception? No hole body found
        }

        ArrayList<Point> innerBoundary = ImageProcessing.findInnerContour(matrix, body.get(0),
                new NumericInnerBodyDefinition(holeValue), neighbourFinder);

        ArrayList<Point> boundary = calcOuterBoundaryFromInnerBoundary(matrix, innerBoundary, neighbourFinder);

        return new Hole(boundary, body);
    }

    /**
     * Traverse inner boundary, and uniquely add all non-hole neighbours.
     * @param matrix the image data
     * @param innerBoundary the inner boundary of the hole
     * @param neighbourFinder
     * @return list of points belonging to the outer boundary of the hole
     * */
    private ArrayList<Point> calcOuterBoundaryFromInnerBoundary(float[][] matrix, ArrayList<Point> innerBoundary, NeighbourFinder neighbourFinder) {
        ArrayList<Point> ans = new ArrayList<>();

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for(Point coordinate : innerBoundary){

            for(Point neighbour : neighbourFinder.find(coordinate)){

                if(!isHoleBody(neighbour, matrix) && !presentInMap(neighbour.getRow(), neighbour.getCol(), map)) {
                    addToMap(neighbour.getRow(), neighbour.getCol(), map);
                    ans.add(new Point(neighbour));
                }

            }

        }

        return ans;
    }

    private void addToMap(int i, int j, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> js = map.getOrDefault(i, new HashSet<>());
        js.add(j);
        map.put(i, js);
    }

    private boolean presentInMap(int i, int j, HashMap<Integer, HashSet<Integer>> map) {
        return map.containsKey(i) && map.get(i).contains(j);
    }

    private ArrayList<Point> detectHoleBody(float[][] matrix, NeighbourFinder neighbourFinder) {

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){

                if(isHoleBody(i, j, matrix)){
                    return findHoleBodyBFS(matrix, i, j, neighbourFinder);
                }

            }
        }

        return null;
    }

    private ArrayList<Point> findHoleBodyBFS(float[][] matrix, int i, int j, NeighbourFinder neighbourFinder) {

        boolean [][] addedToList = new boolean[matrix.length][matrix[0].length];

        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(i,j));
        addedToList[i][j] = true;

        int index = 0;

        Point tmp;

        while(index < list.size()) {

            tmp = list.get(index);

            for(Point neighbour : neighbourFinder.find(tmp)) {

                if(isHoleBody(neighbour, matrix) && !addedToList[neighbour.getRow()][neighbour.getCol()]){
                    addedToList[neighbour.getRow()][neighbour.getCol()] = true;
                    list.add(neighbour);
                }

            }

            index++;
        }

        return list;
    }

}

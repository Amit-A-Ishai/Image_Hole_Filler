package com.amit.holedetection;

import com.amit.ImageProcessing;
import com.amit.NumericInnerBodyDefinition;
import com.amit.model.Hole;
import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BFSAndFindContourHoleDetector extends HoleDetector {

    public BFSAndFindContourHoleDetector(float holeValue) {
        super(holeValue);
    }

    @Override
    public Hole detect(float [][] matrix, NeighbourFinder neighbourFinder) {

        ArrayList<int[]> body = detectHoleBody(matrix, neighbourFinder);

        if(body == null){
            // TODO throw exception? No hole body found
        }

        ArrayList<int[]> innerBoundary = ImageProcessing.findInnerContour(matrix, body.get(0)[0], body.get(0)[1],
                new NumericInnerBodyDefinition(holeValue), neighbourFinder);

        ArrayList<int[]> boundary = calcOuterBoundaryFromInnerBoundary(matrix, innerBoundary, neighbourFinder);

        return new Hole(boundary, body);
    }

    private ArrayList<int[]> calcOuterBoundaryFromInnerBoundary(float[][] matrix, ArrayList<int[]> innerBoundary, NeighbourFinder neighbourFinder) {
        ArrayList<int[]> ans = new ArrayList<>();

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for(int [] coordinate : innerBoundary){

            for(int [] neighbour : neighbourFinder.find(coordinate[0], coordinate[1])){

                if(!isHoleBody(neighbour[0], neighbour[1], matrix) && !presentInMap(neighbour[0], neighbour[1], map)) {
                    addToMap(neighbour[0], neighbour[1], map);
                    ans.add(new int[]{neighbour[0], neighbour[1]});
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

    private ArrayList<int[]> detectHoleBody(float[][] matrix, NeighbourFinder neighbourFinder) {

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){

                if(isHoleBody(i, j, matrix)){
                    return findHoleBodyBFS(matrix, i, j, neighbourFinder);
                }

            }
        }

        return null;
    }

    private ArrayList<int[]> findHoleBodyBFS(float[][] matrix, int i, int j, NeighbourFinder neighbourFinder) {

        boolean [][] addedToList = new boolean[matrix.length][matrix[0].length];

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{i,j});
        addedToList[i][j] = true;

        int index = 0;

        int [] tmp;

        while(index < list.size()) {

            tmp = list.get(index);

            if(!isHoleBody(tmp[0], tmp[1], matrix)){
                index++;
                continue;
            }

            for(int [] neighbour : neighbourFinder.find(tmp[0], tmp[1])) {

                if(!addedToList[neighbour[0]][neighbour[1]]){
                    addedToList[neighbour[0]][neighbour[1]] = true;
                    list.add(neighbour);
                }

            }

           index++;
        }

        return list;
    }

}

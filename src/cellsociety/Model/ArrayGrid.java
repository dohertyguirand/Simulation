package cellsociety.Model;

import cellsociety.Model.Grid;

public class ArrayGrid extends Grid {

    private static int mySize; //for all length calculations I used myArray.length and myArray[0].length just in case myArray is not a square
    public static int[][] myArray;
    private static int[][] myReferenceArray;

    public ArrayGrid(int size) { // assume its a square
        mySize = size;
        myArray = new int[mySize][mySize];
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myArray[i][j] = -1;
            }
        }
    }

    @Override
    public int getSize() {
        return mySize;
    }

    @Override
    public void initializeCell(int row, int col, int state) {
        myArray[row][col] = state;
    }

    @Override
    public void initializeDefaultCell(int state) {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (myArray[i][j] == -1) {
                    myArray[i][j] = state;
                }
            }
        }
    }

    @Override
    public void updateCell(int row, int col, int newState) {
        myArray[row][col] = newState;
    }

    @Override
    public int[] checkNeighbors(int row, int col){
        if (row==0 && col==0) {
            myReferenceArray = myArray;
        }
        int numNeighbors = 0;
        int[] neighbors = new int[8];
        int[] rDelta = {0,0,1,-1,1,1,-1,-1};
        int[] cDelta = {1,-1,0,0,1,-1,1,-1};
        for(int i = 0; i < rDelta.length; i ++) {
           int neighborRow = row + rDelta[i];
           int neighborCol = col + cDelta[i];
           if (inBounds(neighborRow, neighborCol)) {
               neighbors[numNeighbors] = getReferenceState(neighborRow,neighborCol);
               numNeighbors = numNeighbors + 1;
           }
        }
        for (int i = 0; i < (8-numNeighbors); i ++) {
            neighbors[numNeighbors] = -1;
            numNeighbors = numNeighbors + 1;
        }
       return neighbors;
    }

    @Override
    public int getCurrentState(int row, int col) {
        return myArray[row][col];
    }

    private int getReferenceState(int row, int col) {
        return myReferenceArray[row][col];
    }

    private boolean inBounds(int r, int c){
        return (r < myArray.length && r >= 0 && c < myArray[0].length && c >= 0);
    }

}
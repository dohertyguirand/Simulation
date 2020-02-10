package cellsociety.View;

import javafx.scene.shape.Polygon;

public class Triangle {

    private Polygon myTriangle;
    protected int myNumber;


    /**
     *
     *
     ***/

    public Triangle(boolean orientation, double row, double col, double tileSize) {
        myTriangle = new Polygon();
        int r = (int) row;
        int c = (int) col;
        myNumber = r*10 + c;
        if (orientation) {
            myTriangle.getPoints().addAll(row, col, row, col+tileSize, row+tileSize, col+(tileSize/2));
        } else {
            myTriangle.getPoints().addAll(row+tileSize, col, row, col+(tileSize/2), row+tileSize, col+tileSize);
        }
    }

    public Polygon getPolygon() {
        return myTriangle;
    }
}
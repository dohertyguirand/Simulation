package cellsociety.View;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {

    Rectangle myShape;
    int myNumber;

    public Square(int row, int col, double size, Color color){
        myShape = new Rectangle(size,size,color);
        myNumber = (row*10) + col;
    }


}
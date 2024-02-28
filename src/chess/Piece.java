package chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;

public abstract class Piece {
    int row;
    int col;
    String color;
    Piece(int row, int col, String color){
        this.row = row;
        this.col = col;
        this.color = color;

    }
    public abstract boolean isValidMove(int destRow, int destCol);
    public boolean move(int destRow, int destCol){
        int sourceRow = this.row;
        int sourceCol = this.col;
        if (isValidMove(destRow, destCol)) {
            this.col = destCol;
            this.row = destRow;
        }
        if (this.row != sourceRow || this.col != sourceCol) {
            return true;
        } else {
            return false;
        }
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    public String getColor(){
        return this.color;
    }
    public int [] getPosition(){
        int [] pos = {this.row, this.col};
        return pos;
    }
    public ImageView getImage(String color){
        String imagePath;
        if (color.equals("white")) {
            imagePath = "file:images/white_" + getClass().getSimpleName().toLowerCase() + ".png";
        } else {
            imagePath = "file:images/black_" + getClass().getSimpleName().toLowerCase() + ".png";
        }
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public void drawPiece( Button[][] squares){
        ImageView image = getImage(this.color);
        squares[this.row][this.col].setGraphic(image);
    }
    public void setPosition(int destRow, int destCol){
        this.row = destRow;
        this.col = destCol;
    };
    public boolean isValidToCapture(int destRow, int destCol){
        boolean validCapture = isValidMove(destRow,destCol);
        if(validCapture){
            return true;
        }else {
            return false;
        }
    }


}

package chess;

public class Rook extends Piece{
    Rook(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        if((destRow != this.row && destCol == this.col)||(destRow == this.row && destCol != this.col)){
            return true;
        }else {
            return false;
        }
    }
}

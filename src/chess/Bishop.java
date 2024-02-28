package chess;

public class Bishop extends Piece{
    Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        return Math.abs(destRow - this.row) == Math.abs(destCol - this.col);
    }

}

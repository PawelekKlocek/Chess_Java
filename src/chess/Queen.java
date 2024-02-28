package chess;

public class Queen extends Piece{
    Queen(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        if (destRow == this.row || destCol == this.col) {
            return true;
        }
        return Math.abs(destRow - this.row) == Math.abs(destCol - this.col);
    }

}

package chess;

public class Knight extends Piece{
    Knight(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - this.row);
        int colDiff = Math.abs(destCol - this.col);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }


}

package chess;


public class Pawn extends Piece {

    Pawn(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        if (this.color.equals("black") && destRow == this.row + 1 && destCol == this.col ||
                this.color.equals("white") && destRow == this.row - 1 && destCol == this.col ||
                this.row == 1 && this.color.equals("black") && destRow == this.row + 2 && destCol == this.col ||
                this.row == 6 && this.color.equals("white") && destRow == this.row - 2 && destCol == this.col
        ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValidToCapture(int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - this.row);
        int colDiff = Math.abs(destCol - this.col);

        if (colDiff == 1) {
            if (this.color.equals("white")) {
                if (destRow == this.row - 1) {
                    return true;
                }
            }
            else if (this.color.equals("black")) {
                if (destRow == this.row + 1) {
                    return true;
                }
            }
        }
        return false;
    }

}

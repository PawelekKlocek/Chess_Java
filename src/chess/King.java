package chess;
public class King extends Piece{
    King(int row, int col, String color) {
        super(row, col, color);

    }

    @Override
    public boolean isValidMove(int destRow, int destCol) {
        if(destRow == this.row - 1 && destCol == this.col ||
                destRow == this.row + 1 && destCol == this.col ||
                destRow == this.row && destCol == this.col - 1 ||
                destRow == this.row && destCol == this.col + 1 ||
                destCol == this.col + 1 && destRow == this.row + 1 ||
                destCol == this.col - 1 && destRow == this.row - 1 ||
                destCol == this.col + 1 && destRow == this.row - 1 ||
                destCol == this.col - 1 && destRow == this.row + 1) {
            return true;
        }else {
            return false;
        }

    }
}

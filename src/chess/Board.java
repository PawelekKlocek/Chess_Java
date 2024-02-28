package chess;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Board {
    private String turn = "white";
    private Button[][] squares;
    private Piece[] pieces;
    private Piece clickedPiece;
    private Button selectedButton = null;
    private Button buttonToUnclick = null;
    private int blackScore = 0;
    private int whiteScore = 0;

    public Board(GridPane gridPane) {
        squares = new Button[8][8];
        pieces = new Piece[32];
        initializeBoard(gridPane);
        initializePieces();
        initializeGameLoop();

    }
    public void initializeBoard(GridPane gridPane) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-background-color: white;");
                } else {
                    button.setStyle("-fx-background-color: grey;");
                }
                squares[row][col] = button;
                gridPane.add(button, col, row);
            }
        }
    }
    private Piece[] initializePieces() {
        pieces[0] = new King(7,4,"white");
        pieces[1] = new King(0,4,"black");
        pieces[2] = new Pawn(1,0,"black");
        pieces[3] = new Pawn(1,1,"black");
        pieces[4] = new Pawn(1,2,"black");
        pieces[5] = new Pawn(1,3,"black");
        pieces[6] = new Pawn(1,4,"black");
        pieces[7] = new Pawn(1,5,"black");
        pieces[8] = new Pawn(1,6,"black");
        pieces[9] = new Pawn(1,7,"black");
        pieces[10] = new Pawn(6,0,"white");
        pieces[11] = new Pawn(6,1,"white");
        pieces[12] = new Pawn(6,2,"white");
        pieces[13] = new Pawn(6,3,"white");
        pieces[14] = new Pawn(6,4,"white");
        pieces[15] = new Pawn(6,5,"white");
        pieces[16] = new Pawn(6,6,"white");
        pieces[17] = new Pawn(6,7,"white");
        pieces[18] = new Rook(0,0,"black");
        pieces[19] = new Rook(0,7,"black");
        pieces[20] = new Rook(7,7,"white");
        pieces[21] = new Rook(7,0,"white");
        pieces[22] = new Queen(0,3,"black");
        pieces[23] = new Queen(7,3,"white");
        pieces[24] = new Bishop(0,2,"black");
        pieces[25] = new Bishop(0,5,"black");
        pieces[26] = new Bishop(7,2,"white");
        pieces[27] = new Bishop(7,5,"white");
        pieces[28] = new Knight(7,6,"white");
        pieces[29] = new Knight(7,1,"white");
        pieces[30] = new Knight(0,6,"black");
        pieces[31] = new Knight(0,1,"black");
        redrawPieces();

        return pieces;
    }

    private String changePlayer(){
        if (this.turn.equals("white")){
            this.turn = "black";
        }else {
            this.turn = "white";
        }
        return turn;
    }
    private void initializeGameLoop() {
        for (Button[] row : squares) {
            for (Button button : row) {
                button.setOnAction(e -> handleButtonClick(button));
            }
        }
    }

    private void handleButtonClick(Button button) {
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);

        boolean isPieceFound = false;

        if(clickedPiece == null){
            for(Piece piece: pieces) {
                if(piece != null && piece.row == row && piece.col == col) {
                    isPieceFound = true;
                    clickedPiece = piece;
                    if(buttonToUnclick!=null){
                        setUnclickedButton(buttonToUnclick);
                    }
                    break;
                }
            }
            if (isPieceFound) {
                if (selectedButton != null) {
                    setUnclickedButton(selectedButton);
                }
                selectedButton = button;
                setClickedButton(button);

            }
        } else {
            if(clickedPiece.color.equals(turn)){
                int [] pos = clickedPiece.getPosition();
                int[] kingpos = findKingPosition(clickedPiece.color);


                if ((squares[row][col].getGraphic() == null && clickedPiece.move(row, col)) && !isKingChecked(this.turn, kingpos[0], kingpos[1])) {
                    deleteGraphic(pos);
                    setUnclickedButton(selectedButton);
                    setClickedButton(squares[row][col]);
                    redrawPieces();
                    changePlayer();
                    buttonToUnclick = squares[row][col];
                    clickedPiece = null;

                } else if (isKingChecked(this.turn, kingpos[0], kingpos[1])) {
                    System.out.println("rusz sie królem ");
                    kingsEscape(this.turn);
                    changePlayer();


                } else if((squares[row][col].getGraphic() != null && clickedPiece.isValidToCapture(row, col)) && !getPieceAtPosition(row,col).getColor().equals(clickedPiece.color)) {
                    removePiece(row, col);
                    clickedPiece.setPosition(row, col);
                    setUnclickedButton(selectedButton);
                    setClickedButton(squares[row][col]);
                    deleteGraphic(pos);
                    redrawPieces();
                    if (isKingChecked(oppositeColor(clickedPiece.color), kingpos[0], kingpos[1])) {
                        System.out.println("szach co " + oppositeColor(clickedPiece.color));
                    }
                    changePlayer();
                    buttonToUnclick = squares[row][col];
                    clickedPiece = null;

                }
                else {
                    clickedPiece = null;
                }

            }else {
                clickedPiece = null;
            }
        }
    }


    private void deleteGraphic(int [] position){
        Button buttonToDelete = squares[position[0]][position[1]];
        buttonToDelete.setGraphic(null);
    }
    private String oppositeColor(String color){
        if (color.equals("white")){
            return "black";
        }else{
            return "white";
        }
    }
    private boolean isKingChecked(String color, int row, int col){
        for (Piece piece : pieces) {
            if (piece != null && !piece.getColor().equals(color)) {
                if (piece.isValidMove(row, col)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean kingsEscape(String color) {
        int kingRow = -1;
        int kingCol = -1;

        for (Piece piece : pieces) {
            if (piece != null && piece instanceof King && piece.getColor().equals(color)) {
                kingRow = piece.row;
                kingCol = piece.col;
                break;
            }
        }


        return false;
    }
    private boolean isSquareEmpty(int row, int col) {
        for (Piece piece : pieces) {
            if (piece != null && piece.getRow() == row && piece.getCol() == col) {
                return false;
            }
        }
        return true;
    }

    private int[] findKingPosition(String color) {
        int[] kingPosition = new int[]{-1, -1};

        for (Piece piece : pieces) {
            if (piece != null && piece instanceof King && piece.getColor().equals(color)) {
                kingPosition[0] = piece.getRow();
                kingPosition[1] = piece.getCol();
                break;
            }
        }

        return kingPosition;
    }

    private void setClickedButton(Button button){
        button.setStyle("-fx-padding: 0px;-fx-background-color: " + (button.getStyle().contains("white") ? "white" : "gray") + "; -fx-border-color: black; -fx-border-width: 2.5px;");
    }
    private void setUnclickedButton(Button button) {
        String originalColor = button.getStyle().contains("white") ? "white" : "gray";
        button.setStyle("-fx-background-color: " + originalColor + ";");
    }
    public Piece getPieceAtPosition(int row, int col) {
        for (Piece piece : pieces) {
            if (piece != null && piece.getRow() == row && piece.getCol() == col) {
                return piece;
            }
        }
        return null;
    }
    private int updateBlackScore(Piece piece) {
        if (piece.getColor().equals("white")) {
            switch (piece.getClass().getSimpleName()) {
                case "Pawn":
                    blackScore += 1;
                    break;
                case "Bishop":
                    blackScore += 3;
                    break;
                case "Knight":
                    blackScore += 3;
                    break;
                case "Rook":
                    blackScore += 5;
                    break;
                case "Queen":
                    blackScore += 9;
                    break;
                default:
                    break;
            }
        }
        return blackScore;
    }
    private int updateWhiteScore(Piece piece) {
        if (piece.getColor().equals("black")) {
            switch (piece.getClass().getSimpleName()) {
                case "Pawn":
                    whiteScore += 1;
                    break;
                case "Bishop":
                    whiteScore += 3;
                    break;
                case "Knight":
                    whiteScore += 3;
                    break;
                case "Rook":
                    whiteScore += 5;
                    break;
                case "Queen":
                    whiteScore += 9;
                    break;
                default:
                    break;
            }
        }
        return whiteScore;
    }



    private void redrawPieces() {
        for (Piece piece : pieces) {
            if (piece != null) {
                piece.drawPiece(squares);
            }
        }
    }
    public void removePiece(int row, int col) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] != null && pieces[i].getRow() == row && pieces[i].getCol() == col) {
                pieces[i] = null;
            }
        }
    }

}



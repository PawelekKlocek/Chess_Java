import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

class Piece {
    private String name;
    private int positionX;
    private int positionY;
    private String color;

    public Piece(String name, int positionX, int positionY, String color) {
        this.name = name;
        this.positionY = positionY;
        this.positionX = positionX;
        this.color = color;
    }

    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}

class King extends Piece {
    private ImageIcon piece_img;


    public King(int positionX, int positionY, String color) {
        super("Król", positionX, positionY, color);
        if (color.equals("white")) {
            this.piece_img = new ImageIcon("images/white_king.png");
        }
        else {
            this.piece_img = new ImageIcon("images/black_king.png");
        }
    }
    public ImageIcon getPiece_img(){
        return piece_img;
    }
}
public class ChessBoard {

    private JFrame frame;
    public ChessBoard() {
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(8, 8));
        JPanel board_panel = new JPanel(new GridLayout(8,8));
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);



        initializeBoard(blackBorder, board_panel);

        frame.setSize(800, 800);
        centerFrameOnScreen();
        frame.setVisible(true);
    }

    private void initializeBoard(Border blackBorder,JPanel board_panel) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                if ((row + col) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }
                button.setBorder(blackBorder);
                frame.add(button);
            }

        }
    }


    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChessBoard();
        });
    }
}
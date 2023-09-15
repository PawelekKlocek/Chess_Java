import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(8, 8));

        //Czarna obwodka
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);


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

        // Rozmiar
        frame.setSize(800, 800);
        // Pobranie rozmiaru ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Pobranie rozmiaru ramki
        Dimension frameSize = frame.getSize();
        // Ustawienie ramki na srodku ekranu
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        // Ustawienie nowej pozycji ramki
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}


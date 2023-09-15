import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Gra gra=new Gra();
        JFrame frame = new JFrame("Chess");

        frame.setSize(900, 750);
        // Pobranie rozmiaru ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Pobranie rozmiaru ramki
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x,y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(gra);



    }
}
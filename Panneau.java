import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        //x1, y1, x2, y2
        g.drawLine(0, 200, this.getWidth(), this.getHeight());
        g.drawLine(0, this.getHeight(), this.getWidth(), 220);
    }
}
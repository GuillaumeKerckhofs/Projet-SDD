package Interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import code.*;



public class MyPanel extends JPanel {
    public void paintComponent(Graphics g) {
        ArrayList a = Map.getSegmentList();


        //x1, y1, x2, y2
        for (int i = 0; i < a.size(); i++) {     // pas oublier de suppri si inutile apres
            //for (int j =0;j<3;j++){
            Segment segment = (Segment) a.get(i);
            g.drawLine((int) (segment.getUpper_point().getX()), (int) (segment.getUpper_point().getY()), (int) (segment.getLower_point().getX()), (int) (segment.getLower_point().getY()));
        }
    }
}

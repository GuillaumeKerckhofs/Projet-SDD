import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        ArrayList truc =Test.getlist();
        //x1, y1, x2, y2
        for (int i =0;i<truc.size();i++){
            for (int j =0;j<3;j++){
                ArrayList a= Test.getlist();
                ArrayList<Float>seg2 = (ArrayList<Float>) a.get(i);
                Float yep=(Float)seg2.get(j);

                g.drawLine(100, 200, 400, 600);
                g.drawLine(0, this.getHeight(), this.getWidth(), 220);
            }
        }

    }
}
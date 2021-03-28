package Interface;

import javax.swing.*;
import java.awt.*;

import static code.TestGraphique.getMp;

public class MyWindow extends JFrame {


    public MyWindow(){
        super("Tuturu paradox");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
    }
}

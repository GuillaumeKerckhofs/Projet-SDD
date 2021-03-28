package code;

import Interface.MyMenuBarre;
import Interface.MyPanel;
import Interface.MyWindow;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class TestGraphique {


    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Map map =new Map();

        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);
        myWindow.setSize(600,400);
        myWindow.setContentPane(new MyPanel());
        new MyMenuBarre(myWindow);








    }
}

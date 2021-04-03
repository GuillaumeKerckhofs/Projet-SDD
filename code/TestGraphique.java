package code;

import Interface.MyMenuBarre;
import Interface.MyPanel;
import Interface.MyWindow;


import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class TestGraphique {

    static MyPanel mp=new MyPanel();
    static Map map =new Map();

    public static void main(String[] args) throws UnsupportedLookAndFeelException {


        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);
        myWindow.setSize(800,600);
        myWindow.setContentPane(mp);
        new MyMenuBarre(myWindow);

    }

    public static MyPanel getMp() {
        return mp;
    }

    public static Map getMap() {
        return map;
    }
}

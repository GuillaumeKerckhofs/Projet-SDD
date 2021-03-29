package code;

import Interface.MyMenuBarre;
import Interface.MyPanel;
import Interface.MyWindow;
import code.Map;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class TestGraphique {

    static MyPanel mp=new MyPanel();

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Map map =new Map();
        map.Save("test");

        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);
        myWindow.setSize(800,600);
        myWindow.setContentPane(mp);
        new MyMenuBarre(myWindow,mp);



    }

    public static MyPanel getMp() {
        return mp;
    }
}

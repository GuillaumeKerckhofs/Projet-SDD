package Interface;

import code.Algo;
import code.Map;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;



public class MyWindow extends JFrame {

    static MainPanel mp=new MainPanel();
    static Map map =new Map();
    static JScrollPane pane1 = new JScrollPane(mp);

    public MyWindow(){

        super("Tuturu paradox");
        int width = 860;
        int height = 700;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);
        this.setResizable(false);
        MyMenuBarre menu =new MyMenuBarre(this);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.add(createtoolBar(),BorderLayout.EAST);



        pane1.setPreferredSize(new Dimension(width-110,0));

        contentPane.add(pane1,BorderLayout.WEST);



    }

    private JToolBar createtoolBar(){
        JToolBar toolBar = new JToolBar();

        JButton addSegment = new JButton("add segment");


        toolBar.add(addSegment);

        return toolBar;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {


        Algo.FindIntersections(Map.getSegmentList());
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MyWindow myWindow = new MyWindow();


    }



    public static MainPanel getMp() {
        return mp;
    }

    public static Map getMap() {
        return map;
    }

    public static JScrollPane getPane1() {
        return pane1;
    }

    public static void setPane1(JScrollPane pane1) {
        MyWindow.pane1 = pane1;
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
    }
}

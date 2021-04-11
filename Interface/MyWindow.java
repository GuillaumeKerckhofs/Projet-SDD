package Interface;

import code.Algo;
import code.Map;
import code.Segment;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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

        addSegment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String result = (String)JOptionPane.showInputDialog(
                        toolBar,
                        "Type a new segment : x1 y1 x2 y2",
                        "new segment",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "0.00 0.00 1.00 1.00"
                );
                if(result != null && result.length() > 0){
                    try {
                        String point[] = result.split(" ");
                        ArrayList<Float> seg = new ArrayList<Float>();
                        for (int i = 0; i < 4; i++)
                            seg.add(Float.parseFloat(point[i]));
                        //System.out.println(seg);
                        Segment segment = new Segment((Float) seg.get(0), (Float) seg.get(1), (Float) seg.get(2), (Float) seg.get(3));
                        Map.addSegment(segment);
                        Algo.FindIntersections(Map.getSegmentList());
                        mp.repaint();
                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                    }

                }else {

                }
            }
        });


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

    public static void setMap(Map map) {
        MyWindow.map = map;
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

package code.Interface;

import code.logique.Algo;
import code.logique.Map;
import code.logique.Point;
import code.logique.Segment;

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

        super("Intersection finder");
        int width = 860;
        int height = 700;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);
        this.setResizable(false);
        MyMenuBarre menu =new MyMenuBarre(this);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.add(createtoolBar(),BorderLayout.NORTH);



        pane1.setPreferredSize(new Dimension(width,0));

        contentPane.add(pane1,BorderLayout.WEST);



    }

    private JToolBar createtoolBar(){
        JToolBar toolBar = new JToolBar();

        JButton newMap = new JButton("new map");

        newMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setSavePath(null);
                map.setSegmentList(new ArrayList<Segment>());
                Algo.setPrintQ(new ArrayList<Point>());
                Algo.setIntersection(new ArrayList<Point>());
                newSegment(toolBar);
                Algo.findIntersections(Map.getSegmentList());
                mp.repaint();
            }
        });

        toolBar.add(newMap);


        JButton addSegment = new JButton("add segment");

        addSegment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSegment(toolBar);
                Algo.findIntersections(Map.getSegmentList());
                mp.repaint();

            }
        });

        toolBar.add(addSegment);

        JButton suppSegment = new JButton("remove segment");

        suppSegment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprSegment(toolBar);
                //Algo.FindIntersections(Map.getSegmentList());
                mp.repaint();

            }
        });

        toolBar.add(suppSegment);

        JButton prevSweepline = new JButton("previous step");
        prevSweepline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=mp.getSweeplinecount()-1;
                mp.setSweeplinecount(i);
                mp.repaint();
            }
        });
        prevSweepline.setAlignmentY(getWidth()-100);

        toolBar.add(prevSweepline);


        JButton nextSweepline = new JButton("next step");
        nextSweepline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=mp.getSweeplinecount()+1;
                mp.setSweeplinecount(i);
                mp.repaint();
            }
        });

        nextSweepline.setAlignmentY(getWidth()-100);

        toolBar.add(nextSweepline);

        return toolBar;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {


        Algo.findIntersections(Map.getSegmentList());
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MyWindow myWindow = new MyWindow();


    }


    public void supprSegment(JToolBar toolBar){
        String result = (String)JOptionPane.showInputDialog(
                toolBar,
                "Type a segment : x1 y1 x2 y2",
                "remove segment",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0.00 0.00 1.00 1.00"
        );
        if(result != null && result.length() > 0) {
            try {
                String point[] = result.split(" ");
                ArrayList<Float> seg = new ArrayList<Float>();
                for (int i = 0; i < 4; i++)
                    try {
                        seg.add(Float.parseFloat(point[i]));
                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                    }
                //System.out.println(seg);
                Segment segment = new Segment((float) seg.get(0), (float) seg.get(1), (float) seg.get(2), (float) seg.get(3));
                Map.supprSegment(segment);

            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    public void newSegment(JToolBar toolBar){
        String result = (String)JOptionPane.showInputDialog(
                toolBar,
                "Type a new segment : x1 y1 x2 y2",
                "new segment",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0.00 0.00 1.00 1.00"
        );
        if(result != null && result.length() > 0) {
            try {
                String point[] = result.split(" ");
                ArrayList<Float> seg = new ArrayList<Float>();
                for (int i = 0; i < 4; i++)
                    try {
                        seg.add(Float.parseFloat(point[i]));
                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                    }
                //System.out.println(seg);
                Segment segment = new Segment((float) seg.get(0), (float) seg.get(1), (float) seg.get(2), (float) seg.get(3));
                Map.addSegment(segment);

            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }

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

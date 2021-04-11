package Interface;


import code.Map;
import code.Segment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Interface.MyWindow.*;



public class MyMenuBarre extends JPanel implements ActionListener {

    JMenu menu, secondMenu,thirdMenu;
    JMenuItem open, save,fastSave, i3,exit,addSegment;

    public MyMenuBarre(JFrame fenetre){

        final JLabel label = new JLabel();
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("File");
        secondMenu=new JMenu("modif");
        thirdMenu=new JMenu("help");

        open =new JMenuItem("Open");
        fastSave=new JMenuItem("Fast save");
        save=new JMenuItem("Save");
        i3=new JMenuItem("modify");
        exit =new JMenuItem("Quit");
        addSegment =new JMenuItem("Add a segment");

        open.addActionListener(this);
        save.addActionListener(this);
        i3.addActionListener(this);
        exit.addActionListener(this);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getMap().chooseSave();
            }
        });
        mb.add(save);

        fastSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getMap().getSavePath()==null){
                    getMap().chooseSave();
                }
                else{getMap().Save();}

            }
        });
        mb.add(save);


        addSegment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String result = (String)JOptionPane.showInputDialog(
                        fenetre,
                        "Type a new segment : x1 y1 x2 y2",
                        "new segment",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "0.00 0.00 1.00 1.00"
                );
                if(result != null && result.length() > 0){
                    String point[]=result.split(" ");
                    ArrayList<Float> seg = new ArrayList<Float>();
                    for (int i=0;i<4;i++)
                        seg.add(Float.parseFloat(point[i]));
                    //System.out.println(seg);
                    Segment segment=new Segment((Float)seg.get(0),(Float)seg.get(1),(Float)seg.get(2),(Float)seg.get(3));
                    Map.addSegment(segment);
                }else {
                    label.setText("None selected");
                }
            }
        });
        mb.add(addSegment);


        menu.add(open);menu.add(fastSave); menu.add(save); menu.add(i3);

        secondMenu.add(addSegment);

        thirdMenu.add(exit);
        mb.add(menu);
        mb.add(secondMenu);
        mb.add(thirdMenu);
        fenetre.setJMenuBar(mb);
        fenetre.setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source== open){System.out.println(1);}
        else if (source == exit){System.exit(0);}
    }

}


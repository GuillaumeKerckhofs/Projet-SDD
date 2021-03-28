package Interface;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MyMenuBarre extends MyPanel implements ActionListener {

    JMenu menu, secondMenu,thirdMenu;
    JMenuItem i1, i2, i3, i4, i5,i6;

    public MyMenuBarre(JFrame fenetre,MyPanel mp){

        JMenuBar mb=new JMenuBar();
        menu=new JMenu("File");
        secondMenu=new JMenu("Zoom");
        thirdMenu=new JMenu("help");

        i1=new JMenuItem("Open");
        i2=new JMenuItem("Save");
        i3=new JMenuItem("modify");
        i4=new JMenuItem("zoom in");
        i5=new JMenuItem("zoom out");
        i6=new JMenuItem("Quit");

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i6.addActionListener(this);


        i4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mp.setZoom(mp.getZoomFactor()*1.5);
            }
        });
        mb.add(i4);

        i5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mp.setZoom(mp.getZoomFactor()*0.5);
            }
        });
        mb.add(i5);





        menu.add(i1); menu.add(i2); menu.add(i3);

        secondMenu.add(i4); secondMenu.add(i5);

        thirdMenu.add(i6);
        mb.add(menu);
        mb.add(secondMenu);
        mb.add(thirdMenu);
        fenetre.setJMenuBar(mb);
        fenetre.setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source==i1){System.out.println(1);}
        else if (source==i2){System.out.println(2);}
        else if (source==i4){
            this.repaint();}
        else if (source==i5){System.out.println(2);}
        else if (source ==i6){System.exit(0);}
    }

}


package Interface;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static code.TestGraphique.*;


public class MyMenuBarre extends MyPanel implements ActionListener {

    JMenu menu, secondMenu,thirdMenu;
    JMenuItem open, save,fastSave, i3, zoomIn, zoomOut, exit;

    public MyMenuBarre(JFrame fenetre){

        JMenuBar mb=new JMenuBar();
        menu=new JMenu("File");
        secondMenu=new JMenu("Zoom");
        thirdMenu=new JMenu("help");

        open =new JMenuItem("Open");
        fastSave=new JMenuItem("Fast save");
        save=new JMenuItem("Save");
        i3=new JMenuItem("modify");
        zoomIn=new JMenuItem("zoom in");
        zoomOut=new JMenuItem("zoom out");
        exit =new JMenuItem("Quit");

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

        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getMp().setZoom(getMp().getZoomFactor()*1.5);
            }
        });
        mb.add(zoomIn);

        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getMp().setZoom(getMp().getZoomFactor()*0.5);
            }
        });
        mb.add(zoomOut);


        menu.add(open);menu.add(fastSave); menu.add(save); menu.add(i3);

        secondMenu.add(zoomIn); secondMenu.add(zoomOut);

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
        else if (source== zoomIn){ this.repaint();}
        else if (source==zoomOut){System.out.println(2);}
        else if (source == exit){System.exit(0);}
    }

}


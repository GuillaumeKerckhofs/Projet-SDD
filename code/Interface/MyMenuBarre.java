package code.Interface;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static code.Interface.MyWindow.*;



public class MyMenuBarre extends JPanel implements ActionListener {

    JMenu menu,secondMenu;
    JMenuItem open, save,fastSave,exit;

    public MyMenuBarre(JFrame fenetre){

        final JLabel label = new JLabel();
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("File");
        secondMenu=new JMenu("Exit");

        open =new JMenuItem("Open");
        fastSave=new JMenuItem("Fast save");
        save=new JMenuItem("Save");
        exit =new JMenuItem("Quit");

        open.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getMap().chooseSave();
            }
        });
        mb.add(save);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getMap().chooseOpen();
            }
        });
        mb.add(save);


        fastSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getMap().getSavePath()==null){
                    getMap().chooseSave();
                }
                else{getMap().save();}

            }
        });
        mb.add(save);


        menu.add(open);menu.add(fastSave); menu.add(save);


        secondMenu.add(exit);
        mb.add(menu);
        mb.add(secondMenu);
        fenetre.setJMenuBar(mb);
        fenetre.setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source== open){System.out.println(1);}
        else if (source == exit){System.exit(0);}
    }

}


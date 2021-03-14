import javax.swing.*;


public class MenuBar {
        JMenu menu, submenu, secondMenu;
        JMenuItem i1, i2, i3, i4, i5,i6;
        MenuBar(JFrame fenetre){
            JMenuBar mb=new JMenuBar();
            menu=new JMenu("File");
            secondMenu=new JMenu("help");
            submenu=new JMenu("Sub Menu");
            i1=new JMenuItem("Open");
            i2=new JMenuItem("Save");
            i3=new JMenuItem("AYAYA");
            i4=new JMenuItem("Item 4");
            i5=new JMenuItem("Item 5");
            i6=new JMenuItem("Item 6");
            menu.add(i1); menu.add(i2); menu.add(i3);
            submenu.add(i4); submenu.add(i5);
            secondMenu.add(i6);
            menu.add(submenu);
            mb.add(menu);
            mb.add(secondMenu);
            fenetre.setJMenuBar(mb);
            fenetre.setSize(400,400);
            fenetre.setLayout(null);
            fenetre.setVisible(true);
        }
}


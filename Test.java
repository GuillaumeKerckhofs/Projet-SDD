

import javax.swing.JFrame;
import java.util.ArrayList;


public class Test {


    public static void main(String[] args){


        Map map =new Map();
        //ArrayList<Segment> a= map.getSegmentList();

        JFrame fenetre = new JFrame();
        //Définit un titre pour notre fenêtre
        fenetre.setTitle("TUTURU");
        //Définit sa taille : 400 pixels de large et 100 pixels de haut
        fenetre.setSize(600, 500);
        //Nous demandons maintenant à notre objet de se positionner au centre
        fenetre.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Et enfin, la rendre visible
        fenetre.setVisible(true);
        fenetre.setContentPane(new Panneau());
        new MenuBar(fenetre);

        Point p1=new Point(0.5f,1);
        Point p2=new Point(1,0.5f);
        Point p3=new Point(1,1);
        Point p4=new Point(2,2);
        Point p5=new Point(2,2.5f);
        Point p6=new Point(3,1);
        Point p7=new Point(2,1);
        Segment s=new Segment(p1,p7);
        Q_Node node=new Q_Node(p4,s);
        Q_Tree Q=new Q_Tree(node);
        Q.startInsertion(p2,s);
        Q.startInsertion(p3,s);
        Q.startInsertion(p6,s);
        Q.startInsertion(p5,s);
        Q.startInsertion(p1,s);
        Q.startInsertion(p7,s);
        Q.print();
    }


}

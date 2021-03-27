

import javax.swing.JFrame;
import java.util.ArrayList;


public class Test {


    public static void main(String[] args){

        /*

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

         */

        Point p1=new Point(0.5f,1);
        Point p2=new Point(1,0.5f);
        Point p3=new Point(1,1.5f);
        Point p4=new Point(2,2);
        Point p5=new Point(2,2.5f);
        Point p6=new Point(3,3);
        Point p7=new Point(2,2);
        Segment s=new Segment(p1,p7);
        Q_Node node=new Q_Node(p4,s,new Q_Node(),new Q_Node());
        Q_Tree Q=new Q_Tree(node);
        Q.startInsertion(p2,s);
        Q.startInsertion(p3,s);
        Q.startInsertion(p6,s);
        Q.startInsertion(p5,s);
        Q.startInsertion(p1,s);
        Q.startInsertion(p7,s);
        Q.print();

        System.out.println("");
        System.out.println("");
        Q.removeNextEvent();
        System.out.println(Q.getLastRemoved().getPoint());
        Q.removeNextEvent();
        System.out.println(Q.getLastRemoved().getPoint());
        Q.removeNextEvent();
        System.out.println(Q.getLastRemoved().getPoint());
        Q.removeNextEvent();
        System.out.println(Q.getLastRemoved().getPoint());
        Q.removeNextEvent();
        System.out.println(Q.getLastRemoved().getPoint());
        System.out.println("");
        System.out.println("");
        Q.print();

        System.out.println("");
        System.out.println("______________________________");
        System.out.println("");


        Point a1=new Point(5,5);
        Point a2=new Point(10,7);
        Point a3=new Point(1,1);
        Point a4=new Point(7,6);
        Point a5=new Point(9,2);
        Point a6=new Point(4,2);
        Point a7=new Point(2,2);

        Segment s1=new Segment(a1,a3);
        Segment s4=new Segment(a3,a2);
        Segment s2=new Segment(a4,a5);
        Segment s3=new Segment(a6,a5);
        T_Tree T=new T_Tree();
        T.insert(s1);
        T.insert(s2);
        T.insert(s3);
        T.insert(s4);




        System.out.println("S1 ="+s4);
        System.out.println("S2 ="+s3);
        System.out.println("S3 ="+s2);
        System.out.println("S4 ="+s1);

        System.out.println("*****************");
        System.out.println(s2.tSmallerThan(s3));


        


        T.print();


    }


}

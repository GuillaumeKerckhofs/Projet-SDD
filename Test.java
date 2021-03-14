

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
    }


}

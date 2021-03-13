

import javax.swing.JFrame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test {


    static ArrayList<ArrayList> listSeg = new ArrayList<ArrayList>();
    public static void truc(){
        // Le fichier d'entrée
        FileInputStream file;


        {
            try {
                file = new FileInputStream("cartes/fichier1.txt");
                Scanner scanner = new Scanner(file);
                // liste des points

                //renvoie true s'il y a une autre ligne à lire
                while(scanner.hasNextLine())
                {
                    String a=scanner.nextLine();
                    System.out.println(a);
                    String str[]=a.split(" ");
                    ArrayList<Float> seg = new ArrayList<Float>();
                    for (int i=0;i<4;i++)
                        seg.add(Float.parseFloat(str[i]));
                    listSeg.add(seg);
                    //System.out.println(listSeg);
                    System.out.println("---");
                }
                scanner.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList getlist() {
        return listSeg;
    }


    public static void main(String[] args){
        //truc();
        //ArrayList a=getlist();
        //ArrayList<Float>seg2 = (ArrayList<Float>) a.get(0);

        //seg2.get(0);
        //System.out.println(seg2);

        Map map =new Map();

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
    }


}

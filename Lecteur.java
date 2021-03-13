import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lecteur {
    /*static ArrayList<List> listSeg = new ArrayList<List>();

    public List truc(){
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
                ArrayList list = getlist();
                list.add(seg);
                setlist(list);
                //System.out.println(listSeg);
                System.out.println("---");
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    System.out.println(listSeg);
    return listSeg;
    }


    public ArrayList getlist() {
        return listSeg;
    }

    public void setlist(ArrayList listSeg) {
        this.listSeg = listSeg;
    } */
}

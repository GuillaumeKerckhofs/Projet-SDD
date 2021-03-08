import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lecteur {
        public static void main(String args[])
        {
            try
            {
                // Le fichier d'entrée
                FileInputStream file = new FileInputStream("cartes/fichier1.txt");
                Scanner scanner = new Scanner(file);
                List<List> listSeg = new ArrayList<List>();   // liste des points

                //renvoie true s'il y a une autre ligne à lire
                while(scanner.hasNextLine())
                {
                    String a=scanner.nextLine();
                    System.out.println(a);
                    String str[]=a.split(" ");
                    List<Float> seg = new ArrayList<Float>();
                    for (int i=0;i<4;i++)
                        seg.add(Float.parseFloat(str[i]));
                    listSeg.add(seg);
                    System.out.println(listSeg);
                    System.out.println("---");
                }
                scanner.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

}

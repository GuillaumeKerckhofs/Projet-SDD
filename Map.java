import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
//import Segment;

public class Map {
    //private Segment segmentList[];

    public Map(/*String map*/){
        loadPoint("cartes/fichier1.txt");
        //showMap();
    }
    private void loadPoint(String map) throws IOException{
        BufferedReader lecteurAvecBuffer = null;
        String line;

        try{
	        lecteurAvecBuffer = new BufferedReader(new FileReader(map));
        }
        
        catch(FileNotFoundException exc){
	        System.out.println("Erreur d'ouverture");
        }
        
        while ((line = lecteurAvecBuffer.readLine()) != null){
            String point[]=line.split(" ");
            System.out.println(point);
            /*Segment segment=new Segment(point);
            segmentList.append(segment);*/
        }
        
        lecteurAvecBuffer.close();
    }
}

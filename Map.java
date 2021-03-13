import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Map {
    private ArrayList<Segment> segmentList = new ArrayList<Segment>();

    public Map(/*String map*/){
        try {
            loadPoint("cartes/fichier1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            ArrayList<Float> seg = new ArrayList<Float>();
            for (int i=0;i<4;i++)
                seg.add(Float.parseFloat(point[i]));
            System.out.println(seg);
            Segment segment=new Segment((Float)seg.get(0),(Float)seg.get(1),(Float)seg.get(2),(Float)seg.get(3));
            segmentList.add(segment);
            System.out.println(segmentList);
        }
        
        lecteurAvecBuffer.close();
    }
}

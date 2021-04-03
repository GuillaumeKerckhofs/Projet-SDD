package code;

import java.io.*;
import java.util.ArrayList;


public class Map {
    private static ArrayList<Segment> segmentList = new ArrayList<Segment>();

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
        }
        
        lecteurAvecBuffer.close();
    }

    public void addSegment (Segment segment){ segmentList.add(segment);}

    public void Save (String saveName){
        File file=new File("cartes"+File.separator+saveName);
        FileOutputStream fos;
        try
        {
            fos=new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String line="";

            System.out.println("");
            System.out.println("save?");
            System.out.println("");
            for (int i=0;i<this.segmentList.size();i++){
                Segment tmp = segmentList.get(i);
                line = tmp.stringSegment();
                System.out.println(line);
                bw.write(line);
                bw.newLine();
                line = "";
            }
            bw.close();
            System.out.println("");
            System.out.println("save!");
            System.out.println("");

        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde");
        }
    }

    public static ArrayList<Segment> getSegmentList() {
        return segmentList;
    }

}

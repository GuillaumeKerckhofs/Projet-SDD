package code.logique;

import code.Interface.MyWindow;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import static code.Interface.MyWindow.getMap;


public class Map {
    private static ArrayList<Segment> segmentList = new ArrayList<Segment>();
    private static String savePath;
    private static String openPath;

    /**
    crée une map à partir d'un fichier qui est de base le fichier1
     */
    public Map(){
        try {
            if (openPath==null){
                chooseOpen();}
            else{loadPoint(openPath);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    supprime un segment de la map
     */
    public static void supprSegment(Segment segment) {
        ArrayList<Segment> list = getSegmentList();
        System.out.println(list);
        if (!list.isEmpty()){
            for (Segment i:list){
                if (i.isEquals(segment)){
                    list.remove(i);
                    break;
                }
            }
            System.out.println(list);
        }
    }

    /**
    ouvre le fichier et charge les segments avant des les mettre dans la segmentList
     */
    private void loadPoint(String map) throws IOException{
        BufferedReader lecteurAvecBuffer = null;
        String line;

        try{
	        lecteurAvecBuffer = new BufferedReader(new FileReader(map));
        }
        
        catch(FileNotFoundException exc){
	        System.out.println("Erreur d'ouverture");
        }
        segmentList = new ArrayList<Segment>();
        Algo.setIntersection(new ArrayList<Point>());
        while ((line = lecteurAvecBuffer.readLine()) != null){
            String point[]=line.split(" ");
            ArrayList<Float> seg = new ArrayList<Float>();
            for (int i=0;i<4;i++)
                seg.add(Float.parseFloat(point[i]));
            Segment segment=new Segment((Float)seg.get(0),(Float)seg.get(1),(Float)seg.get(2),(Float)seg.get(3));
            segmentList.add(segment);
        }
        
        lecteurAvecBuffer.close();
    }

    public static void addSegment(Segment segment){ segmentList.add(segment);}

    /**
    sauvegarde de la map actuelle dans le fichier qu'indique le savePath
     */
    public void Save (){
        File file=new File(savePath);
        FileOutputStream fos;
        try
        {
            fos=new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String line="";

            for (int i=0;i<this.segmentList.size();i++){
                Segment tmp = segmentList.get(i);
                line = tmp.stringSegment();
                System.out.println(line);
                bw.write(line);
                bw.newLine();
                line = "";
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde");
        }
    }

    /**
    ouvre un nouveau fichier et charge
     */
    public void Open(){

        try
        {
            loadPoint(openPath);
            Algo.FindIntersections(Map.getSegmentList());
            MyWindow.getMp().repaint();

        } catch (IOException e) {
            System.out.println("Erreur d'ouverture'");
        }
    }


    /**
    permet de choisir où sauvegarder
     */
    public void chooseSave() {
        final JFrame fenetre = new JFrame();
        final JFileChooser fc = new JFileChooser();
        int val_retour = fc.showSaveDialog(fenetre);

        if (val_retour == JFileChooser.APPROVE_OPTION) {
            File fichier = fc.getSelectedFile();
            savePath=fichier.getAbsolutePath();
            Save();
        } else {
        }
    }

    /**
    permet de chosir quel map charger
     */
    public void chooseOpen() {
        final JFrame fenetre = new JFrame();
        final JFileChooser fc = new JFileChooser();
        int val_retour = fc.showSaveDialog(fenetre);

        if (val_retour == JFileChooser.APPROVE_OPTION) {
            File fichier = fc.getSelectedFile();
            openPath=fichier.getAbsolutePath();
            Open();
        } else {
        }
    }

    public static String getSavePath() {
        return savePath;
    }

    public static void setSavePath(String savePath) {
        Map.savePath = savePath;
    }

    public static ArrayList<Segment> getSegmentList() {
        return segmentList;
    }

    public static void setSegmentList(ArrayList<Segment> segmentList) {
        Map.segmentList = segmentList;
    }
}

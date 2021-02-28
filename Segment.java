package Projet;

public class Segment{
    private int x1;
    private int y1;

    private int x2;
    private int y2;
    public Segment(String points[]){
        this.x1=Integer.parseInt(points[0]);
        this.y1=Integer.parseInt(points[1]);

        this.x2=Integer.parseInt(points[2]);
        this.y2=Integer.parseInt(points[3]);
    }
}

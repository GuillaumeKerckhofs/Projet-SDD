package code.logique;

public class Point {
    private float x;
    private float y;

    private boolean upper_point;

    /**
    constructeur d'un point
    @param x du point
    @param y du point
    @param upper_point true si upper_point d'un segment,faulse sinon
     */
    public Point(float x,float y,boolean upper_point){
        this.x=x;
        this.y=y;
        this.upper_point=upper_point;
    }

    public Point(float x,float y){
        this(x,y,false);
    }

    public boolean smallerThan(Point point2){
        float x2=point2.getX();
        float y2=point2.getY();
        if(this.y>y2 || (this.y==y2 && this.x<x2)){
            return true;
        }
        else
            return false;
    }

    public boolean isEqualTo(Point point2){
        return (this.x==point2.getX() && this.y==point2.getY());
    }

    public void setUpper_point(boolean upper_point) {
        this.upper_point = upper_point;
    }

    public boolean isUpper_point() {
        return upper_point;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return ("("+this.x+","+this.y+")");
    }

    public String stringPoint(){ return (this.x+" "+this.y);}
}
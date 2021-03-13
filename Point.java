public class Point {
    private int x;
    private int y;

    private boolean upper_point;

    public Point(int x,int y,boolean upper_point=false){
        this.x=x;
        this.y=y;
        this.upper_point=upper_point;
    }

    public boolean smallerThan(Point point2){
        int x2=point2.getX();
        int y2=point2.getY();
        if(this.y>y2 || (this.y==y2 && this.x<x2)){
            return true;
        }
        else
            return false;
    }

    public void setUpper_point(boolean upper_point) {
        this.upper_point = upper_point;
    }

    public boolean isUpper_point() {
        return upper_point;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
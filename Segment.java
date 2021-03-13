public class Segment{
    private Point upper_point;
    private Point lower_point;

    public Segment(Point upper_point,Point lower_point){
        this.upper_point=upper_point;
        this.lower_point=lower_point;
    }

    public Segment(float x1,float y1,float x2,float y2){
        if(y1>y2 || (y1==y2 && x1<x2)){
            this.upper_point=new Point(x1,y1,true);
            this.lower_point=new Point (x2,y2,false);  // j'ai rajouté ",false" car erreur avant
        }
        else{
            this.upper_point=new Point(x2,y2,true);
            this.lower_point=new Point (x1,y1,false);
        }
    }

    public Point getUpper_point(){
        return this.upper_point;
    }

    public Point getLower_point() {
        return lower_point;
    }

    public void setLower_point(Point lower_point) {
        this.lower_point = lower_point;
    }

    public void setUpper_point(Point upper_point) {
        this.upper_point = upper_point;
    }
}

package code;

import java.util.ArrayList;
import static java.lang.Math.max;


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


    public boolean tSmallerThan (Segment segment){                    //à faire

        float p1 = getUpper_point().getX();
        float p2 = segment.getUpper_point().getX();
        if(p1<p2){
            return true;
        }
        else return false;
    }


    public int compareTo(Segment segment,float xHor,float y){

        if (!segment.isHorizontal()&&!this.isHorizontal()){
            float x1 =this.getCurrentPoint(y);
            float x2 =segment.getCurrentPoint(y);

            if (this.isEquals(segment)){
                return 0
            }
            else if (x1>x2)
                return 1;
            else if (x1<x2)
                return -1;
            else{
                float ymax = max(this.lower_point.getY(),segment.getLower_point().getY());
                x1 =this.getCurrentPoint(ymax);
                x2 =segment.getCurrentPoint(ymax);

                if (x1>x2)
                    return 1;
                else if (x1<x2)
                    return -1;

            }
        }
        else {
            if (this.isEquals(segment)){
                return 0;
            }
            else if(this.isHorizontal()){
                float x1=segment.getCurrentPoint(y);
                if(x1<=xHor)
                    return -1;
                else
                    return 1;
            }
            else if(segment.isHorizontal()){
                float x1=this.getCurrentPoint(y);
                if(xHor<x1)
                    return -1;
                else
                    return 1;

            }
        }

        //this.compareto(segment) this> 1 =0 <-1

        return 2;
    }

    public boolean isEquals (Segment segment){
        if (segment.getUpper_point().isEqualTo(this.upper_point) && segment.getLower_point().isEqualTo(this.lower_point)){
            return true;
        }
        else
            return false;
    }

    public boolean contain (Point p){
        float m1 = (getUpper_point().getY()- getLower_point().getY())/((getUpper_point().getX())- getLower_point().getX());
        float m2 = (getUpper_point().getY()- p.getY())/((getUpper_point().getX())- p.getX());
        //System.out.println("m1= "+m1);
        //System.out.println("m2= "+m2);
        if ((getUpper_point().getX())- p.getX()==0&&(getUpper_point().getY()- p.getY())==0){
            //System.out.println("true 1 <-----");
            return true;}
        else if ((getUpper_point().getX())- p.getX()==0&&!((getUpper_point().getY()- p.getY())==0)){
            //System.out.println("false 1");
            return false;}
        else if (m1==m2&&!(p.getY()< getLower_point().getY())&&!(p.getY()> getUpper_point().getY())){
            //System.out.println("true 2 <-----");
            return true;}
        //System.out.println("false 2");
        return false;
    }

    public float getCurrentPoint(float y) {   // à faire
        float x=upper_point.getX()+((y-upper_point.getY())*(lower_point.getX()- upper_point.getX()))/(lower_point.getY()- upper_point.getY());
        return x;
    }

    public boolean isHorizontal(){
        return (getLower_point().getY()==getUpper_point().getY());
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

    public String toString(){
        return ("("+this.upper_point+","+this.lower_point+")");
    }

    public String stringSegment(){ return (this.upper_point.stringPoint()+" "+this.lower_point.stringPoint());}
}

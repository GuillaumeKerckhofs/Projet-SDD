package code;

import java.util.ArrayList;
import static java.lang.Math.max;
import static java.lang.Math.abs;


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

        //System.out.println(!segment.isHorizontal()&&!this.isHorizontal());
        if (!segment.isHorizontal()&&!this.isHorizontal()){
            float x1 =this.getCurrentPoint(y);
            float x2 =segment.getCurrentPoint(y);

            //System.out.println("x1= "+x1);
            //System.out.println("x2= "+x2);

            if (this.isEquals(segment)){
                //System.out.println(0);
                return 0;
            }
            else if(!(Math.abs(x1-x2)<1e-4)) {

                if (x1 > x2){
                    //System.out.println(1);
                    return 1;}
                else if (x1 < x2){
                    //System.out.println(-1);
                    return -1;}
            }
            else{

                float ymax = max(this.lower_point.getY(),segment.getLower_point().getY());
                x1 =this.getCurrentPoint(ymax);
                x2 =segment.getCurrentPoint(ymax);

                System.out.println("verifier");
                System.out.println(ymax);
                System.out.println(x1);
                System.out.println(x2);
                System.out.println("verifier");

                if (x1>x2)
                    return 1;
                else if (x1<x2)
                    return -1;
                else if(x1==x2){
                    x1=this.getUpper_point().getX();
                    x2=segment.getUpper_point().getX();
                    if (x1>x2)
                        return 1;
                    else if (x1<x2)
                        return -1;
                }
            }
        }
        else {
            if (this.isEquals(segment)){

                return 0;
            }
            else if(this.isHorizontal()){
                float x1=segment.getCurrentPoint(y);
                if(x1<=xHor)
                    return 1;
                else
                    return -1;
            }
            else if(segment.isHorizontal()){
                float x1=this.getCurrentPoint(y);
                if(xHor<x1)
                    return 1;
                else
                    return -1;

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
        if (!isHorizontal()){
            float x=this.getCurrentPoint(p.getY());
            if ((Math.abs(x-p.getX())<1e-2)){
                return true;
            }
        }
        else{
            if (p.getY()==this.getUpper_point().getY()&&upper_point.getX()<=p.getX()&&p.getX()<=lower_point.getX())
            return true;
        }

    return false;
    }

    public float getCurrentPoint(float y) {   // à faire
        float x1=upper_point.getX();
        float x2=lower_point.getX();
        float y1=upper_point.getY();
        float y2=lower_point.getY();


        double x=x1+((y-y1)*(x2-x1))/(y2-y1);
        x= Math.round(x*100.0)/100.0;
        return ((float) x);

/*
        System.out.println("");
        System.out.println("");
        System.out.println("currentpoint "+x);
        System.out.println("X1 "+upper_point.getX());
        System.out.println("Y1 "+upper_point.getY());
        System.out.println("X2 "+lower_point.getX());
        System.out.println("Y2 "+lower_point.getY());
        System.out.println("current Y "+y);
        System.out.println("");
        System.out.println("");
*/
    }

    public boolean isHorizontal(){
        return (getLower_point().getY()==getUpper_point().getY());
    }

    public Point isIntersectBy(Segment Sr){
        float x1=upper_point.getX();
        float y1=upper_point.getY();

        float x2=lower_point.getX();
        float y2=lower_point.getY();

        float x3=Sr.getUpper_point().getX();
        float y3=Sr.getUpper_point().getY();

        float x4=Sr.getLower_point().getX();
        float y4=Sr.getLower_point().getY();


        //System.out.println("test==>");

        float a1;
        float a2;
        float b1;
        float b2;



        Point p = null;
        if (x2!=x1&&x4!=x3) {
            a1 = (y2 - y1) / (x2 - x1);
            b1 = y1 - a1 * x1;
            a2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - a2 * x3;

            float x = (b1 - b2) / (a2 - a1);
            float y = a1 * x + b1;


/*
            x= Math.round(x*100.0)/100.0;
            y= Math.round(y*100.0)/100.0;
*/
            System.out.println("========================== test intersect =========================");
            System.out.println(x);
            System.out.println(y);
            System.out.println(x1+" ;"+y1);
            System.out.println(x2+" ;"+y2);
            System.out.println(x3+" ;"+y3);
            System.out.println(x4+" ;"+y4);
            System.out.println("========================== fin test intersect =========================");


            if (x1 > x2) {
                if (x > x1 || x < x2) {
                    System.out.println("1");
                    return p;
                }
            } else if (x2 > x1) {
                if (x < x1 || x > x2) {
                    System.out.println("2");
                    return p;

                }
            }

            if (x3 > x4) {
                if (x > x3 || x < x4) {
                    System.out.println("3");
                    return p;
                }
            }
            else if (x4 > x3) {
                if (x < x3 || x > x4) {
                    System.out.println("4");
                    return p;
                }
            }

            if (y1 > y2) {
                if (y > y1 || y < y2) {
                    System.out.println("5");
                    return p;
                }
            }
            else if (y2 > y1) {
                if (y < y1 || y > y2) {
                    System.out.println("6");
                    return p;
                }
            }

            if (y3 > y4) {
                if (y > y3 || y < y4) {
                    System.out.println("6");
                    return p;
                }
            }
            else if (y4 > y3) {
                if (y < y3 || y > y4) {
                    System.out.println("7");
                    return p;
                }
            }

            //System.out.println("je rentre");
            p = new Point((float) x,(float) y);
            }

        if (x1==x2 && y1!=y2) {       // verticale
            a2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - a2 * x3;

            float y=a2*x1+b2;
            if(y<y1 && y>y2) {
                p= new Point(x1,y);
                return p;
            }

        }
        if (x3==x4 && y3!=y4) {       // verticale
            a1 = (y2 - y1) / (x2 - x1);
            b1 = y2 - a1 * x1;

            float y=a1*x3+b1;
            if(y<y3 && y>y4) {
                p= new Point(x3,y);
                return p;
            }
        }
        if (x1!=x2 && y1==y2) {       //horizontale this
            float x = Sr.getCurrentPoint(y1);
            if (x>x1 && x<x2){
                p= new Point(x,y1);
                return p;
            }
        }

        if (x3!=x4 && y3==y4) {       //horizontale Sl
            float x = this.getCurrentPoint(y3);
            if (x>x3 && x<x4){
                p= new Point(x,y3);
                return p;
            }
        }

        return (p);

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

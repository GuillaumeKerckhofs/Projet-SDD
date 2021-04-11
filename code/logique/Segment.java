package code.logique;

import static java.lang.Math.max;
import static java.lang.Math.abs;


public class Segment{
    private Point upper_point;
    private Point lower_point;

    /**
    constructeur d'un segment
    @param upper_point point du dessus du segment
    @param lower_point point du dessous du segment
     */
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

    public boolean tSmallerThan (Segment segment){

        float p1 = getUpper_point().getX();
        float p2 = segment.getUpper_point().getX();
        if(p1<p2){
            return true;
        }
        else return false;
    }

    /**
    compare 2 segments par rapport à leurs valeurs de x courrant,
     si il n'y a pas de différence on compare par rapport au Y max des lower_point des deux segments
     sinon on compare les upper_point
     si un des deux segments est horizontale, on compare le x passé en paramètre au x courrant de l'autre segment
     @param segment avec lequel on compare
     @param xHor valeur du x du segment horizontal
     @param y qui permet de calculer le x courrant
     @return 0 en cas d'agalité, -1 si il est plus petit ou 1 si il est plus grand
     */
    public int compareTo(Segment segment,float xHor,float y){
        if (!segment.isHorizontal()&&!this.isHorizontal()){
            float x1 =this.getCurrentPoint(y);
            float x2 =segment.getCurrentPoint(y);
            if (this.isEquals(segment)){
                return 0;
            }
            else if(!(Math.abs(x1-x2)<1e-4)) {

                if (x1 > x2){
                    return 1;
                }
                else if (x1 < x2){
                    return -1;
                }
            }
            else{

                float ymax = max(this.lower_point.getY(),segment.getLower_point().getY());
                x1 =this.getCurrentPoint(ymax);
                x2 =segment.getCurrentPoint(ymax);
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
        return 2;
    }


    /**
     * Vérifie si les deux segments sont égaux
     * @param segment avec qui on compare
     * @return true si les segments sont égaux, false sinon
     */
    public boolean isEquals (Segment segment){
        if (segment.getUpper_point().isEqualTo(this.upper_point) && segment.getLower_point().isEqualTo(this.lower_point)){
            return true;
        }
        else
            return false;
    }


    /**
     * regarde si le segment passe par p
     * @param p point par lequel on veut voir si le segment passe
     * @return true si le segment passe par p, false sinon
     */
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

    /**
     * calcule le point courrant par rapport à un y donné ainsi qu'avec les coordonnées du segment
     * @param y pour lequel on veut le x associé
     * @return x courrant
     */
    public float getCurrentPoint(float y) {   // à faire
        float x1 = upper_point.getX();
        float x2 = lower_point.getX();
        float y1 = upper_point.getY();
        float y2 = lower_point.getY();


        double x = x1 + ((y - y1) * (x2 - x1)) / (y2 - y1);
        x = Math.round(x * 100.0) / 100.0;
        return ( (float) x);

    }

    /**
     * vérifie si la droite est horizontale
     * @return true si vrai,false sinon
     */
    public boolean isHorizontal(){
        return (getLower_point().getY()==getUpper_point().getY());
    }

    /**
     * calcul l'intersection entre deux segments et vérifie qu'elle est dans les bornes des deux segments
     * @param Sr deuxième segment avec lequel on veut calculer l'intersection
     * @return null si pas d'intersection,sinon un point qui correspond à l'intersection
     */
    public Point isIntersectBy(Segment Sr){
        float x1=upper_point.getX();
        float y1=upper_point.getY();

        float x2=lower_point.getX();
        float y2=lower_point.getY();

        float x3=Sr.getUpper_point().getX();
        float y3=Sr.getUpper_point().getY();

        float x4=Sr.getLower_point().getX();
        float y4=Sr.getLower_point().getY();

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

            if (x1 > x2) {
                if (x > x1 || x < x2) {
                    return p;
                }
            } else if (x2 > x1) {
                if (x < x1 || x > x2) {
                    return p;

                }
            }

            if (x3 > x4) {
                if (x > x3 || x < x4) {
                    return p;
                }
            }
            else if (x4 > x3) {
                if (x < x3 || x > x4) {
                    return p;
                }
            }

            if (y1 > y2) {
                if (y > y1 || y < y2) {
                    return p;
                }
            }
            else if (y2 > y1) {
                if (y < y1 || y > y2) {
                    return p;
                }
            }

            if (y3 > y4) {
                if (y > y3 || y < y4) {
                    return p;
                }
            }
            else if (y4 > y3) {
                if (y < y3 || y > y4) {
                    return p;
                }
            }
            p = new Point((float) x,(float) y);
            }

        if (x1==x2 && y1!=y2) {
            a2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - a2 * x3;

            float y=a2*x1+b2;
            if(y<y1 && y>y2) {
                p= new Point(x1,y);
                return p;
            }

        }
        if (x3==x4 && y3!=y4) {
            a1 = (y2 - y1) / (x2 - x1);
            b1 = y2 - a1 * x1;

            float y=a1*x3+b1;
            if(y<y3 && y>y4) {
                p= new Point(x3,y);
                return p;
            }
        }
        if (x1!=x2 && y1==y2) {
            float x = Sr.getCurrentPoint(y1);
            if (x>x1 && x<x2){
                p= new Point(x,y1);
                return p;
            }
        }

        if (x3!=x4 && y3==y4) {
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

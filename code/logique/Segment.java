package code.logique;

import static java.lang.Math.max;
import static java.lang.Math.abs;


public class Segment{
    private Point upper_point;
    private Point lower_point;
    private double precision =1e-8;

    /**
    constructeur d'un segment
    @param upper_point point du dessus du segment
    @param lower_point point du dessous du segment
     */
    public Segment(Point upper_point,Point lower_point){
        this.upper_point=upper_point;
        this.lower_point=lower_point;
    }

    public Segment(double x1,double y1,double x2,double y2){
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

        double p1 = getUpper_point().getX();
        double p2 = segment.getUpper_point().getX();
        if(p1<p2){
            return true;
        }
        else return false;
    }

    /**
    compare 2 segments par rapport à leurs valeurs de x courant,
     si il n'y a pas de différence on compare par rapport au Y max des lower_point des deux segments
     sinon on compare les upper_point
     si un des deux segments est horizontal, on compare le x passé en paramètre au x courant de l'autre segment
     @param segment avec lequel on compare
     @param xHor valeur du x du segment horizontal
     @param y qui permet de calculer le x courrant
     @return 0 en cas d'agalité, -1 si il est plus petit ou 1 si il est plus grand
     */
    public int compareTo(Segment segment,double xHor,double y){
        if (!segment.isHorizontal()&&!this.isHorizontal()) {
            double x1 = this.getCurrentPoint(y);
            double x2 = segment.getCurrentPoint(y);
            int x = Comparaison.cp(x1, x2);
            if (x != 0) {
                return x;
            } else {
                double ymax = max(this.lower_point.getY(),segment.getLower_point().getY());
                x1 =this.getCurrentPoint(ymax);
                x2 =segment.getCurrentPoint(ymax);
                return Comparaison.cp(x1, x2);
            }
        }
        else {
            if (this.isEquals(segment)){

                return 0;
            }
            else if(this.isHorizontal()){
                double x1=segment.getCurrentPoint(y);
                int res=Comparaison.cp(x1, xHor);
                if(res<=0)
                    return 1;
                else
                    return -1;
            }
            else if(segment.isHorizontal()){
                double x1=this.getCurrentPoint(y);
                int res=Comparaison.cp(xHor, x1);
                if(res==-1)
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
            double x=this.getCurrentPoint(p.getY());
            if ((Math.abs(x-p.getX())<precision)){
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
    public double getCurrentPoint(double y) {   // à faire
        double x1 = upper_point.getX();
        double x2 = lower_point.getX();
        double y1 = upper_point.getY();
        double y2 = lower_point.getY();


        double x = x1 + ((y - y1) * (x2 - x1)) / (y2 - y1);
        //x = Math.round(x * 100.0) / 100.0;
        return  x;

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
        double x1=upper_point.getX();
        double y1=upper_point.getY();

        double x2=lower_point.getX();
        double y2=lower_point.getY();

        double x3=Sr.getUpper_point().getX();
        double y3=Sr.getUpper_point().getY();

        double x4=Sr.getLower_point().getX();
        double y4=Sr.getLower_point().getY();


        double b= -(x2-x1);
        double a= y2-y1;

        double e= -(x4-x3);
        double d= y4-y3;


        double c= a*x1  + b*y1;
        double f= d*x3  + e*y3;



        Point p = null;
        double y_ = 0;
        double x_ = 0;


        if(d==0){
            y_= f/e;
            x_=(c-b*y_)/a;
            p= new Point(x_,y_);
            return p;
        }

        if (-d *b +e * a !=0) {
            y_= (f*a-(d*c))/(-d*b+e*a);
            x_= (c-b*y_)/a;
            p= new Point(x_,y_);
            return p;
        }


        if (-d *b +e * a ==0){ return p;}


        return p;

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

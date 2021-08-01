package code.logique;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Point> intersection=new ArrayList<Point>();
    static Point lastEvent;
    static Q_Tree q;
    static ArrayList<Point> printQ=new ArrayList<Point>();

    /**
     * sur base d'une liste de segment, initialise la queue Q avec les points des segments et ensuite fait appel
     * à HandleEventPoint
     * @param segmentList liste des segments
     */
    public static void FindIntersections(ArrayList<Segment> segmentList) {
        q = new Q_Tree();
        Segment segment;
        for (int i = 0; i < segmentList.size(); i++) {
            segment = segmentList.get(i);
            q.startInsertion(segment.getUpper_point(), segment);
            q.startInsertion(segment.getLower_point(), null);
        }
        t = new T_Tree();
        lastEvent = new Point(0.0, 0.0);
        while (q.getRoot().getPoint() != null) {
            Q_Node p = q.getLastRemoved();
            q.removeNextEvent();
            printQ.add(p.getPoint());
            HandleEventPoint(p);
        }
    }

    /**
     * Récupère l'event point p, initialise U(p) avec les segments du node P
     * Cherche les segments passant par p dans T est les ajoute dans C(p) ou L(p)
     * Retire les segments qui sont dans C(p) et L(p) de T via le x et le y du lastEvent
     * on change le lastEvent par le point de p
     * On ajoute les segments de C(p) et U(p) dans T avec le x et le y du nouvel lastEvent
     * On calcule aussi le min et le max
     * si C(p) U U(p)==0, on cherche les segments les plus proche du point P dans T avec NrightP et NleftP
     * on fait un appel sur findNewEvent ensuite
     * sinon on cherche le prédécesseur de min dans T et le successeur de max dans T et on fait un appel sur findNewEvent
     * @param p node contenant le nouvel eventPoint ainsi que la liste de segment U(p)
     */
    public static void HandleEventPoint(Q_Node p){
        Segment Sl = null;
        Segment Sr= null;
        Segment Sp1= null; //doit etre min et max peut etre
        Segment Sp2= null;
        ArrayList<Segment> Up=p.getSegments();
        ArrayList<Segment> Cp=new ArrayList<Segment>();
        ArrayList<Segment> Lp=new ArrayList<Segment>();
        t.SegmentsContainPoint(p.getPoint(),Cp,Lp);
        if (Lp.size()+Up.size()+Cp.size()>1)
            intersection.add(p.getPoint());
        for (Segment segment : Lp ){
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());
        }
        for (Segment segment : Cp ){
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());
        }

        lastEvent=p.getPoint();

        Segment min=null;
        Segment max=null;
        for (Segment segment : Up ) {
            if (min == null || segment.compareTo(min, lastEvent.getX(), lastEvent.getY()) < 0) {
                min = segment;
            }
            if (max == null || segment.compareTo(max, lastEvent.getX(), lastEvent.getY()) > 0) {
                max = segment;
            }
            t.insert(segment);
        }
        for (Segment segment : Cp ){
            if(min==null || segment.compareTo(min,lastEvent.getX(),lastEvent.getY())<0){
                min=segment;
            }
            if(max==null || segment.compareTo(max,lastEvent.getX(),lastEvent.getY())>0){
                max=segment;
            }
            t.reinsert(segment,lastEvent.getX(),lastEvent.getY());
        }
        if(Up.size()+Cp.size()==0) {
            Sl=t.NleftP(p.getPoint(),null);
            Sr=t.NrightP(p.getPoint(),null);
            if (Sl!=null&&Sr!=null)
                FindNewEvent(Sl,Sr,p);}

        else{
            Sl=t.prev(min,null,lastEvent.getX(),lastEvent.getY());
            Sr=t.succ(max,null,lastEvent.getX(),lastEvent.getY());

            if( Sl!=null&&min!=null  && !Sl.isEquals(min)) {
                FindNewEvent(Sl, min, p);
            }
            if( Sr!=null&&max!=null && !Sr.isEquals(max)) {
                FindNewEvent(Sr, max, p);
            }
        }
    }

    /**
     * calcul si il y a une intersection entre Sl et Sr et vérifie que ce point n'as pas de Y plus grand
     * que le lastEvent,si le Y est égal, vérifie que le x est plus petit
     * ajoute l'intersection dans Q
     * @param Sl segment de gauche
     * @param Sr segment de droite
     * @param p node contenant le lastEvent point
     */
    public static void FindNewEvent(Segment Sl, Segment Sr,Q_Node p){
        Point intersect=Sl.isIntersectBy(Sr);

        if (intersect!=null){
            if ((intersect.getY()==p.getPoint().getY()&&intersect.getX()>p.getPoint().getX())||intersect.getY()<p.getPoint().getY()){
                q.startInsertion(intersect,null);
            }
        }
    }

    public static ArrayList<Point> getIntersection() {
        return intersection;
    }

    public static void setIntersection(ArrayList<Point> intersection) {
        Algo.intersection = intersection;
    }

    public static void setPrintQ(ArrayList<Point> printQ) {
        Algo.printQ = printQ;
    }

    public static ArrayList<Point> getPrintQ() {
        return printQ;
    }
}

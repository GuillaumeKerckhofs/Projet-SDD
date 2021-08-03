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
    public static void findIntersections(ArrayList<Segment> segmentList) {
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
            handleEventPoint(p);
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
    private static void handleEventPoint(Q_Node p){
        Segment sl = null;
        Segment sr= null;
        Segment sp1= null; //doit etre min et max peut etre
        Segment sp2= null;
        ArrayList<Segment> up=p.getSegments();
        ArrayList<Segment> cp=new ArrayList<Segment>();
        ArrayList<Segment> lp=new ArrayList<Segment>();
        t.SegmentsContainPoint(p.getPoint(),cp,lp);
        if (lp.size()+up.size()+cp.size()>1)
            intersection.add(p.getPoint());
        for (Segment segment : lp ){
            t.suppress_init(segment,lastEvent.getX(),lastEvent.getY());
        }
        for (Segment segment : cp ){
            t.suppress_init(segment,lastEvent.getX(),lastEvent.getY());
        }

        lastEvent=p.getPoint();

        Segment min=null;
        Segment max=null;
        for (Segment segment : up ) {
            if (min == null || segment.compareTo(min, lastEvent.getX(), lastEvent.getY()) < 0) {
                min = segment;
            }
            if (max == null || segment.compareTo(max, lastEvent.getX(), lastEvent.getY()) > 0) {
                max = segment;
            }
            t.insert_init(segment,lastEvent);
        }
        for (Segment segment : cp ){
            if(min==null || segment.compareTo(min,lastEvent.getX(),lastEvent.getY())<0){
                min=segment;
            }
            if(max==null || segment.compareTo(max,lastEvent.getX(),lastEvent.getY())>0){
                max=segment;
            }
            t.insert_init(segment,lastEvent);
        }
        if(up.size()+cp.size()==0) {
            sl=t.nleftP(p.getPoint(),null);
            sr=t.nrightP(p.getPoint(),null);
            if (sl!=null&&sr!=null)
                findNewEvent(sl,sr,p);}

        else{
            sl=t.prev(min,null,lastEvent.getX(),lastEvent.getY());
            sr=t.succ(max,null,lastEvent.getX(),lastEvent.getY());

            if( sl!=null&&min!=null  && !sl.isEquals(min)) {
                findNewEvent(sl, min, p);
            }
            if( sr!=null&&max!=null && !sr.isEquals(max)) {
                findNewEvent(sr, max, p);
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
    private static void findNewEvent(Segment Sl, Segment Sr, Q_Node p){
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

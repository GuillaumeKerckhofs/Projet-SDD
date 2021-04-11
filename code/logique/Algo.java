package code.logique;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Point> intersection=new ArrayList<Point>();
    static Point lastEvent;
    static Q_Tree q;
    static ArrayList<Point> printQ=new ArrayList<Point>();

    public static void FindIntersections(ArrayList<Segment> segmentList) {
        q = new Q_Tree();
        Segment segment;
        for (int i = 0; i < segmentList.size(); i++) {
            segment = segmentList.get(i);
            q.startInsertion(segment.getUpper_point(), segment);
            q.startInsertion(segment.getLower_point(), null);
        }
        t = new T_Tree();
        lastEvent = new Point(0, 0);
        while (q.getRoot().getPoint() != null) {
            Q_Node p = q.getLastRemoved();
            q.removeNextEvent();
            printQ.add(p.getPoint());
            HandleEventPoint(p);
        }
    }

    public static void HandleEventPoint(Q_Node p){
        Segment Sl = null;
        Segment Sr= null;
        Segment Sp1= null;
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

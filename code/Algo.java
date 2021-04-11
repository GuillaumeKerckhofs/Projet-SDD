package code;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Point> intersection=new ArrayList<Point>();
    static Point lastEvent;
    static Q_Tree q;
    static ArrayList<Point> printQ=new ArrayList<Point>();

    public static void FindIntersections(ArrayList<Segment> segmentList){


    System.out.println("");
        q= new Q_Tree();
        Segment segment;
        for (int i=0;i< segmentList.size();i++){
            segment=segmentList.get(i);
            System.out.println(segment);
            //System.out.println(segment.getUpper_point());
            //System.out.println(segment.getLower_point());
            q.startInsertion(segment.getUpper_point(), segment);
            q.startInsertion(segment.getLower_point(),null);

        }
        System.out.println("=========== Q =========");
        q.print();
        System.out.println("");
        t=new T_Tree();
        lastEvent=new Point(0,0);      // (0,0)?


        while (q.getRoot().getPoint()!=null){

            System.out.println("================ new point remove from Q ====================");



            System.out.println("last remove point = "+q.getLastRemoved().getPoint());

            Q_Node p =q.getLastRemoved();
            System.out.println("p.seg ="+p.getSegments());
            q.removeNextEvent();
            printQ.add(p.getPoint());
            HandleEventPoint(p);
        }
        System.out.println(intersection);
        System.out.println("fin");
        t.print(0,lastEvent.getY());
    }

    public static void HandleEventPoint(Q_Node p){

        System.out.println("");
        System.out.println("======= enter handle ==========");
        System.out.println("");
        Segment Sl = null;
        Segment Sr= null;
        Segment Sp1= null;
        Segment Sp2= null;
        ArrayList<Segment> Up=p.getSegments();

        for (Segment segment : Up )
            System.out.println("segment Up= "+segment);

        System.out.println("");

        ArrayList<Segment> Cp=new ArrayList<Segment>();
        ArrayList<Segment> Lp=new ArrayList<Segment>();


        t.SegmentsContainPoint(p.getPoint(),Cp,Lp);


        if (Lp.size()+Up.size()+Cp.size()>1)

            intersection.add(p.getPoint());




        System.out.println("up= "+Up.size());
        System.out.println("Cp= "+Cp.size());
        System.out.println("Lp= "+Lp);
        System.out.println("lastEventX "+lastEvent.getX());
        System.out.println("lastEventY "+lastEvent.getY());
        System.out.println("");

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        //t.print(0,lastEvent.getY());
        System.out.println("");

        for (Segment segment : Lp ){
            System.out.println("on veut supprimer "+segment);
            System.out.println("");
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());
        }


        //System.out.println("==========+");
        for (Segment segment : Cp ){
            System.out.println("on veut supprimer "+segment);
            System.out.println("");
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());}

        lastEvent=p.getPoint();

        Segment min=null;
        Segment max=null;
        for (Segment segment : Up ){
            if(min==null || segment.compareTo(min,lastEvent.getX(),lastEvent.getY())<0){
                min=segment;
            }
            if(max==null || segment.compareTo(max,lastEvent.getX(),lastEvent.getY())>0){
                max=segment;
            }
            t.insert(segment);
            System.out.println("insert "+segment);
            System.out.println("");}


        for (Segment segment : Cp ){
            if(min==null || segment.compareTo(min,lastEvent.getX(),lastEvent.getY())<0){
                min=segment;
            }
            if(max==null || segment.compareTo(max,lastEvent.getX(),lastEvent.getY())>0){
                max=segment;
            }
            t.reinsert(segment,lastEvent.getX(),lastEvent.getY());
            System.out.println("reinsert "+segment);
        }
        t.print(0,lastEvent.getY());
        System.out.println("");


        if(Up.size()+Cp.size()==0) {
            System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
            Sl=t.NleftP(p.getPoint(),null);
            Sr=t.NrightP(p.getPoint(),null);
            System.out.println("Sl= "+Sl);
            System.out.println("Sr= "+Sr);
            System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
            if (Sl!=null&&Sr!=null)
                FindNewEvent(Sl,Sr,p);}

        else{

            System.out.println("max = "+max);
            System.out.println("min = "+min);

            Sl=t.prev(min,null,lastEvent.getX(),lastEvent.getY());
            Sr=t.succ(max,null,lastEvent.getX(),lastEvent.getY());

            System.out.println("Sl= "+Sl);
            System.out.println("Sr= "+Sr);

            if( Sl!=null&&min!=null  && !Sl.isEquals(min)) {
                FindNewEvent(Sl, min, p);
            }
            if( Sr!=null&&max!=null && !Sr.isEquals(max)) {
                FindNewEvent(Sr, max, p);
            }



        }
    }

    public static void FindNewEvent(Segment Sl, Segment Sr,Q_Node p){
        System.out.println("");
        System.out.println("======= find new event ==========");
        System.out.println("");

        Point intersect=Sl.isIntersectBy(Sr);
        System.out.println("intersect = "+intersect);
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

    public static ArrayList<Point> getPrintQ() {
        return printQ;
    }
}

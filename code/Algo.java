package code;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Q_Node> intersection;
    static Point lastEvent;

    public static void FindIntersections(ArrayList<Segment> segmentList){

        /*
        1.Initialize an empty event queue Q. Next, insert the segment endpoints intoQ; when an upper endpoint is inserted, the corresponding segment should be stored with it.
        2.Initialize an empty status structure T.
        3.while Q is not empty
        4.doDetermine the next event point p in Q and delete it.
        5.HandleEventPoint(p);*/

        Q_Tree q= new Q_Tree();
        Segment segment;
        for (int i=0;i< segmentList.size();i++){
            segment=segmentList.get(i);
            //System.out.println(segment.getUpper_point());
            q.startInsertion(segment.getUpper_point(), segment);
            q.startInsertion(segment.getLower_point(),null);
            //System.out.println("oui");
        }
        //System.out.println("ici");
        //q.print();
        t=new T_Tree();
        lastEvent=new Point(0,0);      // (0,0)?

        while (q.getRoot().getPoint()!=null){
            //System.out.println("q=");
            //System.out.println(q.getRoot().getPoint());

            q.removeNextEvent();

            //q.print();
            System.out.println(q.getLastRemoved().getPoint());

            Q_Node p =q.getLastRemoved();
            System.out.println("p.seg ="+p.getSegments());
            /*
            System.out.println("-------------------------");
            System.out.println(p.getPoint().getX());
            System.out.println(p.getPoint().getY());
            System.out.println("-------------------------");

             */
            HandleEventPoint(p);
        }
        System.out.println("fin");
    }

    public static void HandleEventPoint(Q_Node p){

        /*
        1.Let U(p)be the set of segments whose upper endpoint is p; these segments are stored with the event point p.  (For horizontal segments, the upperendpoint is by definition the left endpoint.)
        2.Find all segments stored in T that contain p; they are adjacent in T. Let L(p)denote the subset of segments found whose lower endpoint isp, and let C(p)denote the subset of segments found that contain p in their interior.
        3.if L(p)∪U(p)∪C(p)contains more than one segment
        4.then Report p as an intersection, together with L(p),U(p), andC(p).
        5.Delete the segments in L(p)∪C(p)from T.
        6.Insert the segments in U(p)∪C(p)into T. The order of the segments inTshould correspond to the order in which they are intersected by a sweepline just belowp. If there is a horizontal segment, it comes last among all segments containing p.
        7.(∗Deleting and re-inserting the segments of C(p)reverses their order.∗)
        8.if U(p)∪C(p)=ensemble vide
        9.then Let sl and sr be the left and right neighbors of p in T.
        10.FINDNEWEVENT(sl,sr,p)
        11.else Let s′be the leftmost segment of U(p)∪ C(p)in T.
        12.Let sl be the left neighbor of s′in T.
        13.FINDNEWEVENT(sl,s′,p)
        14.Let s′′be the rightmost segment of U(p)∪ C(p) in T.
        15.Let sr be the right neighbor of s′′in T.
        16.FINDNEWEVENT(s′′,sr,p)

         */
        Segment Sl = null;
        Segment Sr= null;
        Segment Sp1= null;
        Segment Sp2= null;
        ArrayList<Segment> Up=p.getSegments();
        ArrayList<Segment> Cp=new ArrayList<Segment>();
        ArrayList<Segment> Lp=new ArrayList<Segment>();
        t.SegmentsContainPoint(p.getPoint(),Cp,Lp);

        if (Lp.size()+Up.size()+Cp.size()>1)

            intersection.add(p);
        System.out.println("==========");

        System.out.println(Up.size());
        System.out.println(Cp.size());
        System.out.println(Lp);


        if(Lp.size()!=0){
            for (Segment segment : Lp )
                t.suppress(segment,lastEvent.getX(),lastEvent.getY());}
        if(Cp.size()!=0){
            System.out.println("==========+");
            for (Segment segment : Cp )
                t.suppress(segment,lastEvent.getX(),lastEvent.getY());}
        for (Segment segment : Up )
            t.insert(segment);
        if(Cp.size()!=0){
            System.out.println("==========+");
            for (Segment segment : Cp )
                t.reinsert(segment,p.getPoint().getX(),p.getPoint().getY());}
        lastEvent=p.getPoint();


        if(Up.size()+Cp.size()==0) {

            t.NleftP(p.getPoint(),Sl);
            t.NrightP(p.getPoint(),Sr);
            if (Sl!=null&&Sr!=null)
                FindNewEvent(Sl,Sr,p);}

        else{
            t.print(0);
            System.out.println(p.getPoint());

            Sp1=t.LeftMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            System.out.println("ohohoh"+Sp1);
            //System.out.println("++++++++++++++++++++++++++++++++++++");
            Sl=t.searchPrev(Sp1,lastEvent.getX(),lastEvent.getY());

            //System.out.println("Sl="+Sl);
            if (Sl!=null)
                FindNewEvent(Sp1,Sl,p);

            System.out.println("++++++++++++++++++++++++++++++++++++----");
            Sp2=t.RightMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            System.out.println("++++++++++++++++++++++++++++++++++++****");

            Sr=t.searchSucc(Sp2,lastEvent.getX(),lastEvent.getY());
            //System.out.println("Sr="+Sr);
            if (Sr!=null)
                FindNewEvent(Sp2,Sr,p);



        }
    }

    public static void FindNewEvent(Segment Sl, Segment Sr,Q_Node p){

        System.out.println("**********************************************************");
    }
}

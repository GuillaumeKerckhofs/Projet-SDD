package code;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Q_Node> intersection=new ArrayList<Q_Node>();
    static Point lastEvent;

    public static void FindIntersections(ArrayList<Segment> segmentList){



        Q_Tree q= new Q_Tree();
        Segment segment;
        for (int i=0;i< segmentList.size();i++){
            segment=segmentList.get(i);
            q.startInsertion(segment.getUpper_point(), segment);
            q.startInsertion(segment.getLower_point(),null);

        }

        t=new T_Tree();
        lastEvent=new Point(0,0);      // (0,0)?

        while (q.getRoot().getPoint()!=null){

            q.removeNextEvent();

            System.out.println(q.getLastRemoved().getPoint());

            Q_Node p =q.getLastRemoved();
            System.out.println("p.seg ="+p.getSegments());

            HandleEventPoint(p);
        }
        System.out.println("fin");
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

            intersection.add(p);


        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("up= "+Up.size());
        System.out.println("Cp= "+Cp.size());
        System.out.println("Lp= "+Lp);
        System.out.println("lastEventX "+lastEvent.getX());
        System.out.println("lastEventY "+lastEvent.getY());

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
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

            Sl=t.NleftP(p.getPoint(),null);
            Sr=t.NrightP(p.getPoint(),null);
            if (Sl!=null&&Sr!=null)
                FindNewEvent(Sl,Sr,p);}

        else{
            t.print(0);
            System.out.println(p.getPoint());

            Sp1=t.LeftMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            System.out.println("ohohoh"+Sp1);
            //System.out.println("++++++++++++++++++++++++++++++++++++");
            if (Sp1!=null){
            Sl=t.searchPrev(Sp1,lastEvent.getX(),lastEvent.getY());}

            //System.out.println("Sl="+Sl);
            if (Sl!=null)
                FindNewEvent(Sp1,Sl,p);

            System.out.println("++++++++++++++++++++++++++++++++++++----");
            Sp2=t.RightMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            System.out.println("++++++++++++++++++++++++++++++++++++****");
            if (Sp2!=null){
            Sr=t.searchSucc(Sp2,lastEvent.getX(),lastEvent.getY());}
            //System.out.println("Sr="+Sr);
            if (Sr!=null)
                FindNewEvent(Sp2,Sr,p);



        }
    }

    public static void FindNewEvent(Segment Sl, Segment Sr,Q_Node p){

        System.out.println("**********************************************************");

        float x1=Sl.getUpper_point().getX();
        float y1=Sl.getUpper_point().getX();

        float x2=Sl.getLower_point().getX();
        float y2=Sl.getLower_point().getX();

        float x3=Sr.getUpper_point().getX();
        float y3=Sr.getUpper_point().getX();

        float x4=Sr.getLower_point().getX();
        float y4=Sr.getLower_point().getX();
        if(x1==x2){
            float a2 = (y4-y3)/(x4-x3);
            float b2 = y3 - a2*x3;
        }
        else if(x3==x4){
            float a1 = (y2-y1)/(x2-x1);
            float b1 = y1 - a1*x1;
        }
        else{
            float a1 = (y2-y1)/(x2-x1);
            float b1 = y1 - a1*x1;
            float a2 = (y4-y3)/(x4-x3);
            float b2 = y3 - a2*x3;
        }
    }
}

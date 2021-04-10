package code;

import java.util.ArrayList;

public class Algo {

    static T_Tree t;
    static ArrayList<Q_Node> intersection=new ArrayList<Q_Node>();
    static Point lastEvent;
    static Q_Tree q;

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

        t=new T_Tree();
        lastEvent=new Point(0,0);      // (0,0)?


        while (q.getRoot().getPoint()!=null){

            q.removeNextEvent();

            System.out.println("last remove point = "+q.getLastRemoved().getPoint());

            Q_Node p =q.getLastRemoved();
            System.out.println("p.seg ="+p.getSegments());

            HandleEventPoint(p);
        }
        //System.out.println("fin");
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
        //System.out.println("ici");
        //System.out.println(p.getPoint());
        for (Segment segment : Up )
            System.out.println("segment Up= "+segment);

        System.out.println("");

        ArrayList<Segment> Cp=new ArrayList<Segment>();
        ArrayList<Segment> Lp=new ArrayList<Segment>();
        t.SegmentsContainPoint(p.getPoint(),Cp,Lp);

        if (Lp.size()+Up.size()+Cp.size()>1)

            intersection.add(p);

/*
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        System.out.println("up= "+Up.size());
        System.out.println("Cp= "+Cp.size());
        System.out.println("Lp= "+Lp);
        System.out.println("lastEventX "+lastEvent.getX());
        System.out.println("lastEventY "+lastEvent.getY());

 */

        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        //System.out.println("arbre 1");
        //t.print(0);
        //System.out.println("----------------------------------------------------------");
        for (Segment segment : Lp )
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());

        System.out.println("==========+");
        for (Segment segment : Cp )
            t.suppress(segment,lastEvent.getX(),lastEvent.getY());

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
            System.out.println("insert"+segment);}

        System.out.println("==========+");
        for (Segment segment : Cp ){
            if(min==null || segment.compareTo(min,lastEvent.getX(),lastEvent.getY())<0){
                min=segment;
            }
            if(max==null || segment.compareTo(max,lastEvent.getX(),lastEvent.getY())>0){
                max=segment;
            }
            t.reinsert(segment,lastEvent.getX(),lastEvent.getY());
            System.out.println("reinsert"+segment);
        }

        //System.out.println("arbre 2");
        //t.print(0);
        System.out.println("");

        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        if(Up.size()+Cp.size()==0) {

            Sl=t.NleftP(p.getPoint(),null);
            Sr=t.NrightP(p.getPoint(),null);
            if (Sl!=null&&Sr!=null)
                FindNewEvent(Sl,Sr,p);}

        else{
            /*t.print(0);
           //System.out.println(p.getPoint());

            Sp1=t.LeftMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            //System.out.println("++++++++++++++++++++++++++++++++++++");
            if (Sp1!=null){
            Sl=t.searchPrev(Sp1,lastEvent.getX(),lastEvent.getY());}

            //System.out.println("Sl="+Sl);
            if (Sl!=null)
                FindNewEvent(Sp1,Sl,p);

            //System.out.println("++++++++++++++++++++++++++++++++++++----");
            Sp2=t.RightMostSegment1(Up,Cp,lastEvent.getX(),lastEvent.getY());
            //System.out.println("++++++++++++++++++++++++++++++++++++****");
            if (Sp2!=null){
            Sr=t.searchSucc(Sp2,lastEvent.getX(),lastEvent.getY());}
            //System.out.println("Sr="+Sr);
            if (Sr!=null)
                FindNewEvent(Sp2,Sr,p);*/


            System.out.println("max = "+max);
            System.out.println("min = "+min);

            Sl=t.prev(min,lastEvent.getX(),lastEvent.getY());
            Sr=t.succ(max,lastEvent.getX(),lastEvent.getY());

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
        System.out.println("Sl= "+Sl);
        System.out.println("Sr= "+Sr);
        Point intersect=Sl.isIntersectBy(Sr);
        System.out.println("intersect"+intersect);
        if (intersect!=null){
            if ((intersect.getY()==p.getPoint().getY()&&intersect.getX()>p.getPoint().getX())||intersect.getY()<p.getPoint().getY()){
                q.startInsertion(intersect,null);

            }
        }
    }
}

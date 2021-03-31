package code;

import java.util.ArrayList;

public class Algo {

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
            System.out.println(segment.getUpper_point());
            q.startInsertion(segment.getUpper_point(), segment);
            System.out.println("oui");
        }
        T_Tree t=new T_Tree();

        while (q.getRoot()!=null){
            q.removeNextEvent();
            Point p =q.getLastRemoved().getPoint();
            System.out.println(p.getX());
            System.out.println(p.getY());
            System.out.println("-------------------------");
            HandleEventPoint(p);
        }
    }

    public static void HandleEventPoint(Point p){
        /*
        1.Let U(p)be the set of segments whose upper endpoint isp; these segments are stored with the event point p.  (For horizontal segments, the upperendpoint is by definition the left endpoint.)
        2.Find all segments stored in T that contain p; they are adjacent in T. Let L(p)denote the subset of segments found whose lower endpoint isp, and let C(p)denote the subset of segments found that contain p in their interior.
        3.if L(p)∪U(p)∪C(p)contains more than one segment
        4.then Report pas an intersection, together with L(p),U(p), andC(p).
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
        System.out.println("handle");
    }
}

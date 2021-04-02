package code;

import java.util.ArrayList;

import static java.lang.Integer.max;

public class Q_Node{
    private Point point;
    private ArrayList<Segment> segments;
    private Q_Node Right;
    private Q_Node Left;
    private int height;

    public Q_Node(Point point, Segment segment, Q_Node Left, Q_Node Right){
        this.point=point;
        this.addSegment(segment);
        this.Right=Right;
        this.Left=Left;
        this.height=1;
    }

    public Q_Node(){
        this(null,null,null,null);
    }

    public void Height(){
        if(this.isLeaf()){
            this.height=1;
        }
        else{
            if(Left.isEmpty()){
                this.height=Right.getHeight()+1;
            }
            else if(Right.isEmpty()){
                this.height=Left.getHeight()+1;
            }
            else{
                this.height=1+max(Left.getHeight(),Right.getHeight());
            }
        }

    }

    public int Bal(){
        if(this.isLeaf()){
            return 0;
        }
        else{
            if(this.Left.isEmpty()){
                return(Right.getHeight());
            }
            else if(this.Right.isEmpty()){
                return(-(Left.getHeight()));
            }
            else{
                return(Right.getHeight()-Left.getHeight());
            }
        }
    }

    public void changeNode(Q_Node node2){
        this.point=node2.getPoint();
        this.addSegment(node2.getSegments);
        this.Left=node2.getLeft();
        this.Right=node2.getRight();

    }
    public void addSegment(Segment segment) {
        if(segment =!null){
            this.segments.add(segment);
        }
    }

    public boolean isLeaf(){ return (this.Right.isEmpty() && this.Left.isEmpty());}

    public boolean isEmpty(){ return (this.point==null && this.Right==null && this.Left==null);}

    public void setRight(Q_Node right) {
        this.Right = right;
    }

    public void setLeft(Q_Node left) {
        this.Left = left;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public Q_Node getRight() {
        return Right;
    }

    public Q_Node getLeft() {
        return Left;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }
}
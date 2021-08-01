package code.logique;

import java.util.ArrayList;

import static java.lang.Integer.max;

public class Q_Node{
    private Point point;
    private ArrayList<Segment> segments;
    private Q_Node right;
    private Q_Node left;
    private int height;

    /**
    @param point data du noeud
    @param segment segment qui a pour upper point le point passé en paramètre
    @param left Fils gauche du noeud
    @param right Fils droit du noeud
     */
    public Q_Node(Point point, Segment segment, Q_Node left, Q_Node right){
        this.point=point;
        this.segments=new ArrayList<Segment>();
        this.addSegment(segment);
        this.right =right;
        this.left =left;
        this.height=1;
    }

    public Q_Node(){
        this(null,null,null,null);
    }
    /**
    Change la valeur de la hauteur du noeud en prenant la hateur max entre les 2 hauteurs de ces fils
     */
    public void height(){
        if(this.isLeaf()){
            this.height=1;
        }
        else{
            if(left.isEmpty()){
                this.height= right.getHeight()+1;
            }
            else if(right.isEmpty()){
                this.height= left.getHeight()+1;
            }
            else{
                this.height=1+max(left.getHeight(), right.getHeight());
            }
        }

    }

    /**
    @return retourne la balance du noeud
     */
    public int bal(){
        if(this.isLeaf()){
            return 0;
        }
        else{
            if(this.left.isEmpty()){
                return(right.getHeight());
            }
            else if(this.right.isEmpty()){
                return(-(left.getHeight()));
            }
            else{
                return(right.getHeight()- left.getHeight());
            }
        }
    }

    /**
    change les variables du noeud par celle du noeud passé en argument
    @param node2 noeud qui deviendra le noeud courrant
     */
    public void changeNode(Q_Node node2){
        this.point=node2.getPoint();
        this.segments=node2.getSegments();
        this.left =node2.getLeft();
        this.right =node2.getRight();

    }

    /**
    ajoute un segment à la liste des segments s'il n'est pas null
    @param segment a rajouté à la liste
     */
    public void addSegment(Segment segment) {
        if(segment !=null){
            this.segments.add(segment);
        }
    }

    /**
    return true si le noeud est une feuille, false sinon
     */
    public boolean isLeaf(){ return (this.right.isEmpty() && this.left.isEmpty());}

    /**
    return true si le noeud est vide, false sinon
     */
    public boolean isEmpty(){ return (this.point==null && this.right ==null && this.left ==null);}

    public void setRight(Q_Node right) {
        this.right = right;
    }

    public void setLeft(Q_Node left) {
        this.left = left;
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
        return right;
    }

    public Q_Node getLeft() {
        return left;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }
}
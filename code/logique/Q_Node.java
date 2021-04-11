package code.logique;

import java.util.ArrayList;

import static java.lang.Integer.max;

public class Q_Node{
    private Point point;
    private ArrayList<Segment> segments;
    private Q_Node Right;
    private Q_Node Left;
    private int height;

    /*
    @param Point data du noeud
    @param Segment segment qui a pour upper point le point passé en paramètre
    @param Left Fils gauche du noeud
    @param Right Fils droit du noeud
     */
    public Q_Node(Point point, Segment segment, Q_Node Left, Q_Node Right){
        this.point=point;
        this.segments=new ArrayList<Segment>();
        this.addSegment(segment);
        this.Right=Right;
        this.Left=Left;
        this.height=1;
    }

    public Q_Node(){
        this(null,null,null,null);
    }
    /*
    Change la valeur de la hauteur du noeud en prenant la hateur max entre les 2 hauteurs de ces fils
     */
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

    /*
    @return retourne la balance du noeud
     */
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

    /*
    change les variables du noeud par celle du noeud passé en argument
    @param node2 noeud qui deviendra le noeud courrant
     */
    public void changeNode(Q_Node node2){
        this.point=node2.getPoint();
        this.segments=node2.getSegments();
        this.Left=node2.getLeft();
        this.Right=node2.getRight();

    }

    /*
    ajoute un segment à la liste des segments s'il n'est pas null
    @param segment a rajouté à la liste
     */
    public void addSegment(Segment segment) {
        if(segment !=null){
            this.segments.add(segment);
        }
    }

    /*
    return true si le noeud est une feuille, false sinon
     */
    public boolean isLeaf(){ return (this.Right.isEmpty() && this.Left.isEmpty());}

    /*
    return true si le noeud est vide, false sinon
     */
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
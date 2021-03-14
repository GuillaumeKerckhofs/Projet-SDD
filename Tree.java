import org.w3c.dom.Node;

public class Tree <Segment>{
    private Segment data;
    private Tree <Segment> Ltree;
    private Tree <Segment> Rtree;

    public Tree(Segment data, Tree<Segment> Ltree, Tree<Segment> Rtree){
        data = data;
        Ltree=Ltree;
        Rtree=Rtree;
    }

    public Tree() {
        this(null,null,null);
    }


    public void setData(Segment dara) {
        data = data;
    }
    public Segment getData() {
        return data;
    }

    public void setLeft(Tree<Segment> l) {
        Ltree = l;
    }
    public Tree<Segment> getLeft() {
        return Ltree;
    }

    public void setRight(Tree<Segment> r) {
        Rtree = r;
    }
    public Tree<Segment> getRight() {
        return Rtree;
    }

    public void insertEmpty(Segment data) {
        data = data;
        Ltree = new Tree();
        Rtree = new Tree();
    }





}

import org.w3c.dom.Node;

public class Tree <Segment>{
    private Segment data;
    private Tree <Segment> Ltree;
    private Tree <Segment> Rtree;
    private int height;

    public Tree(Segment data, Tree<Segment> Ltree, Tree<Segment> Rtree){
        data = data;
        Ltree=Ltree;
        Rtree=Rtree;
        height = 0;
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

    public void setHeight(int h) {
        height = height;
    }
    public int getHeight() {
        return height;
    }

    public boolean isEmpty() {
        if (data == null && Ltree == null && Rtree == null)
            return true;
        else
            return false;
    }

    public void height() {
        if (isEmpty())
            height = 0;
        else
            height = 1 + Math.max(getLeft().getHeight(),
                    getRight().getHeight());
    }


    public int balance() {
        if (isEmpty())
            return 0;
        else
            return getRight().getHeight() - getLeft().getHeight();
    }


    public void insertEmpty(Segment data) {
        data = data;
        Ltree = new Tree();
        Rtree = new Tree();
        height = 1;
    }





}

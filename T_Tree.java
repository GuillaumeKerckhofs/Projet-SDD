

public class T_Tree <Segment>{
    private Segment data;
    private T_Tree <Segment> Ltree;
    private T_Tree <Segment> Rtree;
    private int height;

    public T_Tree(Segment data, T_Tree<Segment> Ltree, T_Tree<Segment> Rtree){
        data = data;
        Ltree = Ltree;
        Rtree = Rtree;
        height = 0;
    }

    public T_Tree() {
        this(null,null,null);
    }


    public void setData(Segment dara) {
        data = data;
    }
    public Segment getData() {
        return data;
    }

    public void setLeft(T_Tree<Segment> l) {
        Ltree = l;
    }
    public T_Tree<Segment> getLeft() {
        return Ltree;
    }

    public void setRight(T_Tree<Segment> r) {
        Rtree = r;
    }
    public T_Tree<Segment> getRight() {
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
        Ltree = new T_Tree();
        Rtree = new T_Tree();
        height = 1;
    }





}

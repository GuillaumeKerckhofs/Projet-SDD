

public class T_Tree {
    private Segment data;
    private T_Tree Ltree;
    private T_Tree Rtree;
    private int height;

    public T_Tree(Segment data, T_Tree Ltree, T_Tree Rtree){
        data = data;
        Ltree = Ltree;
        Rtree = Rtree;
        height = 0;
    }

    public T_Tree() {
        this(null,null,null);
    }


    public void setData(Segment data) {
        data = data;
    }
    public Segment getData() {
        return data;
    }

    public void setLeft(T_Tree l) {
        Ltree = l;
    }
    public T_Tree getLeft() {
        return Ltree;
    }

    public void setRight(T_Tree r) {
        Rtree = r;
    }
    public T_Tree getRight() {
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

    public void insert(T_Tree T, Segment data){
        if (isEmpty())
            insertEmpty(data);
        else {
            if (data.smallerThan(T.getData())){
                insert(T.getLeft(),data);
                equilibrate(T);
            }
            else
            if (T.getData().smallerThan(data)){
                insert(T.getRight(),data);
                equilibrate(T);
            }
        }
    }

    public void insertEmpty(Segment data) {
        data = data;
        Ltree = new T_Tree();
        Rtree = new T_Tree();
        height = 1;
    }

    public void equilibrate(T_Tree T){
        if(T.balance()==2){
            if(T.getRight().balance()>=0){
                rotateLeft(T);
            }
            else{
                rotateRight(T.getRight());
                rotateLeft(T);
            }
        }
        else if(T.balance()==-2){
            if(T.getLeft().balance()<=0){
                rotateRight(T);
            }
            else{
                rotateLeft(T.getLeft());
                rotateRight(T);
            }
        }
        else{
            T.height();
        }
    }

    public void rotateLeft(T_Tree T){
        T_Tree tmp=T;
        T=T.getRight();
        tmp.setRight(T.getLeft());
        T.setLeft(tmp);
        tmp.height();
        T.height();
    }

    public void rotateRight(T_Tree T){
        T_Tree tmp=T;
        T=T.getLeft();
        tmp.setLeft(T.getRight());
        T.setRight(tmp);
        tmp.height();
        T.height();
    }



}

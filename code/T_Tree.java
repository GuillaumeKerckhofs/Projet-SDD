package code;

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


    public void setData(Segment data) { this.data=data;}
    public Segment getData() {
        return data;
    }

    public void setLeft(T_Tree l) {
        Ltree = l;
    }
    public T_Tree getLeft() {
        return Ltree;
    }

    public void setRight(T_Tree r) { Rtree = r; }
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

    public void insert(Segment data){

        if (isEmpty()){
            insertEmpty(data);
            //System.out.println("---------");
            }
        else {
            if (getData().tSmallerThan(data)){
                //System.out.println(getData().getUpper_point().getX()+"<"+data.getUpper_point().getX());

                if (getLeft().isEmpty()&& getRight().isEmpty())
                    getLeft().insert(getData());
                getRight().insert(data);
                equilibrate();
            }
            else {   //pas trop sûr
                //System.out.println(getData().getUpper_point().getX()+">"+data.getUpper_point().getX());

                if (getLeft().isEmpty()&& getRight().isEmpty())
                    getRight().insert(getData());
                getLeft().insert(data);
                equilibrate();

            }
        }
    }

    public void insertEmpty(Segment data) {

        this.setData (data);
        this.setLeft (new T_Tree());
        this.setRight (new T_Tree());
        this.height = 1;
    }

    public void equilibrate(){
        if(balance()==2){
            if(getRight().balance()>=0){
                rotateLeft(this);
            }
            else{
                rotateRight(getRight());
                rotateLeft(this);
            }
        }
        else if(balance()==-2){
            if(getLeft().balance()<=0){
                rotateRight(this);
            }
            else{
                rotateLeft(getLeft());
                rotateRight(this);
            }
        }
        else{
            this.height();
        }
    }

    public void rotateLeft(T_Tree T){


        Segment d = T.getData();
        T_Tree t = T.getRight();
        T.setData(t.getData());
        T.setRight(t.getRight());
        t.setData(d);
        t.setRight(t.getLeft());
        t.setLeft(T.getLeft());
        T.setLeft(t);
        t.height();
        T.height();
    }

    public void rotateRight(T_Tree T){

        Segment d = T.getData();
        T_Tree t = T.getLeft();
        T.setData(t.getData());
        T.setLeft(t.getLeft());
        t.setData(d);
        t.setLeft(t.getRight());
        t.setRight(T.getRight());
        T.setRight(t);
        t.height();
        T.height();
    }
    public boolean isLeaf (){
        if (getRight().isEmpty()&& getLeft().isEmpty())
            return true;
        return false;
    }

    public boolean search(Segment data) {
        if (isEmpty())
            return false;
        else	if (getData().compareTo(data) < 0)
            return getRight().search(data);
        else 	if (getData().compareTo(data) > 0)
            return getLeft().search(data);
        else 	return true;
    }



    public void suppress(Segment data) {

        if (!isEmpty()) {
            if (getData().compareTo(data)<0)
                getRight().suppress(data);
            else if (getData().compareTo(data)>0)
                getLeft().suppress(data);
            else if (isLeaf())	{
                //System.out.println("feuille");
                suppressRoot(); }
                else {suppressRoot();
                equilibrate();
                if (getRight().getData().compareTo(data)==0)
                    getRight().suppress(data);
                else if (getLeft().getData().compareTo(data)==0)
                    getLeft().suppress(data);
                }
            equilibrate();
        }

    }

    public Segment suppressMin() {
        Segment min;
        if (getLeft().isEmpty()) {
            min = getData();
            T_Tree t = getRight();
            setData(t.getData());
            setLeft(t.getLeft());
            setRight(t.getRight());
        }
        else
            min = getLeft().suppressMin();
        equilibrate();
        return min;
    }

    public void suppressRoot() {
        if (getLeft().isEmpty()) {
            T_Tree t = getRight();
            setData(t.getData());
            setLeft(t.getLeft());
            setRight(t.getRight());
        }
        else if (getRight().isEmpty()) {
            T_Tree t = getLeft();
            setData(t.getData());
            setRight(t.getRight());
            setLeft(t.getLeft());
        }
        else
            setData(getRight().suppressMin());
        equilibrate();
    }

    public void print(int space) {   // a nettoyer quand fini
        if (!isEmpty()) {
            //System.out.println("left");
            space+=5;
            Rtree.print(space);
            System.out.print("\n");
            for(int i = 1;i<space;i++){
                System.out.print(" ");
            }
            //System.out.println("remonte");
            System.out.println(data );

            //System.out.println("right");
            Ltree.print(space);

        }
    }


}

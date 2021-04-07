package code;

import java.util.ArrayList;

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

    public void insert (Segment data){
        T_Tree T = new T_Tree();
        insert2(T,data);
    }

    public void insert2(T_Tree node,Segment data){

        if (getData()!=data){
            if (getData()==null){
                insertEmpty(data);
                //System.out.println("---------");
                }

            else {
                if (getData().compareTo(data)<0){  //node.data<data
                    //System.out.println(getData().getUpper_point().getX()+"<"+data.getUpper_point().getX());

                    if (getLeft().isEmpty()&& getRight().isEmpty()) //feuille
                        {getLeft().insert2(node,getData());
                        node.setData(data);}


                    getRight().insert2(node,data);
                    equilibrate();
                }
                else if (getData().compareTo(data)>0){   //node.data>data
                    //System.out.println(getData().getUpper_point().getX()+">"+data.getUpper_point().getX());
                    if (getLeft().isEmpty()&& getRight().isEmpty()){//feuille
                        getRight().insert2(node,getData());
                        setData(data);

                        }

                    node=this;
                    getLeft().insert2(node,data);
                    equilibrate();  }
                else {
                    System.out.println(getData()+";"+data);
                    System.out.println("aled");
                }
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

    public void suppress (Segment data){
        T_Tree T = new T_Tree();
        suppress2(data,T);
    }

    public void suppress2 (Segment data,T_Tree node){

        if (!isEmpty()){

             if (isLeaf()){
                setData(null);
            }
            else if (getLeft().getData().compareTo(data)==0&&getLeft().isLeaf()) {

                T_Tree t = getRight();
                setData(t.getData());
                setLeft(t.getLeft());
                setRight(t.getRight());
            }
            else if (getData().compareTo(data)==0&&!isLeaf()){

                Ltree.suppress2(data,this);
            }
            else if (getRight().getData().compareTo(data)==0&&getRight().isLeaf()){

                node.setData(getData());
                T_Tree t = getLeft();
                setData(t.getData());
                setRight(t.getRight());
                setLeft(t.getLeft());
            }
            else if (getData().compareTo(data)>0){
                Ltree.suppress2(data,node);
                equilibrate();
            }
            else if (getData().compareTo(data)<0){
                Rtree.suppress2(data,node);
                equilibrate();
            }
        }
    }

    public void SegmentsContainPoint(Point p, ArrayList<Segment> Cp, ArrayList<Segment> Lp){
        if(!isEmpty()){
            System.out.println(getData());
        if (getData().contain(p)){

            if (isLeaf()){


                if(getData().getLower_point().isEqualTo(p))
                    Lp.add(getData());
                else
                    Cp.add(getData());
            }
            else {
                getRight().SegmentsContainPoint(p,Cp,Lp);
                getLeft().SegmentsContainPoint(p,Cp,Lp);
            }
        }
        else {

            if (getData().getCurrentPoint(p.getY())<p.getX()){
                getRight().SegmentsContainPoint(p,Cp,Lp);
            }
            //else {                                            pas de else?
            getLeft().SegmentsContainPoint(p,Cp,Lp);

        }
    }}

    public void Nleft(Point p,Segment sl){  //les cas ou ça passe par le point?
        if (isLeaf())
            sl=getData();
        else {

            if (getData().getCurrentPoint(p.getY()) < p.getX() && getRight().getData().getCurrentPoint(p.getY()) > p.getX())
                sl = getData();
            else if (getData().getCurrentPoint(p.getY()) > p.getX())
                Nleft(p, sl);
        }
    }

    public void Nright(Point p,Segment sr){  //les cas ou ça passe par le point?
        if (isLeaf())
            sr=getData();
        else {

            if (getData().getCurrentPoint(p.getY()) > p.getX() && getLeft().getData().getCurrentPoint(p.getY()) < p.getX())
                sr = getData();
            else if (getData().getCurrentPoint(p.getY()) < p.getX())
                Nright(p,sr);

        }
    }




    public void print(int space) {   // a nettoyer quand fini
        if (!isEmpty()) {

            space+=5;
            Rtree.print(space);
            System.out.print("\n");
            for(int i = 1;i<space;i++){
                System.out.print(" ");
            }

            System.out.println(data +" => "+space/5);

            Ltree.print(space);

        }
    }
        public void printleaves(){   // a nettoyer quand fini
            if (!isEmpty()) {
                //System.out.println("left");
                Ltree.printleaves();
                //System.out.println("remonte");
                if(isLeaf())
                    System.out.println(data );

                //System.out.println("right");
                Rtree.printleaves();

            }
        }
    }

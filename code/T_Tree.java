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
        if (data == null)
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
        float y=data.getUpper_point().getY();
        float x=data.getUpper_point().getX();
        insert2(T, data, x, y);
    }

    public void reinsert (Segment data, float x, float y){
        T_Tree T = new T_Tree();
        System.out.println("-------------------------------- reinsert  --------------------------------------");
        insert2(T, data,x, y);
    }



    public void insert2(T_Tree node,Segment data,float x,float y){



        System.out.println("-------------------------------- insert  --------------------------------------");
        print(0,y);
        System.out.println("à insert = "+data);
        if (getData()!=data){
            if (getData()==null){
                insertEmpty(data);
                //System.out.println("---------");
                }

            else {
                if (getData().compareTo(data,x,y)<0){  //node.data<data
                    //System.out.println(getData().getUpper_point().getX()+"<"+data.getUpper_point().getX());

                    if (getLeft().isEmpty()&& getRight().isEmpty()) //feuille
                        {getLeft().insert2(node,getData(),x,y);
                        node.setData(data);}


                    getRight().insert2(node,data,x,y);
                    equilibrate();
                }
                else if (getData().compareTo(data,x,y)>0){   //node.data>data
                    //System.out.println(getData().getUpper_point().getX()+">"+data.getUpper_point().getX());
                    if (getLeft().isEmpty()&& getRight().isEmpty()){//feuille
                        getRight().insert2(node,getData(),x,y);
                        setData(data);

                        }

                    node=this;
                    getLeft().insert2(node,data,x,y);
                    equilibrate();  }
                else {
                    //System.out.println(getData()+";"+data);
                    //System.out.println("aled");
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


        //System.out.println("droit ="+getRight());
        //System.out.println("gauche ="+getLeft());
        if (getRight().isEmpty()&& getLeft().isEmpty())
            return true;
        return false;
    }

    public void suppress (Segment data,float x,float y){

        T_Tree T = new T_Tree();
        suppress2(data,T,x,y);
    }

    public void suppress2 (Segment data,T_Tree node,float x,float y){

        System.out.println("getdata ="+getData());
        System.out.println("data ="+data);
        System.out.println("node ="+node.getData());
        System.out.println("compare ="+getData().compareTo(data,x,y));
        System.out.println("y ="+y);
        print(0,y);

        System.out.println("-------------------------------- suppress  --------------------------------------");
        System.out.println("à supprimer = "+data);

        if (!isEmpty()){

             if (isLeaf()){

                 System.out.println("-------------------------------- feuille --------------------------------------");
                 System.out.println("getdata ="+getData());
                 System.out.println("data ="+data);
                setData(null);
                 System.out.println("-------------------------------- feuille --------------------------------------");


            }
            else if (getLeft().getData().compareTo(data,x,y)==0&&getLeft().isLeaf()) {              //

                 T_Tree t = getRight();
                 setData(t.getData());
                 setLeft(t.getLeft());
                 setRight(t.getRight());
             }
           else if (getData().compareTo(data,x,y)==0&&!isLeaf()){

                Ltree.suppress2(data,this,x,y);

            }
            else if (getRight().getData().compareTo(data,x,y)==0&&getRight().isLeaf()){             //

                node.setData(getData());
                T_Tree t = getLeft();
                setData(t.getData());
                setRight(t.getRight());
                setLeft(t.getLeft());

            }
            else if (getData().compareTo(data,x,y)>0 ){
/*
                 System.out.println("");
                 System.out.println("On va à gauche");
                 System.out.println("ici =>"+getData().compareTo(data,x,y));
                 System.out.println(getData());
                 System.out.println(data);
                 System.out.println("");*/


                Ltree.suppress2(data,node,x,y);

            }
            else if (getData().compareTo(data,x,y)<0){          //
                //System.out.println("bah oui");
                Rtree.suppress2(data,node,x,y);

            }
            equilibrate();
        }
    }


    public void SegmentsContainPoint(Point p, ArrayList<Segment> Cp, ArrayList<Segment> Lp) {
        if (!isEmpty()) {
            //print(0);

            if (getData().contain(p)) {
                if (isLeaf()) {


                    if (getData().getLower_point().isEqualTo(p))
                        Lp.add(getData());
                    else
                        Cp.add(getData());
                } else {
                    getRight().SegmentsContainPoint(p, Cp, Lp);
                    getLeft().SegmentsContainPoint(p, Cp, Lp);
                }
            } else {

                if (getData().getCurrentPoint(p.getY()) < p.getX()) {
                    getRight().SegmentsContainPoint(p, Cp, Lp);
                }
                //else {                                            pas de else?
                getLeft().SegmentsContainPoint(p, Cp, Lp);

            }
        }
    }

    public Segment NleftP(Point p,Segment little){
        if (isEmpty())
                return null;
        else if(isLeaf())
            return little;

        else {

            //lorsque les segments passent pas par le point
            //System.out.println(getData().getCurrentPoint(p.getY()));
            //System.out.println((p.getY()));

            if(getData().getCurrentPoint(p.getY())>p.getX()){
                Ltree.NleftP(p,little);
            }
            else if (getData().getCurrentPoint(p.getY())<p.getX()){
                Rtree.NleftP(p,getData());
            }
        }
        return null;
    }

    public Segment NrightP(Point p,Segment little){
        if (isEmpty())
            return null;
        else if(isLeaf())
            return little;

        else {

            //lorsque les segments passent pas par le point
            //System.out.println(getData().getCurrentPoint(p.getY()));
            //System.out.println((p.getY()));

            if(getData().getCurrentPoint(p.getY())>p.getX()){ //
                Ltree.NrightP(p,getData());
            }
            else if (getData().getCurrentPoint(p.getY())<p.getX()){ //
                Rtree.NrightP(p,little);
            }
        }
        return null;
    }

    public Segment succ(Segment d,Segment succ, float x, float y) {
        //System.out.println("segment = "+d);
        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().compareTo(d,x,y)>0) {
            return getData();
        }
        else if (isLeaf() && getData().compareTo(d,x,y)<=0){
            return succ;
        }
        else if (getData().compareTo(d,x,y)>0){
            return Ltree.prev(d,getData(),x,y);
        }
        else if (getData().compareTo(d,x,y)<=0){
            return Rtree.prev(d,prev,x,y);
        }


        return succ;
    }

    public Segment prev(Segment d,Segment prev, float x, float y) {

        if (isEmpty()) {
            return null;
        }
        else if (isLeaf() && getData().compareTo(d,x,y)<0){
            return getData();
        }
        else if (isLeaf() && getData().compareTo(d,x,y)>=0){
            return prev;
        }
        else if (getData().compareTo(d,x,y)<0){
            return Rtree.prev(d,getData(),x,y);
        }
        else if (getData().compareTo(d,x,y)>=0){
            return Ltree.prev(d,prev,x,y);
        }


        return prev;
    }

    public Segment searchMin() {
        if (isEmpty())
            return null;
        else if (getLeft().isEmpty())
            return getData();
        else 	return getLeft().searchMin();
    }

    public Segment searchMax() {
        if (isEmpty())
            return null;
        else if (getRight().isEmpty())
            return getData();
        else 	return getRight().searchMax();
    }



    public void print(int space,float y) {   // a nettoyer quand fini
        if (!isEmpty()) {

            space+=5;
            Rtree.print(space,y);
            System.out.print("\n");
            for(int i = 1;i<space;i++){
                System.out.print(" ");
            }

            System.out.println(data+" ("+data.getCurrentPoint(y)+""+y+")  => "+space/5);

            Ltree.print(space,y);

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

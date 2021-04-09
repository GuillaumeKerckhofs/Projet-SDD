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
        insert2(T, data,x, y);
    }



    public void insert2(T_Tree node,Segment data,float x,float y){

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
        if (getRight().isEmpty()&& getLeft().isEmpty())
            return true;
        return false;
    }
/*
    public boolean search(Segment data) {
        if (isEmpty())
            return false;
        else	if (getData().compareTo(data) < 0)
            return getRight().search(data);
        else 	if (getData().compareTo(data) > 0)
            return getLeft().search(data);
        else 	return true;
    }
*/
    public void suppress (Segment data,float x,float y){
        T_Tree T = new T_Tree();
        suppress2(data,T,x,y);
    }

    public void suppress2 (Segment data,T_Tree node,float x,float y){

        if (!isEmpty()){

             if (isLeaf()){
                setData(null);

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
            else if (getData().compareTo(data,x,y)>0){          //
                Ltree.suppress2(data,node,x,y);
                equilibrate();
            }
            else if (getData().compareTo(data,x,y)<0){          //
                Rtree.suppress2(data,node,x,y);
                equilibrate();
            }
        }
    }


    public void SegmentsContainPoint(Point p, ArrayList<Segment> Cp, ArrayList<Segment> Lp) {
        if (!isEmpty()) {
            print(0);
            System.out.println(isEmpty());
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

    public void NleftP(Point p,Segment sl){  //les cas ou ça passe par le point?
        if (isLeaf())
            sl=getData();
        else {

            //lorsque les segments passent pas par le point
            if (getData().getCurrentPoint(p.getY()) < p.getX() && getRight().getData().getCurrentPoint(p.getY()) > p.getX())
                sl = getData();
            else if (getData().getCurrentPoint(p.getY()) > p.getX())
                NleftP(p, sl);

        }
    }

    public void NrightP(Point p,Segment sr){  //les cas ou ça passe par le point?
        if (isLeaf())
            sr=getData();
        else {

            if (getData().getCurrentPoint(p.getY()) > p.getX() && getLeft().getData().getCurrentPoint(p.getY()) < p.getX())
                sr = getData();
            else if (getData().getCurrentPoint(p.getY()) < p.getX())
                NrightP(p,sr);

        }
    }

    public Segment LeftMostSegment1(ArrayList<Segment> Up,ArrayList<Segment> Cp,float x,float y){
        ArrayList<Segment> sum=Up;

        if (!Cp.isEmpty()){
            for (Segment segment:Cp)
                sum.add(segment);}
        Segment min = searchMin();
        System.out.println("ici min =>"+min);


        return leftMostSegment2(min,sum,x,y);
        }

    public Segment leftMostSegment2(Segment min,ArrayList<Segment> sum,float x,float y){  //plus a gauche donc
        System.out.println("ici min 2=>"+min);
        //System.out.println("ici 2 =>"+searchSucc(min,x,y));
        if (sum.contains(min))
            return min;
        else {System.out.println("searchMin ==> "+searchSucc(min,x,y));
        return leftMostSegment2(searchSucc(min,x,y),sum,x,y);
                }
    }

    public Segment RightMostSegment1(ArrayList<Segment> Up,ArrayList<Segment> Cp,float x,float y){
        ArrayList<Segment> sum=Up;

        if (!Cp.isEmpty())
            for (Segment segment:Cp)
                sum.add(segment);
        Segment max = searchMax();

        return rightMostSegment2(max,sum,x,y);
    }

    public Segment rightMostSegment2(Segment max,ArrayList<Segment> sum,float x,float y){
        System.out.println("");
        System.out.println("////////////////////////////////////");
        System.out.println("");

        System.out.println("Max ==> "+max);
        System.out.println("sum ==> "+sum);

        if (sum.contains(max))
            return max;
        else {System.out.println("searchMax ==> "+searchPrev(max,x,y));
            return rightMostSegment2(searchPrev(max,x,y),sum,x,y);}

    }

    public Segment searchSucc(Segment d,float x,float y) {
        //System.out.println("succedefsfsf ="+succ(d,null,x,y));
        return succ(d,x,y);
    }

    private Segment succ(Segment d,float x,float y) {
        //System.out.println("segment = "+d);
        if (isEmpty()) {
            return null;
        }
        else if (isLeaf()){
            return getData();
        }
        else if (getData().compareTo(d,x,y) == 0){
                //System.out.println("pouet pouet 2");
            return getRight().succ(d,x,y);
        }

        else if (getData().compareTo(d,x,y) > 0){
                //System.out.println("pouet pouet 3");
            return getLeft().succ(d,x,y);
        }
        else if (getData().compareTo(d,x,y) <0){
            return getRigt().suuc(d,x,y);
        }
            /*else if (getRight().isEmpty()){
                //System.out.println("pouet pouet 4");
                return segment;}
            else    return getRight().searchMin();*/
    }

    public Segment searchPrev(Segment d,float x,float y) {

        return prev(d,x,y);
    }

    private Segment prev(Segment d,float x,float y) {

        if (isEmpty()) {
            return null;
        }
        if (isLeaf()){
            return getData();
        }
        else if (getData().compareTo(d,x,y) < 0 && Rtree.getData().compareTo(d,x,y)==0 && Rtree.getLeft().isLeaf()){
            return Ltree.prev(d,x,y);
        }
        else if (getData().compareTo(d,x,y) < 0){
            System.out.println("D ="+getData());
            System.out.println("Data ="+d);
            System.out.println("D >d");
            System.out.println("");
            return getRight().prev(d,x,y);
        }

        else if (getData().compareTo(d,x,y) > 0 /*&& Rtree.getData().compareTo(d,x,y)>=0*/){
            System.out.println("D ="+getData());
            System.out.println("Data ="+d);
            System.out.println("D <data");
            System.out.println("");
            return getLeft().prev(d,x,y);
        }
        else if (getData().compareTo(d,x,y)==0){
            return getLeft().prev(d,x,y);
        }

        /*else if (getLeft().isEmpty())
            return segment;
        else return getLeft().searchMax();*/
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

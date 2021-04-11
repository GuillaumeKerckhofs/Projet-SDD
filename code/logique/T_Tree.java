package code.logique;

import java.util.ArrayList;

public class T_Tree {
    private Segment data;
    private T_Tree Ltree;
    private T_Tree Rtree;
    private int height;

    /**
    Constructeur d'un arbre T
    @param data valeur de la racine
    @param Ltree arbre gauche de l'arbre
    @param Rtree arbre droit de l'arbre
     */

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


    /**

    @return boolean qui indique si la valeur de la racine est null
     */

    public boolean isEmpty() {
        if (data == null)
            return true;
        else
            return false;
    }

    /**
    calcule la hauteur de l'arbre
     */

    public void height() {
        if (isEmpty())
            height = 0;
        else
            height = 1 + Math.max(getLeft().getHeight(),
                    getRight().getHeight());
    }

    /**
    calcule la balance de l'arbre
     */

    public int balance() {
        if (isEmpty())
            return 0;
        else
            return getRight().getHeight() - getLeft().getHeight();
    }

    /**
    initialise les objets utile à l'insertion
    @param data le segment à inserer
     */
    public void insert (Segment data){
        T_Tree T = new T_Tree();
        float y=data.getUpper_point().getY();
        float x=data.getUpper_point().getX();
        insert2(T, data, x, y);
    }
    /**
        initialise les objets utile lors de la réinsertion
        @param data le segment à inserer
        @param x valeur x du Point p
        @param y valeur y du Point p
         */
    public void reinsert (Segment data, float x, float y){
        T_Tree T = new T_Tree();
        insert2(T, data,x, y);
    }

    /**
    insert un segment dans l'arbre
    @param node arbre vide
    @param data le segment à inserer
    @param x valeur x du Point p
    @param y valeur y du Point p
         */

    public void insert2(T_Tree node,Segment data,float x,float y){
        if (getData()!=data){
            if (getData()==null){
                insertEmpty(data);
                }

            else {
                if (getData().compareTo(data,x,y)<0){
                    if (getLeft().isEmpty()&& getRight().isEmpty())
                        {getLeft().insert2(node,getData(),x,y);
                        node.setData(data);}
                    getRight().insert2(node,data,x,y);
                    equilibrate();
                }
                else if (getData().compareTo(data,x,y)>0){
                    if (getLeft().isEmpty()&& getRight().isEmpty()){
                        getRight().insert2(node,getData(),x,y);
                        setData(data);
                        }

                    node=this;
                    getLeft().insert2(node,data,x,y);
                    equilibrate();
                }
            }
        }
    }
/**
    insert dans un arbre vide
    @param data le segment à inserer
         */

    public void insertEmpty(Segment data) {

        this.setData (data);
        this.setLeft (new T_Tree());
        this.setRight (new T_Tree());
        this.height = 1;
    }
/**
 * equilibre l'arbre
 */

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

    /**
     * effectue une rotation gauche lors de l'équilibrage
     * @param T
     */

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

    /**
     * effectue une rotation gauche lors de l'équilibrage
     * @param T
     */

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

    /**
     *
     * @return boolean si l'arbre est une feuille
     */
    public boolean isLeaf (){
        if (getRight().isEmpty()&& getLeft().isEmpty())
            return true;
        return false;
    }

    /**
     initialise les objets utile lors de la suppression
     @param data le segment à supprimer
     @param x valeur x du Point p
     @param y valeur y du Point p
     */
    public void suppress (Segment data,float x,float y){

        T_Tree T = new T_Tree();
        suppress2(data,T,x,y);
    }

    /**
     supprime un segment de l'arbre
     @param node arbre vide
     @param data le segment à supprimer
     @param x valeur x du Point p
     @param y valeur y du Point p
     */

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
            else if (getData().compareTo(data,x,y)>0 ){
                Ltree.suppress2(data,node,x,y);

            }
            else if (getData().compareTo(data,x,y)<0){
                Rtree.suppress2(data,node,x,y);

            }
            equilibrate();
        }
    }

    /**
     regarde les segments qui contiennent le point P , Lp sera les segments dont P est le lower point, Cp le segment qui contiennent P (pas upper point ni lower point)
     @param p le point que dont on verifie l'appartenance à la droite
     @param Lp arraylist de segment vide
     @param Cp arraylist de segment vide
     */

    public void SegmentsContainPoint(Point p, ArrayList<Segment> Cp, ArrayList<Segment> Lp) {
        if (!isEmpty()) {
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
            }
            else {
                if (getData().getCurrentPoint(p.getY()) < p.getX()) {
                    getRight().SegmentsContainPoint(p, Cp, Lp);
                }
                else if (getData().getCurrentPoint(p.getY()) > p.getX()) {
                    getLeft().SegmentsContainPoint(p, Cp, Lp);
                }
            }
        }
    }

    /**
     * cherche le voisin gauche d'un point p
     * @param p le point
     * @param little un segment null
     * @return le segment trouvé
     */

    public Segment NleftP(Point p,Segment little){

        float y=p.getY();
        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().getCurrentPoint(y)<p.getX()) {
            return getData();

        }
        else if (isLeaf() && getData().getCurrentPoint(y)>p.getX()){
            return little;
        }
        else if (getData().getCurrentPoint(y)<p.getX()){
            return Rtree.NleftP(p,getData());
        }
        else if (getData().getCurrentPoint(y)>p.getX()){
            return Ltree.NleftP(p,little);
        }


        return little;
    }

    /**
     * cherche le voisin droit d'un point p
     * @param p le point
     * @param little un segment null
     * @return le segment trouvé
     */

    public Segment NrightP(Point p,Segment little){

        float y=p.getY();
        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().getCurrentPoint(y)>p.getX()) {
            return getData();

        }
        else if (isLeaf() && getData().getCurrentPoint(y)<p.getX()){
            return little;
        }
        else if (getData().getCurrentPoint(y)<p.getX()){
            return Rtree.NrightP(p,little);
        }
        else if (getData().getCurrentPoint(y)>p.getX()){
            return Ltree.NrightP(p,getData());
        }


        return little;
    }

    /**
     * recherche le segment qui suit un segment donné dans l'arbre T
     * @param d le segment donné
     * @param succ segment null
     * @param x parametre x du point P
     * @param y parametre y du point P
     * @return le segment trouvé
     */

    public Segment succ(Segment d,Segment succ, float x, float y) {

        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().compareTo(d,x,y)>0) {

            return getData();

        }
        else if (isLeaf() && getData().compareTo(d,x,y)<=0){

            return succ;
        }
        else if (getData().compareTo(d,x,y)>0){

            return Ltree.succ(d,getData(),x,y);
        }
        else if (getData().compareTo(d,x,y)<=0){

            return Rtree.succ(d,succ,x,y);
        }


        return succ;
    }

    /**
     * recherche le segment qui précède un segment donné dans l'arbre T
     * @param d le segment donné
     * @param prev segment null
     * @param x parametre x du point P
     * @param y parametre y du point P
     * @return le segment trouvé
     */
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

    /**
     * affiche l'arbre
     * @param space
     * @param y
     */

    public void print(int space,float y) {
        if (!isEmpty()) {

            space+=5;
            Rtree.print(space,y);
            System.out.print("\n");
            for(int i = 1;i<space;i++){
                System.out.print(" ");
            }

            System.out.println(data+" ("+data.getCurrentPoint(y)+";"+y+")  => "+space/5);

            Ltree.print(space,y);

        }
    }
    /**
     * affiche les feuilles de l'arbre
     */
        public void printleaves(){
            if (!isEmpty()) {

                Ltree.printleaves();
                if(isLeaf())
                    System.out.println(data );

                Rtree.printleaves();

            }
        }
    }

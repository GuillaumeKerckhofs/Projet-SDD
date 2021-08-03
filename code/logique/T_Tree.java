package code.logique;

import java.util.ArrayList;

public class T_Tree {
    private Segment data;
    private T_Tree ltree;
    private T_Tree rtree;
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
        ltree = l;
    }
    public T_Tree getLeft() {
        return ltree;
    }

    public void setRight(T_Tree r) { rtree = r; }
    public T_Tree getRight() {
        return rtree;
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
    /*public void insert_init(Segment data){
        T_Tree T = new T_Tree();
        double y=data.getUpper_point().getY();
        double x=data.getUpper_point().getX();
        insert(T, data, x, y);
    }*/

    public void insert_init (Segment data, Point point){
    /**
        initialise les objets utile lors de la réinsertion
        @param data le segment à inserer
        @param x valeur x du Point p
        @param y valeur y du Point p
         */
        T_Tree T = new T_Tree();
        double x=point.getX();
        double y=point.getY();
        insert(T, data,x, y);
    }

    /**
    insert un segment dans l'arbre
    @param node arbre vide
    @param data le segment à inserer
    @param x valeur x du Point p
    @param y valeur y du Point p
         */

    private void insert(T_Tree node, Segment data, double x, double y){
        if (getData()!=data){
            if (getData()==null){
                insertEmpty(data);
                }

            else {
                if (getData().compareTo(data,x,y)<0){
                    if (getLeft().isEmpty()&& getRight().isEmpty())
                        {getLeft().insert(node,getData(),x,y);
                        node.setData(data);}
                    getRight().insert(node,data,x,y);
                    equilibrate();
                }
                else if (getData().compareTo(data,x,y)>0){
                    if (getLeft().isEmpty()&& getRight().isEmpty()){
                        getRight().insert(node,getData(),x,y);
                        setData(data);
                        }

                    node=this;
                    getLeft().insert(node,data,x,y);
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

    public void suppress_init(Segment data, double x, double y){

    /**
     initialise les objets utile lors de la suppression
     @param data le segment à supprimer
     @param x valeur x du Point p
     @param y valeur y du Point p
     */

        T_Tree T = new T_Tree();
        suppress(data,T,x,y);
    }


    /**
     supprime un segment de l'arbre
     @param node arbre vide
     @param data le segment à supprimer
     @param x valeur x du Point p
     @param y valeur y du Point p
     */

    private void suppress (Segment data,T_Tree node,double x,double y){
        if (!isEmpty()){

             if (isLeaf()){
                setData(null);
            }
            else if (getLeft().getData().compareTo(data,x,y)==0&&getLeft().isLeaf()) {

                 T_Tree t = getRight();
                 setData(t.getData());
                 setLeft(t.getLeft());
                 setRight(t.getRight());
             }
           else if (getData().compareTo(data,x,y)==0&&!isLeaf()){

                ltree.suppress(data,this,x,y);

            }
            else if (getRight().getData().compareTo(data,x,y)==0&&getRight().isLeaf()){

                node.setData(getData());
                T_Tree t = getLeft();
                setData(t.getData());
                setRight(t.getRight());
                setLeft(t.getLeft());

            }
            else if (getData().compareTo(data,x,y)>0 ){
                ltree.suppress(data,node,x,y);

            }
            else if (getData().compareTo(data,x,y)<0){
                rtree.suppress(data,node,x,y);

            }
            equilibrate();
        }
    }

    /**
     regarde les segments qui contiennent le point P , Lp sera les segments dont P est le lower point, Cp le segment qui contient P (pas upper point ni lower point)
     @param p le point que dont on verifie l'appartenance à la droite
     @param lp arraylist de segment vide
     @param cp arraylist de segment vide
     */

    public void SegmentsContainPoint(Point p, ArrayList<Segment> cp, ArrayList<Segment> lp) {
        if (!isEmpty()) {
            if (getData().contain(p)) {
                if (isLeaf()) {
                    if (getData().getLower_point().isEqualTo(p))
                        lp.add(getData());
                    else
                        cp.add(getData());
                } else {
                    getRight().SegmentsContainPoint(p, cp, lp);
                    getLeft().SegmentsContainPoint(p, cp, lp);
                }
            }
            else {
                if (getData().getCurrentPoint(p.getY()) < p.getX()) {
                    getRight().SegmentsContainPoint(p, cp, lp);
                }
                else if (getData().getCurrentPoint(p.getY()) > p.getX()) {
                    getLeft().SegmentsContainPoint(p, cp, lp);
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

    public Segment nleftP(Point p, Segment little){

        double y=p.getY();
        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().getCurrentPoint(y)<p.getX()) {
            return getData();

        }
        else if (isLeaf() && getData().getCurrentPoint(y)>p.getX()){
            return little;
        }
        else if (getData().getCurrentPoint(y)<p.getX()){
            return rtree.nleftP(p,getData());
        }
        else if (getData().getCurrentPoint(y)>p.getX()){
            return ltree.nleftP(p,little);
        }


        return little;
    }

    /**
     * cherche le voisin droit d'un point p
     * @param p le point
     * @param little un segment null
     * @return le segment trouvé
     */

    public Segment nrightP(Point p, Segment little){

        double y=p.getY();
        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().getCurrentPoint(y)>p.getX()) {
            return getData();

        }
        else if (isLeaf() && getData().getCurrentPoint(y)<p.getX()){
            return little;
        }
        else if (getData().getCurrentPoint(y)<p.getX()){
            return rtree.nrightP(p,little);
        }
        else if (getData().getCurrentPoint(y)>p.getX()){
            return ltree.nrightP(p,getData());
        }


        return little;
    }

    public Segment succ(Segment d,Segment succ, double x, double y) {
    /**
     * recherche le segment qui suit un segment donné dans l'arbre T
     * @param d le segment donné
     * @param succ segment null
     * @param x parametre x du point P
     * @param y parametre y du point P
     * @return le segment trouvé
     */

        if (isEmpty()) {
            return null;
        } else if (isLeaf() && getData().compareTo(d,x,y)>0) {

            return getData();

        }
        else if (isLeaf() && getData().compareTo(d,x,y)<=0){

            return succ;
        }
        else if (getData().compareTo(d,x,y)>0){

            return ltree.succ(d,getData(),x,y);
        }
        else if (getData().compareTo(d,x,y)<=0){

            return rtree.succ(d,succ,x,y);
        }


        return succ;
    }

    public Segment prev(Segment d,Segment prev, double x, double y) {
    /**
     * recherche le segment qui précède un segment donné dans l'arbre T
     * @param d le segment donné
     * @param prev segment null
     * @param x parametre x du point P
     * @param y parametre y du point P
     * @return le segment trouvé
     */

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
            return rtree.prev(d,getData(),x,y);
        }
        else if (getData().compareTo(d,x,y)>=0){
            return ltree.prev(d,prev,x,y);
        }


        return prev;
    }

    /**
     * affiche l'arbre
     */


    public Segment searchMax() {
        if (isEmpty())
            return null;
        else if (getRight().isEmpty())
            return getData();
        else 	return getRight().searchMax();
    }



    public void print(int space,double y) {
        if (!isEmpty()) {

            space+=5;
            rtree.print(space,y);
            System.out.print("\n");
            for(int i = 1;i<space;i++){
                System.out.print(" ");
            }

            System.out.println(data+" ("+data.getCurrentPoint(y)+";"+y+")  => "+space/5);

            ltree.print(space,y);

        }
    }
    /**
     * affiche les feuilles de l'arbre
     */
        public void printleaves(){
            if (!isEmpty()) {

                ltree.printleaves();
                if(isLeaf())
                    System.out.println(data );

                rtree.printleaves();

            }
        }
    }

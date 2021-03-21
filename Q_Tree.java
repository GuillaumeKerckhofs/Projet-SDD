public class Q_Tree{
    private Q_Node root;

    public Q_Tree(Q_Node root){
        this.root=root;
    }

    public Q_Tree(Point point,Segment segment){
        this(new Q_Node(point,segment));
    }

    public Q_Tree(){this(null);}



    /*public void insertionEmpty(Point point,Segment segment){
        this.point=point;
        this.addSegment(segment);
    } */

    public void insertion(Q_Node current,Point point,Segment segment){
        if(current==null){
            current=new Q_Node(point,segment);
        }
        else{
            if(point.isEqualTo(current.getPoint())){
                current.addSegment(segment);
            }
            else if(point.smallerThan(current.getPoint())){
                insertion(current.getLeft(),point,segment);
                Equilibrate(current);
            }
            else{
                insertion(current.getRight(),point, segment);
                Equilibrate(current);
            }
        }
    }

    public void Equilibrate(Q_Node node){
        if(node.Bal()==2){
            if(node.getRight().Bal()>=0){
                RotateL(node);
            }
            else{
                RotateR(node.getRight());
                RotateL(node);
            }
        }
        else if(node.Bal()==-2){
            if(node.getLeft().Bal()<=0){
                RotateR(node);
            }
            else{
                RotateL(node.getLeft());
                RotateR(node);
            }
        }
        else{
            node.Height();
        }
    }

    public void RotateL(Q_Node node){
        Q_Node tmp=node;
        node=node.getRight();
        tmp.setRight(node.getLeft());
        node.setLeft(tmp);
        tmp.Height();
        node.Height();
    }

    public void RotateR(Q_Node node){
        Q_Node tmp=node;
        node=node.getLeft();
        tmp.setLeft(node.getRight());
        node.setRight(tmp);
        tmp.Height();
        node.Height();
    }

    public Q_Node removeNextEvent(Q_Node node){
        if(node.getLeft()!=null) {
            removeNextEvent(node.getLeft());
            Equilibrate(node);
        }
        Q_Node tmp=node;
        else{
            remove(node);
        }
        return tmp;
    }

    public void remove(Q_Node node){
        if (node.isLeaf()){
            node=null;
        }
        else{
            node=node.getLeft();
        }
    }
}
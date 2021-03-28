package code;

public class Q_Tree {
    private Q_Node root;
    private Q_Node lastRemoved;

    public Q_Tree(Q_Node root) {
        this.root = root;
        this.lastRemoved=new Q_Node();
    }

    public Q_Tree(Point point, Segment segment) {
        this(new Q_Node(point, segment,new Q_Node(),new Q_Node()));
    }

    public Q_Tree() {
        this(null);
    }


    /*public void insertionEmpty(Point point,Segment segment){
        this.point=point;
        this.addSegment(segment);
    } */
    public void startInsertion(Point point, Segment segment) {
        insertion(this.root, point, segment);
    }

    public void insertion(Q_Node current, Point point, Segment segment) {
        if (current.isEmpty()) {
            current.setPoint(point);
            //current.addSegment(segment);
            current.setLeft(new Q_Node());
            current.setRight(new Q_Node());
        } else {
            if (point.isEqualTo(current.getPoint())) {
                //current.addSegment(segment);
            } else if (point.smallerThan(current.getPoint())) {
                insertion(current.getLeft(), point, segment);
                Equilibrate(current);
            } else {
                insertion(current.getRight(), point, segment);
                Equilibrate(current);
            }
        }
    }

    public void Equilibrate(Q_Node node) {
        if (node.Bal() == 2) {
            if (node.getRight().Bal() >= 0) {
                RotateL(node);
            } else {
                RotateR(node.getRight());
                RotateL(node);
            }
        } else if (node.Bal() == -2) {
            if (node.getLeft().Bal() <= 0) {
                RotateR(node);
            } else {
                RotateL(node.getLeft());
                RotateR(node);
            }
        } else {
            node.Height();
        }
    }

    public void RotateL(Q_Node node) {
        Q_Node tmp =new Q_Node();
        tmp.changeNode(node);
        node.changeNode(node.getRight());
        if (tmp==this.root){
            this.root.changeNode(node);
        }
        tmp.setRight(node.getLeft());
        node.setLeft(tmp);
        tmp.Height();
        node.Height();
    }

    public void RotateR(Q_Node node) {
        Q_Node tmp =new Q_Node();
        tmp.changeNode(node);
        node.changeNode(node.getLeft());
        if (tmp==this.root){
            this.root=node;
        }
        tmp.setLeft(node.getRight());
        node.setRight(tmp);
        tmp.Height();
        node.Height();
    }

    public void removeNextEvent() {
       remove(this.root);
    }

    public void remove(Q_Node node) {
        if (!node.getLeft().isEmpty()) {
            remove(node.getLeft());
            Equilibrate(node);
        }
        else{
            removeNode(node);
        }
    }

    public void removeNode(Q_Node node) {

        // Save the removed Node
        lastRemoved.changeNode(node);

        //replace the node by is right son, and change the root if the removed node is the root
        Q_Node nodeR=node.getRight();
        if(node==this.root){
            this.root.changeNode(nodeR);
        }
        node.changeNode(nodeR);
    }

    public void print() {
        printTree(root, 0);
    }

    public static void printTree(Q_Node node, int space) {
        if (node==null){
            return;
        }

        space+=5;

        printTree(node.getRight(),space);
        System.out.print("\n");
        for(int i = 1;i<space;i++){
            System.out.print(" ");
        }
        System.out.print(node.getPoint());
        printTree(node.getLeft(),space);
    }

    public Q_Node getRoot() {
        return root;
    }

    public Q_Node getLastRemoved() {
        return lastRemoved;
    }

    public void setRoot(Q_Node root) {
        this.root = root;
    }
}
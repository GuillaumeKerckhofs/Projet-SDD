public class Q_Tree {
    private Q_Node root;

    public Q_Tree(Q_Node root) {
        this.root = root;
    }

    public Q_Tree(Point point, Segment segment) {
        this(new Q_Node(point, segment));
    }

    public Q_Tree() {
        this(null);
    }


    /*public void insertionEmpty(Point point,Segment segment){
        this.point=point;
        this.addSegment(segment);
    } */
    public void startInsertion(Point point, Segment segment) {
        this.root=insertion(this.root, point, segment);
    }

    public Q_Node insertion(Q_Node current, Point point, Segment segment) {
        if (current == null) {
            return(new Q_Node(point, segment));
        } else {
            if (point.isEqualTo(current.getPoint())) {
                current.addSegment(segment);
                return current;
            } else if (point.smallerThan(current.getPoint())) {
                Q_Node node=insertion(current.getLeft(), point, segment);
                current.setLeft(node);
                Equilibrate(current);
                return current;
            } else {
                Q_Node node=insertion(current.getRight(), point, segment);
                current.setRight(node);
                Equilibrate(current);
                return current;
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
        Q_Node tmp = node;
        node = node.getRight();
        if (tmp==this.root){
            System.out.println(root.getPoint());
            this.root=node;
            System.out.println(root.getPoint());
        }
        tmp.setRight(node.getLeft());
        node.setLeft(tmp);
        tmp.Height();
        node.Height();
    }

    public void RotateR(Q_Node node) {
        Q_Node tmp = node;
        node = node.getLeft();
        if (tmp==this.root){
            System.out.println(root.getPoint());
            this.root=node;
            System.out.println(root.getPoint());
        }
        tmp.setLeft(node.getRight());
        node.setRight(tmp);
        tmp.Height();
        node.Height();
    }

    public Q_Node startRemove() {
        return removeNextEvent(this.root);
    }

    public Q_Node removeNextEvent(Q_Node node) {
        Q_Node tmp = node;
        if (node.getLeft() != null) {
            removeNextEvent(node.getLeft());
            Equilibrate(node);
        }
        else{
            remove(node);
        }
        return tmp;
    }

    public void remove(Q_Node node) {
        if (node.isLeaf()) {
            node = null;
        } else {
            node = node.getLeft();
        }
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

    public void setRoot(Q_Node root) {
        this.root = root;
    }
}
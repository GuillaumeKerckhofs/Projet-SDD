package code.logique;

public class Q_Tree {
    private Q_Node root;
    private Q_Node lastRemoved;

    /**
    Constructeur d'un arbre Q
    @param root racine de l'arbre
     */
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


    /**
    fait appel à la méthode insertion
    @param point a rajouté dans Q
    @param segment a rajouté à la liste s'il existe
     */
    public void startInsertion(Point point, Segment segment) {
        insertion(this.root, point, segment);
    }

    /**
    Ajoute un point et son segment dans Q, On regarde si son x est plus elevé que le point du noeud courrant,
    si c'est égal on regarde si le y est plus élevé, si une des deux conditions est repescté on va à gauche, sinon à droite
    sauf si égalité on rajoute juste le segment dans segments
    quand on est dans un noeud vide, on insert en changeant les variables
    @param current noeud courrant
    @param point point à insert
    @param segment segment lié au point
     */
    public void insertion(Q_Node current, Point point, Segment segment) {
        if (this.root==null){
            this.root=new Q_Node(point,segment,new Q_Node(),new Q_Node());
        }
        else if (current.isEmpty()) {
            current.setPoint(point);
            current.addSegment(segment);
            current.setLeft(new Q_Node());
            current.setRight(new Q_Node());
        } else {
            if (point.isEqualTo(current.getPoint())) {
                current.addSegment(segment);
            } else if (point.smallerThan(current.getPoint())) {
                insertion(current.getLeft(), point, segment);
                Equilibrate(current);
            } else {
                insertion(current.getRight(), point, segment);
                Equilibrate(current);
            }
        }
    }

    /**
    la fonction fait un équilibrage comme vue au cours
    @param node noeud courrant
     */
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

    /**
    effectue une rotation gauche
    vérifie si le noeud courrant est la racine et le change
    @param node
     */
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

    /**
    effectue une rotation droite
    vérifie si le noeud courrant est la racine et le change
    @param node
     */
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

    /**
    fait appel à remove avec le noeud root
     */
    public void removeNextEvent() {
       remove(this.root);
    }

    /**
    cherche le noeud le plus à gauche car c'est le noeud le plus petit dans Q par définition
    on applique removeNode quand on arrive dans une feuille
    @param node noeud courant
     */
    public void remove(Q_Node node) {
        if (!node.getLeft().isEmpty()) {
            remove(node.getLeft());
            Equilibrate(node);
        }
        else{
            removeNode(node);
        }
    }

    /**
    change la variable lastRemoved par le noeud passé en paramètre
    vérifie si le noeud courrant est la racine et le change
    le noeud prend les valeurs de son fils droit
    @param node noeud courrant
     */
    public void removeNode(Q_Node node) {
        lastRemoved.changeNode(node);
        Q_Node nodeR=node.getRight();
        if(node==this.root){
            this.root.changeNode(nodeR);
        }
        node.changeNode(nodeR);
    }

    /**
    print l'arbre et l'affiche sur le terminal
     */
    public void print() {
        printTree(root, 0);
    }

    public static void printTree(Q_Node node, int space) {
        if (node.isEmpty()){
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
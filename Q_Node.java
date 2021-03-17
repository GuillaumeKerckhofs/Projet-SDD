public class Q_Node{
    private Point point;
    private Segment[] segments;
    private Q_Node Right;
    private Q_Node Left;
    private int height;

    public Q_Node(Point point,Segment segment){
        this.point=point;
        this.segments.append(segment);
        this.Right=null;
        this.Left=null;
        this.height=1;
    }

    public Q_Node(){
        this.(null,null,null,null);
    }

    public void Height(){
        if(this.isLeaf()){
            this.height=1;
        }
        else{
            if(Left==null){
                this.Height=Right.getHeight()+1;
            }
            else if(Right==null){
                this.Height=Left.getHeight()+1;
            }
            else{
                this.Height=max(Left.getHeight(),Right.getHeight())+1;
            }
        }
    }

    public int Bal(){
        if(this.isLeaf()){
            return 0;
        }
        else{
            if(Left.getPoint()==null){
                return(Right.getHeight());
            }
            else if(Right.getPoint()==null){
                return(-(Left.getHeight()));
            }
            else{
                return(Right.getHeight()-Left.getHeight());
            }
        }
    }

    public void addSegment(Segment segment) {
        this.segments.append(segment);
    }

    public boolean isLeaf(){ return (Right.getPoint()==null && Left.getPoint()==null);}

    public boolean isEmpty(){ return (this.point==null && this.Right==null && this.Left=null);}


    public void setRight(Q_Node right) {
        this.Right = right;
    }

    public void setLeft(Q_Node left) {
        this.Left = left;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getPoint() {
        return point;
    }

    public Q_Node getRight() {
        return Right;
    }

    public Q_Node getLeft() {
        return Left;
    }

    public int getHeight() {
        return height;
    }
}
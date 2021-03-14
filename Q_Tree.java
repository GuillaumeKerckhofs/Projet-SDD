public class Q_Tree{
    private Point point;
    private Segment[] segments;
    private Q_Tree Tr=null;
    private Q_Tree Tl=null;
    private int height=1;

    public Q_Tree(Point point,Segment segment){
        this.point=point;
        this.segments.append(segment);
    }

    public static void Height(Q_Tree Q){
        if(Q.isLeaf()){
            Q.setHeight(1);
        }
        else{
            Q_Tree tl=Q.getTl();
            Q_Tree tr=Q.getTr();
            if(tl==null){
                Q.setHeight(tr.getHeight()+1);
            }
            else if(tr==null){
                Q.setHeight(tl.getHeight()+1);
            }
            else{
                Q.setHeight(max(tl.getHeight(),tr.getHeight())+1);
            }
        }
    }

    public static int Bal(Q_Tree Q){
        if(Q.isLeaf()){
            return 0;
        }
        else{
            Q_Tree tl=Q.getTl();
            Q_Tree tr=Q.getTr();
            if(tl==null){
                return(tr.getHeight());
            }
            else if(tr==null){
                return(-(tl.getHeight()));
            }
            else{
                return(tr.getHeight()-tl.getHeight());
            }
        }
    }

    public boolean isLeaf(){
        return (this.getTr()==null && this.getTl()==null);
    }

    public void addSegment(Segment segment) {
        this.segments.append(segment);
    }

    public void setTr(Q_Tree tr) {
        this.Tr = tr;
    }

    public void setTl(Q_Tree tl) {
        this.Tl = tl;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBal() {
        return bal;
    }

    public Point getPoint() {
        return point;
    }

    public Q_Tree getTr() {
        return Tr;
    }

    public Q_Tree getTl() {
        return Tl;
    }

    public int getHeight() {
        return height;
    }
}
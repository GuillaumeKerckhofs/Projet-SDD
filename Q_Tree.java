public class Q_Tree{
    private Point point;
    private Segment[] segments;
    private Q_Tree Tr;
    private Q_Tree Tl;
    private int height;

    public Q_Tree(Point point,Segment segment,Q_Tree Tr,Q_Tree Tl){
        this.point=point;
        this.segments.append(segment);
        this.Tr=Tr;
        this.Tl=Tl;
        this.height=1;
    }

    public Q_Tree(){this.(null,null,null,null);}

    public void Height(){
        if(this.isLeaf()){
            this.height=1;
        }
        else{
            Q_Tree tl=this.Tl;
            Q_Tree tr=this.Tr;
            if(tl==null){
                this.Height=tr.getHeight()+1;
            }
            else if(tr==null){
                this.Height=tl.getHeight()+1;
            }
            else{
                this.Height=max(tl.getHeight(),tr.getHeight())+1;
            }
        }
    }

    public int Bal({
        if(this.isLeaf()){
            return 0;
        }
        else{
            Q_Tree tl=this.Tl;
            Q_Tree tr=this.Tr;
            if(tl.getPoint()==null){
                return(tr.getHeight());
            }
            else if(tr.getPoint()==null){
                return(-(tl.getHeight()));
            }
            else{
                return(tr.getHeight()-tl.getHeight());
            }
        }
    }

    public void insertionEmpty(Point point,Segment segment){
        this.point=point;
        this.addSegment(segment);
        this.Tl=new Q_Tree();
        this.Tr=new Q_Tree();
        this.height=1;
    }

    public void insertion(Point point,Segment segment){
        if(this.isEmpty()){
            this.insertionEmpty(point,segment);
        }
        else{
            if(point.isEqualTo(this.point)){
                this.addSegment(segment);
                this.Equilibrate();
            }
            else if(point.smallerThan(this.point)){
                this.Tl.insertion(point,segment);
            }
            else{
                this.Tr.insertion(point, segment);
                this.Equilibrate();
            }
        }
    }

    public void Equilibrate(){
        if(this.Bal()==2){
            if(this.Tr.Bal()>=0){
                this.RotateL();
            }
            else{
                this.Tr.RotateR();
                this.RotateL();
            }
        }
        else if(this.Bal()==-2){
            if(this.Tl.Bal()<=0){
                this.RotateR();
            }
            else{
                this.Tl.RotateL();
                this.RotateR();
            }
        }
        else{
            this.Height();
        }
    }

    public void RotateL(){
        Q_Tree tmp=this;
        
    }

    public void RotateR(){}

    public boolean isLeaf(){
        return (Tr.getPoint()==null && Tl.getPoint()==null);
    }

    public boolean isEmpty(){
        (this.point==null && this.Tr==null && this.Tl=null)
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
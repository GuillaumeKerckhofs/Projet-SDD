public class Comparaison {
    private double precision = 1e-8;

    public boolean equality(double x, double y) {
        if (Math.abs(x - y) < precision) {
            return true;
        }
        return false;
    }

    public boolean smaller(double x, double y) {
        if (!equality(x, y)) {
            if (x < y) {
                return true;
            }
        }
        return false;
    }

    public boolean bigger(double x, double y) {
        if (!equality(x, y)) {
            if (x > y) {
                return true;
            }
        }
        return false;
    }
    public int comparaison(double x,double y){
        if(equality(x,y)){
            return 0;
        }
        else if(smaller(x,y)){
            return -1;    //pas sur si 1 ou -1
        }
        else{
            return 1;
        }
    }
}
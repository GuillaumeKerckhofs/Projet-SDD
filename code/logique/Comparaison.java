package code.logique;

public class Comparaison {

    public static boolean equal(double x, double y) {
        if (Math.abs(x - y) < 1e-8) {
            return true;
        }
        return false;
    }

    public static boolean st(double x, double y) {
        if (!equal(x, y)) {
            if (x < y) {
                return true;
            }
        }
        return false;
    }

    public static boolean bt(double x, double y) {
        if (!equal(x, y)) {
            if (x > y) {
                return true;
            }
        }
        return false;
    }
    public static int cp(double x,double y){
        if(equal(x,y)){
            return 0;
        }
        else if(st(x,y)){
            return -1;    //pas sur si 1 ou -1
        }
        else{
            return 1;
        }
    }
}
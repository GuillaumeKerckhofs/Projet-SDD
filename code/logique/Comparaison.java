package code.logique;

public class Comparaison {

    /**
     compare 2 double par rapport à leurs valeurs avec une erreur de 1e-8 pour savoir si x=y,
     @param x
     @param y
     @return true si x=y, false sinon
     */
    public static boolean equal(double x, double y) {
        if (Math.abs(x - y) < 1e-8) {
            return true;
        }
        return false;
    }

    /**
     compare 2 double par rapport à leurs valeurs avec une erreur de 1e-8 pour savoir si x<y,
     @param x
     @param y
     @return true si x<y, false sinon
     */
    public static boolean st(double x, double y) {
        if (!equal(x, y)) {
            if (x < y) {
                return true;
            }
        }
        return false;
    }

    /**
    compare 2 double par rapport à leurs valeurs avec une erreur de 1e-8 pour savoir si x>y,
    @param x
    @param y
     @return true si x>y, false sinon
            */
    public static boolean bt(double x, double y) {
        if (!equal(x, y)) {
            if (x > y) {
                return true;
            }
        }
        return false;
    }

    /**
     compare 2 double par rapport à leurs valeurs avec une erreur de 1e-8,
     @param x
     @param y
     @return 0 en cas d'agalité, -1 si x est plus petit ou 1 si x est plus grand
     */
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
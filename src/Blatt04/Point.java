package Blatt04;


public class Point {

    private final double x;
    private final double y;
    private final double[] koordinaten;


    public Point(double x, double y){
        this.x = x;
        this.y = y;
        koordinaten = new double[]{x, y};
    }


    public String toString(){
        //return "(" + x + ", " + y + ")";
        return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ")";
    }


    public double get(int i){
        return koordinaten[i];
    }




}

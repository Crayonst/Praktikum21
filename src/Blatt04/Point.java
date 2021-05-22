package Blatt04;
import java.util.ArrayList;


public class Point {

    double x;
    double y;
    double[] koordinaten;


    public Point(double x, double y){
        this.x = x;
        this.y = y;
        koordinaten = new double[]{x, y};
    }


    public double[] getKoordinaten(){
        return koordinaten;
    }


    public boolean equals(Point p){
        for(int i=0; i<koordinaten.length; i++){
            if( !p.equals(koordinaten[i]) ){
                return false;
            }
        }
        return true;
    }


    public String toString(){
        return "(" + x + ", " + y + ")";
    }


    public double[] getAllX(ArrayList<Point> list){
        double[] allX = new double[list.size()];
        for(int i=0; i<list.size(); i++){
            allX[i] = list.get(i).get(0);
        }
        return allX;
    }


    public double get(int i){
        return koordinaten[i];
    }




}

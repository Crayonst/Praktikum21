package Blatt04;
import java.util.ArrayList;

// 试试上传
public class Line {

    Point Startpunkt;
    Point Endpunkt;


    public Line(Point s, Point e){
        Startpunkt = s;
        Endpunkt = e;
    }


    public void invertDirection(){
        Point temp = Startpunkt;
        Startpunkt = Endpunkt;
        Endpunkt = temp;
    }


    public String toString(){
        return Startpunkt + " -- " + Endpunkt;
    }


    public double vektorLength(Point p1, Point p2){
        return Math.sqrt( (p2.get(0)-p1.get(0))*(p2.get(0)-p1.get(0)) + (p2.get(1)-p1.get(1))*(p2.get(1)-p1.get(1)) );
    }


    public int side(Point P){

        double x1 = Startpunkt.get(0);
        double y1 = Startpunkt.get(1);

        double x2 = Endpunkt.get(0);
        double y2 = Endpunkt.get(1);

        double x = P.get(0);
        double y = P.get(1);

        double value = (x2-x1)*(y-y1) - (y2-y1)*(x-x1);
        //auf der Geraden liegt
        if( value==0 ){
            return 0;
        }
        //links
        else if( value>0 ){
            return 1;
        }
        //rechts
        else{
            return -1;
        }


    }


    //找出距离线段最远的点
    public Point getPmax(ArrayList<Point> points){
        Line l = new Line(Startpunkt, Endpunkt);
        Point Pmax = new Point(0,0);

        double x1 = Startpunkt.get(0); double y1 = Startpunkt.get(1);
        double x2 = Endpunkt.get(0); double y2 = Endpunkt.get(1);
        double x = points.get(0).get(0); double y = points.get(0).get(1);


        double max = Math.abs( x1*y2+x*y1+x2*y-x*y2-x2*y1-x1*y );

        for(int i=0; i<points.size(); i++){
            x = points.get(i).get(0); y = points.get(i).get(1);
            if(max<=Math.abs( x1*y2+x*y1+x2*y-x*y2-x2*y1-x1*y )){
                max = Math.abs( x1*y2+x*y1+x2*y-x*y2-x2*y1-x1*y );
                Pmax = points.get(i);
            }
        }
        return Pmax;
    }





}
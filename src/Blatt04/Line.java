package Blatt04;

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


    public double getLength(){
        return Math.sqrt( (Endpunkt.get(0)-Startpunkt.get(0))*(Endpunkt.get(0)-Startpunkt.get(0))
                + (Endpunkt.get(1)-Startpunkt.get(1))*(Endpunkt.get(1)-Startpunkt.get(1)) );
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
}
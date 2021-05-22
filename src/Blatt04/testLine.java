package Blatt04;

public class testLine {
    public static void main(String[] args){

        Point p1 = new Point(0,0);
        Point p2 = new Point(10,0);
        Point p = new Point(1, 1);
        Line l = new Line(p1, p2);

        System.out.println(l.toString());
        System.out.println(l.side(p));
        System.out.println(l.getLength());


        l.invertDirection();
        System.out.println(l.toString());
        System.out.println(l.side(p));
        System.out.println(l.getLength());
    }
}

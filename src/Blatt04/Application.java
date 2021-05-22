package Blatt04;
import java.util.ArrayList;

public class Application {


    public static void main(String[] args) throws Exception {

        int len = args.length;
        try {
            //kleiner als 2 Eingaben
            if(len < 2){
                System.out.println("Falsche Parameteranzahl!");
                throw new Exception();
            }


            //genau 2 Eingaben
            else if(len == 2){
                int a = 0; int b = 0;
                try {
                    a = Integer.parseInt(args[0]);
                    if(a <= 2){
                        System.out.println("Anzahl der Punkte muss groesser als 2 sein.");
                        throw new Exception();
                    }
                }catch (IllegalArgumentException e){
                    System.out.println("Falscher Parameter! Nur Integer groesser 2 sind erlaubt.");
                    throw new Exception();
                }

                try {
                    b = Integer.parseInt(args[1]);
                }catch (IllegalArgumentException e){
                    System.out.println("Falscher Parameter! Als Seed sind nur Integer erlaubt.");
                    throw new Exception();
                }

                PointsGenerator pointsGenerator = new PointsGenerator(0, 100, b);
                ArrayList<Point> points = pointsGenerator.generate(a);

                Point s = points.get(0);
                Point e = points.get(1);
                Line line = new Line(s, e);
                System.out.println("Vergleiche Punkte mit der Geraden " + line);

                for (int i = 2; i < points.size(); i++) {
                    Point p = points.get(i);
                    if(line.side(p) == 0){
                        System.out.println("Punkt " + p + " liegt auf der Linie.");
                        if(isBetween(s, e, p)){
                            System.out.println("Punkt " + p + " liegt zwischen " + s + " und " + e);
                        }
                    }
                    else if(line.side(p) == 1){
                        System.out.println("Punkt " + p + " liegt links der Linie.");
                    }
                    else{
                        System.out.println("Punkt " + p + " liegt rechts der Linie.");
                    }
                }


            }


            //mehr als 2 Eingaben
            else if(len > 4){
                double[] arr = new double[len];
                try {
                    for (int i = 0; i < len; i++) {
                        arr[i] = Double.parseDouble(args[i]);
                    }
                }catch (Exception e){
                    System.out.println("Es war nicht moeglich, alle Punkte einzulesen.");
                    throw new Exception();
                }

                Point s = new Point(arr[0], arr[1]);
                Point e = new Point(arr[2], arr[3]);
                Line line = new Line(s, e);
                System.out.println("Vergleiche Punkte mit der Geraden " + line);

                for (int i = 5; i < len; i+=2) {
                    Point p = new Point(arr[i-1], arr[i]);
                    if(line.side(p) == 0){
                        System.out.println("Punkt " + p + " liegt auf der Linie.");
                        if(isBetween(s, e, p)){
                            System.out.println("Punkt " + p + " liegt zwischen " + s + " und " + e);
                        }
                    }
                    else if(line.side(p) == 1){
                        System.out.println("Punkt " + p + " liegt links der Linie.");
                    }
                    else{
                        System.out.println("Punkt " + p + " liegt rechts der Linie.");
                    }
                }
            }
            else{
                System.out.println("Anzahl der Punkte muss groesser als 2 sein.");
                throw new Exception();
            }


        }catch (Exception e){
            System.out.println("Aufruf mit : java Application numberOfPoints seed");
            System.out.println("oder mit gerader Anzahl Koordinaten: java Application p1x p1y p2x p2y ...");
            System.out.println("Bsp: java Application 100 1337");
        }
    }


    public static boolean isBetween(Point i, Point j, Point k){
        Line line = new Line(i, j);
        Line ik = new Line(i, k);
        Line jk = new Line(j, k);
        return ( line.side(k) == 0 && (ik.getLength() <= line.getLength() && jk.getLength() <= line.getLength()) );
    }



}

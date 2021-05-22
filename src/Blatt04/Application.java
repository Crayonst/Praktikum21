package Blatt04;

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
            }

            //mehr als 2 Eingaben
            else{
                try {
                    int[] arr = new int[len];
                    for (int i = 0; i < len; i++) {
                        arr[i] = Integer.parseInt(args[i]);
                    }
                }catch (Exception e){
                    System.out.println("Es war nicht moeglich, alle Punkte einzulesen.");
                    throw new Exception();
                }


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

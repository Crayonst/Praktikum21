package Blatt04;

import java.util.ArrayList;
import java.util.LinkedList;

// Aufgabe 4.2
public class SimpleConvexHull {

    public static LinkedList<Point> computeHull(Point[] p) {
        LinkedList<Point> list = new LinkedList<>();
        for (int i = 0; i < p.length - 1; i++) {
            /* 这是错的，取两点应该是p[i]和p[j]，然后在j循环里查看所有其他点是否满足
               ”全在同一侧”或“全在直线上且不是最外侧点”
               两条件满足其一，则p[i]和p[j]可添加到list里。
               也就是说，可能需要*第三个循环k*，a[k]用来表示其他所有点
            */
            for (int j = i + 1; j < p.length; j++) {
                Line l = new Line(p[i], p[j]);
                int counta = 0;
                int countl = 0;
                int countr = 0;
                for (int k = 0; k < p.length; k++) {
                    if (p[i].equals(p[k]) || p[j].equals(p[k])) {
                        continue;
                    } else {
                        if (l.side(p[k]) == 0) {
                            if (ausserstePunkt(p[i], p[j], p[k])) { //p[k]是最外点
                                break; //若p[k]是最外点，则p[j]和p[i]组成的线段肯定不是konvex
                            } else {
                                counta++;
                            }
                        } else if (l.side(p[k]) == 1) {
                            countl++;
                        } else {
                            countr++;
                        }
                    }
                }
                if (counta == p.length - 2 || countl == p.length - 2 || countr == p.length - 2) {
                    list.add(p[i]);
                    list.add(p[j]);
                    System.out.println("Neue Aussenkante gefunden: ( " + p[i] + " )" + " -- "
                            + "( " + p[j] + " )");
                }
            }
        }
        return list;
    }

    //判断是否为最外侧点
    // c点是与a和b比较的点
    private static boolean ausserstePunkt(Point a, Point b, Point c) {
        Line l = new Line(a, b);
        if (l.side(c) == 0) {
            if (a.get(0) >= b.get(0) && a.get(1) >= b.get(1)) {
                if (c.get(0) <= a.get(0) && c.get(0) >= b.get(0)
                        && c.get(1) <= a.get(1) && c.get(1) >= b.get(1)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (c.get(0) <= b.get(0) && c.get(0) >= a.get(0)
                        && c.get(1) <= b.get(1) && c.get(1) >= a.get(1)) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private static void FehlerAusdruck() {
        System.out.println("Aufruf mit : java Application numberOfPoints seed");
        System.out.println("oder mit gerader Anzahl Koordinaten: java Application p1x p1y p2x p2y ...");
        System.out.println("Bsp: java Application 100 1337");
    }


    public static void main(String[] args) {
        int len = args.length;
        if (len < 2) {
            System.out.println("Falsche Parameteranzahl!");
            FehlerAusdruck();
            System.exit(0);
        }
        if (len == 2) {
            int a = 0;
            int b = 0;
            try {
                a = Integer.parseInt(args[0]);
                if (a <= 2) {
                    System.out.println("Anzahl der Punkte muss groesser als 2 sein.");
                    FehlerAusdruck();
                    System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Falscher Parameter! Nur Integer groesser 2 sind erlaubt.");
                FehlerAusdruck();
                System.exit(0);
            }
            try {
                b = Integer.parseInt(args[1]);
            } catch (IllegalArgumentException e) {
                System.out.println("Falscher Parameter! Als Seed sind nur Integer erlaubt.");
                FehlerAusdruck();
                System.exit(0);
            }
            PointsGenerator pointsGenerator = new PointsGenerator(0, 100, b);
            ArrayList<Point> points = pointsGenerator.generate(a);
            Point[] p = new Point[points.size()];
            for (int i = 0; i < p.length; i++) {
                p[i] = points.get(i);
            }
            LinkedList<Point> list = computeHull(p);
            for (int i = 2; i < list.size() - 3; i++) {
                System.out.print( list.get(i)   + " -- ");
            }
            System.out.println(list.get(list.size() - 3));
//            System.out.println("(" + list.get(list.size() - 2) + ", " + list.get(list.size() - 1) + ")");
        }


        if (len > 3) {
            double[] arr = new double[len];
            try {
                for (int i = 0; i < len; i++) {
                    arr[i] = Double.parseDouble(args[i]);
                }
            } catch (Exception e) {
                System.out.println("Es war nicht moeglich, alle Punkte einzulesen.");
                FehlerAusdruck();
                System.exit(0);
            }
            Point[] p = new Point[len];
            int count = 0;
            for (int i = 0; i < p.length; i+=2) {
                p[count] = new Point(arr[i],arr[i+1]);
                count++;
            }
            LinkedList<Point> list = computeHull(p);
            for (int i = 2; i < list.size() - 3; i++) {
                System.out.print( list.get(i)   + " -- ");
            }
            System.out.println(list.get(list.size() - 3));
        }
    }
}

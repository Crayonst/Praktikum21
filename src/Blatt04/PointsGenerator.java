package Blatt04;
import java.util.ArrayList;
import java.util.Random;


public class PointsGenerator {

    private final double min;
    private final double max;
    private final int Seed;


    public PointsGenerator(double min, double max, int Seed){
        this.min = min;
        this.max = max;
        this.Seed = Seed;
    }


    //erzeugen eine pointsList mit n points
    public ArrayList<Point> generate(int n) {

        Random rng = new Random(Seed);
        ArrayList<Point> points = new ArrayList<>();
        for(int i=0; i<n; i++) {
            double coordX = (rng.nextDouble() * (max - min) + min);
            double coordY = (rng.nextDouble() * (max - min) + min);
            Point p = new Point(coordX, coordY);
            points.add(p);
        }
        return points;
    }
}

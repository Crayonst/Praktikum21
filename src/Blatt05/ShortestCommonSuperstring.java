package Blatt05;
import java.util.ArrayList;
import java.util.Random;

public class ShortestCommonSuperstring {


    public static String generateRandomString(Random numberGenerator) {
        String alphabet = "AEIOU";
        StringBuilder builder = new StringBuilder();
        int length = 3 + numberGenerator.nextInt(4);
        while (length-- > 0) {
            int randomIdx = numberGenerator.nextInt(alphabet.length());
            builder.append(alphabet.charAt(randomIdx));

        }
        return builder.toString();
    }


    //um eine random String-Arraylist anzulegen
    //wenn keine Seed Eingegeben werden
    public static ArrayList<String> CreateArr(int x){
        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<x; i++){
            arr.add(generateRandomString(new Random(6521)));
        }
        return arr;
    }

    //wenn eine Seed eingegeben werden
    public static ArrayList<String> CreateArr(int x, int Seed){
        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<x; i++){
            arr.add(generateRandomString(new Random(Seed)));
        }
        return arr;
    }




    //suchen wie viel überlappenden Zahlen von zwei String    只对于每个string左右两边判断，中间的无法比较
    public static int StringOverlap(String A, String B){
        int m = A.length();
        int n = B.length();
        int max = 0;
        for( int i=0; i<Math.min(m,n); i++ ){
            if( A.substring(m-i, m).equals(B.substring(0, i)) ){
                max = i;
            }
        }
        return max;
    }


    public static String findHinter(String A, String B){
        ArrayList<String> arr = new ArrayList<>();
        int m = A.length();
        int n = B.length();
        for( int i=0; i<Math.min(m,n); i++ ){
            if( A.substring(m-i, m).equals(B.substring(0, i)) ){
                arr.add(B.substring(i, n));
            }
        }
        return arr.get(arr.size()-1);
    }




    public static void Realisierung(ArrayList<String> arr){
        //vergleichen, um die maximale Anzahl deren überlappden Buchstaben von zwei String zu finden
        while(arr.size() > 1){
            int max = 0; int index1 = 0; int index2 = 0;

            for(int i = 0; i < arr.size(); i++){

                for(int j = 0; j < arr.size(); j++){
                    if(j != i){
                        int tmp = StringOverlap(arr.get(i), arr.get(j));
                        if(max <= tmp){
                            max = tmp;
                            index1 = i;
                            index2 = j;
                        }
                    }
                }
            }

            String x = arr.get(index1);
            String y = arr.get(index2);

            String neuer = x+ findHinter(x, y);

            System.out.println("Ersetze " + x + " und " + y + " durch " + neuer);

            arr.remove(x);
            arr.remove(y);
            arr.add(neuer);

            System.out.println(arr.toString().replace("[","").replace("]",""));
        }
    }





    public static void main(String[] args) {

        int len = args.length;
        ArrayList<String> arr = new ArrayList<>();

        //1. keine Eingabe
        if(len <= 0){
            System.out.println("FEHLER: Es wurde kein Parameter übergeben.");
            return;
        }

        //2. eine Eingabe
        else if(len == 1){
            try {
                int x = Integer.parseInt(args[0]);
                if(x > 1){
                    arr = CreateArr(x);
                }
                else{
                    System.out.println("FEHLER: Der erste Parameter muss eine natuerliche Zahl >1 sein.");
                    return;
                }
            } catch (Exception e){
                System.out.println("FEHLER: Der Seed Parameter konnte nicht gelesen werden.");
                return;
            }
        }

        //3. mehrere Eingaben
        else{
            //ob jeden String im List nur aus a~z/A~Z bestehen(mit regulaerer Ausdruck)
            for(int i = 0; i < len; i++){
                if( !args[i].matches("[a-zA-Z]+") ){
                    //System.out.println("Fehler: Jeden Eingabe muss nur aus a~z bestehen.");
                    return;
                }
                if(args[i].length() < 2){
                    System.out.println("FEHLER: Uebergebene Strings muessen mindestens Laenge 2 haben.");
                    return;
                }


                //wenn kein Problem hat dann zur Großbuchstaben
                arr.add(args[i].toUpperCase());
            }
        }


        //und dann ausfuehren
        System.out.println(arr.toString().replace("[","").replace("]",""));
        Realisierung(arr);
        System.out.println("Superstring "+ arr+ " mit Laenge "+arr.get(0).length()+ " gefunden ");
    }
}

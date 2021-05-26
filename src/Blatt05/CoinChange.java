package Blatt05;

public class CoinChange {
    public static int[] change(int b, int[] w) {
        int[] res = new int[w.length];
        res[0] = b / 200;
        res[1] = (b - res[0] * 200) / 100;
        res[2] = (b - res[0] * 200 - res[1] * 100) / 50;
        res[3] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50) / 20;
        res[4] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 20) / 10;
        if (w.length == 8) { // Euro
            res[5] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 20 - res[4] * 10) / 5;
            res[6] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 20 - res[4] * 10 - res[5] * 5) / 2;
            res[7] = b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 20 - res[4] * 10 - res[5] * 5 -
                    res[6] * 2;
        } else if (w.length == 10) { // Mira
            res[5] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 2 - res[4] * 10) / 9;
            res[6] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 2 - res[4] * 10 - res[5] * 9) / 7;
            res[7] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 2 - res[4] * 10 - res[5] * 9 -
                    res[6] * 7) / 5;
            res[8] = (b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 2 - res[4] * 10 - res[5] * 9 -
                    res[6] * 7 - res[7] * 5) / 2;
            res[9] = b - res[0] * 200 - res[1] * 100 - res[2] * 50 - res[3] * 2 - res[4] * 10 - res[5] * 9 -
                    res[6] * 7 - res[7] * 5 - res[8] * 2;
        } else {
            throw new IllegalArgumentException();
        }
        return res;
    }

    private static void ausdruck() {
        System.out.println("Aufruf mit : java CoinChange Euro|Mira n");
        System.out.println("Bsp: java CoinChange Euro 100");
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("FEHLER: Falsche Parameteranzahl!");
            ausdruck();
            System.exit(0);
        } else {
            String w = args[0];
            if (!w.equals("Euro") && !w.equals("Mira")) {
                System.out.println("FEHLER: Unbekannte Waehrung " + w + "!");
                ausdruck();
                System.exit(0);
            }
            try {
                Integer.parseInt(args[1]);
            } catch (IllegalArgumentException e) {
                System.out.println("FEHLER: Falscher Parametertyp fuer das Wechselgeld!");
                ausdruck();
                System.exit(0);
            }
            int betrag = Integer.parseInt(args[1]);
            if (betrag < 0) {
                System.out.println("FEHLER: Wechselgeld darf nicht negativ sein!");
                ausdruck();
                System.exit(0);
            }
            if (w.equals("Euro")) {
                int[] wE = new int[]{200, 100, 50, 20, 10, 5, 2, 1};
                System.out.println("Auszugebendes Wechselgeld: " + betrag + " Eurocent");
                int[] change = change(betrag, wE);
                int rest = betrag;
                for (int i = 0; i < change.length; i++) {
                    if (change[i] != 0) {
                        rest = rest - change[i] * wE[i];
                        System.out.println("(" + wE[i] + "," + change[i] + "," + rest + ")");
                    }
                }
                System.out.println("Ausgegebenes Wechselgeld: " + betrag + " Eurocent");
            }
            if (w.equals("Mira")) {
                int[] wM = new int[]{200, 100, 50, 20, 10, 9, 7, 5, 2, 1};
                System.out.println("Auszugebendes Wechselgeld: " + betrag + " Mira");
                int[] change = change(betrag, wM);
                int rest = betrag;
                for (int i = 0; i < change.length; i++) {
                    if (change[i] != 0) {
                        rest = rest - change[i] * wM[i];
                        System.out.println("(" + wM[i] + "," + change[i] + "," + rest + ")");
                    }
                }
                System.out.println("Ausgegebenes Wechselgeld: " + betrag + " Mira");
            }
        }
    }
}

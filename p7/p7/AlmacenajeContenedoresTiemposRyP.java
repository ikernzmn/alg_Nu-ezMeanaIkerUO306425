package p7;

public class AlmacenajeContenedoresTiemposRyP {

    public static void main(String[] args) {
        String nombreFichero;
        long t1;
        long t2;
        double tResultado;

        System.out.println("BACKTRACKING: BIN PACKING");

        System.out.println("MEDICION DE TIEMPOS: ");
        for (int i = 0; i < 10; i++) {
            nombreFichero = "./test0" + i + ".txt";
            String[] argsMain = { nombreFichero };

            t1 = System.nanoTime();
            AlmacenajeContenedoresRyP.main(argsMain);
            t2 = System.nanoTime();
            tResultado = (t2 - t1) / 1_000_000.0;

            System.out.println("FICHERO: " + nombreFichero + ", TIEMPO(ms) = " + tResultado);
        }
        System.out.println("MEDICION DE TIEMPOS FINALIZADA");
    }
}

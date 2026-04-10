package p6Extra;

public class NReinasTiempos{
    private static NReinas nReinas = new NReinas();

    public static void main(String[] args){
        long t1;
        long t2;
        double tFinal;

        System.out.println("MEDICION DE TIEMPOS N REINAS: ");

        for(int i = 2; i < 20; i += 2){
            t1 = System.nanoTime();

            nReinas.resolverNReinas(i);

            t2 = System.nanoTime();

            tFinal = (t2 - t1)/ 1_000_000;  

            System.out.printf("PARA N = %d , T = %f MS", i, tFinal);
            System.out.println();
        }

        System.out.println("FIN DE LA MEDICION DE TIEMPOS");
    }
}
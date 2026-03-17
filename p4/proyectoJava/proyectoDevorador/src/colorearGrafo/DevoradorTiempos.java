package colorearGrafo;

public class DevoradorTiempos {
    public static void main(String[] args){
        String[] ficheros = {"g4.json", "g8.json", "g16.json", "g32.json", "g64.json", "g128.json",
            "g256.json","g512.json","g1024.json","g2048.json","g4096.json","g8192.json",
            "g16384.json", "g32768.json","g65536.json"};

        long t1;
        long t2;
        double tiempo;
        for(String fichero : ficheros){
            t1 = System.currentTimeMillis();

            Devorador.main(new String[]{fichero});

            t2 = System.currentTimeMillis();

            tiempo = t2 - t1;

            System.out.printf("Fichero: %s ; Tiempo: %f", fichero, tiempo);
            System.out.println();
        }
    }
}

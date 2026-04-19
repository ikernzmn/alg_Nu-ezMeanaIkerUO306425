package p0;
import java.util.ArrayList;


public class JavaA2{
    public static void main(String[] args){
        System.out.println("TIEMPOS DEL ALGORITMO A2");
        int n=5000;

        for(int i = 0; i < 8; i++){
            long tInicio = System.nanoTime();

            getListaPrimos(n);

            long tFin = System.nanoTime();

            long duracion = ((tFin - tInicio) / 1_000_000);

            System.out.printf("n = %d, ***, tiempo = %d milisegundos", n, duracion);
            System.out.println();

            n = n * 2;
    }
    
    }

    public static boolean isPrimoA2(int numero){
        for(int i = 2; i < numero; i++){
            if(numero % i == 0){
                return false;
            }
        }

        return true;
    }

    public static void getListaPrimos(int limite){
        ArrayList<Integer> listaPrimos = new ArrayList<Integer>();
        int contadorPrimos = 0;

        for(int i = 2; i < limite; i++){
            if(isPrimoA2(i)){
                listaPrimos.add(i);
                contadorPrimos++;
            }
        }

        System.out.printf("Hasta %d hay %d primos.", limite, contadorPrimos);
        System.out.println();
    }
}

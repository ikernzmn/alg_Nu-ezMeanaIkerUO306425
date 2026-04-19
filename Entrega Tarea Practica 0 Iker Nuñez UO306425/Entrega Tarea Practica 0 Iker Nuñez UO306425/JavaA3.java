package p0;
import java.util.ArrayList;


public class JavaA3{
    public static void main(String[] args){
        System.out.println("TIEMPOS DEL ALGORITMO A3");
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

    public static boolean isPrimoA3(int numero){
        for(int i = 2; i < (numero/2) + 1; i++){
            if(numero % i == 0){
                return false;
            }
        }

        return true;
    }

    public static void getListaPrimos(int limite){
        ArrayList<Integer> listaPrimos = new ArrayList<Integer>();
        int contadorPrimos = 0;

        listaPrimos.add(2);
        contadorPrimos++;

        for(int i = 3; i < limite + 1; i = i + 2){
            if(isPrimoA3(i)){
                listaPrimos.add(i);
                contadorPrimos++;
            }
        }

        System.out.printf("Hasta %d hay %d primos.", limite, contadorPrimos);
        System.out.println();
    }
}
package p0;
import java.util.ArrayList;

public class JavaA4{
    public static void main(String[] args){
        System.out.println("TIEMPOS DEL ALGORITMO A4");
        int n=5000;

        for(int i = 0; i < 8; i++){
            long tInicio = System.nanoTime();

            getListaPrimosEratostenes(n);

            long tFin = System.nanoTime();

            long duracion = ((tFin - tInicio) / 1_000_000);

            System.out.printf("n = %d, ***, tiempo = %d milisegundos", n, duracion);
            System.out.println();

            n = n * 2;
        }
    }

    public static void getListaPrimosEratostenes(int numero){
        boolean[] esPrimo = new boolean[numero + 1];
        int contadorPrimos = 0;

        //ponemos todos los numeros a true sin mirar si son primos o si no. Si no son primos seran tachados despues
        for(int i = 2; i < numero + 1; i++){
            esPrimo[i] = true;
        }

        //Ahora recorremos el array, buscando los primos y tachando sus respectivos múltiplos. Los numeros
        //que queden sin tachar seran los numeros primos menores que el limite
        for(long i = 2; i * i < numero + 1; i++){
            //si es true es que no fue tachado por ningun divisor, luego es primo. Tachamos sus multiplos
            if(esPrimo[(int) i]){
                for(long j = i * i; j < numero + 1; j = j + i){
                    esPrimo[(int) j] = false;
                }
            }
        }

        for(int i = 2; i < numero + 1; i++){
            if(esPrimo[i]){
                contadorPrimos++;
            }
        }
        
        System.out.printf("Hasta %d, hay %d primos.", numero, contadorPrimos);
        System.out.println();


    }
}
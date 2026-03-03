package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PuntosTrivial
{
    static double[][] matrizPuntos;
    static int tamañoMatriz;
    static ArrayList<String> lineas = new ArrayList<String>();
    static Random rand = new Random();

    /*
     * Metodo para leer de fichero
     * @param nombreFichero
     */
    public static void leerFichero(String nombreFichero){
        BufferedReader bf = null;
        try
        {
            bf = new BufferedReader(new FileReader(nombreFichero));
            String linea;
            boolean isFirstLine = true;
            int indice = 0;

            while((linea = bf.readLine()) != null)
            {
                if(isFirstLine)
                {
                    tamañoMatriz = Integer.parseInt(linea);
                    matrizPuntos = new double[tamañoMatriz][2];
                    isFirstLine = false;
                } else
                {
                    String[] lineaDiv = linea.split(",S");
                    matrizPuntos[indice][0] = 
                        Double.parseDouble(lineaDiv[0]);
                    matrizPuntos[indice][1] = 
                        Double.parseDouble(lineaDiv[1]);
                    indice++;
                }

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try{
                if(bf != null)
                {
                    bf.close(); 
                }
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void mezclar()
    {
        for(int i = 0; i < matrizPuntos.length; i++)
        {
            int j = rand.nextInt(i + 1);

            double[] temporal = matrizPuntos[i];
            matrizPuntos[i] = matrizPuntos[j];
            matrizPuntos[j] = temporal;
        }
    }

    public static String[] BuscarDistanciaMinima()
    {   
        double minDistancia = 100000000;
        int indice1 = -1;
        int indice2 = -1;
        String[] resultado = new String[2];

        for(int i = 0; i < matrizPuntos.length; i++)
        {
            for(int j = i + 1; j < matrizPuntos.length - 1; j++)
            {
                double x1 = matrizPuntos[i][0];
                double y1 = matrizPuntos[i][1];

                double x2 = matrizPuntos[j][0];
                double y2 = matrizPuntos[j][1];

                //formula de la distancia entre 2 puntos: Raiz((x2-x1)^2 + (y2-y1)^2)
                double distancia = Math.sqrt((Math.pow((x2 - x1), 2)) + Math.pow((y2 - y1), 2));

                if(distancia < minDistancia){
                    minDistancia = distancia;
                    indice1 = i;
                    indice2 = j;
                }
            }
        }

        resultado[0] = String.format("SU DISTANCIA MINIMA: %.6f", minDistancia);
        resultado[1] = String.format("PUNTOS MAS CERCANOS: [%.6f,%.6f] [%.6f,%.6f]", 
            matrizPuntos[indice1][0], matrizPuntos[indice1][1],
            matrizPuntos[indice2][0], matrizPuntos[indice2][1]);

        return resultado;
    }

    public static void generarMatrizPuntosAleatoria(int n)
    {
        matrizPuntos = new double[n][2];

        Random rand = new Random();
        
        for (int i = 0; i < n; i++) {
            //generamos numeros con 6 decimales
            double x = Math.round(rand.nextDouble(1,100) * 1000000) / 1000000.0;
            double y = Math.round(rand.nextDouble(1,100) * 1000000) / 1000000.0;
            matrizPuntos[i][0] = x;
            matrizPuntos[i][1] = y;
        }
    }

}
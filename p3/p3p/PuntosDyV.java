package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuntosDyV
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
                    String[] lineaDiv = linea.split(",");
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

    public static String[]  BuscarDistanciaMinima()
    {   
        int iz = 0;
        int de = matrizPuntos.length - 1;

        quickSortPorX();
        //Arrays.sort(matrizPuntos, Comparator.compareDouble(matriz -> matriz[0]))
        //coge la matriz y ordena por coordenada x 

        Object[] resultado = BuscarDistanciaMinimaRec(iz, de);

        //AQUI GUARDAS LOS INDICES DE LA PRIMERA PARTICION Y ASI CALCULAS LA DISTANCIA DE LOS UNICOS PUNTOS
        //QUE NO SE CALCULA. LUEGO LA COMPARAS CON LA DISTANCIA MINIMA QUE TE DA EL RECURSIVO Y YA ESTA

        double distanciaMinima = (Double) resultado[0];
        int i = (Integer) resultado[1];
        int j = (Integer) resultado[2];
        
        String[] retorno = new String[2];

        retorno[0] = String.format("SU DISTANCIA MINIMA: %.6f", resultado[0]);
        retorno[1] = String.format("PUNTOS MAS CERCANOS: [%.6f,%.6f] [%.6f,%.6f]", 
            matrizPuntos[i][0], matrizPuntos[i][1],
            matrizPuntos[j][0], matrizPuntos[j][1]);

        return retorno;

    }

    public static Object[] BuscarDistanciaMinimaRec(int iz, int de)
    {   
        double minDistancia = Double.MAX_VALUE;
        int indice1 = -1;
        int indice2 = -1;
        Object[] resultado = new Object[3];

        Object[] resultado1;
        Object[] resultado2;

        Object[] retorno;

        int indiceParticion = (iz + de)/2;

        if(de == iz)
        {
            resultado[0] = minDistancia;
            resultado[1] = indice1;
            resultado[2] = indice2;
            return resultado;
        }
        
        if(de - iz == 1)
        {
            double x1 = matrizPuntos[iz][0];
            double y1 = matrizPuntos[iz][1];

            double x2 = matrizPuntos[de][0];
            double y2 = matrizPuntos[de][1];

            //formula de la distancia entre 2 puntos: Raiz((x2-x1)^2 + (y2-y1)^2)
            double distancia = Math.sqrt((Math.pow((x2 - x1), 2)) + Math.pow((y2 - y1), 2));

            if(distancia < minDistancia)
            {
                minDistancia = distancia;
                indice1 = iz;
                indice2 = de;

                resultado[0] = minDistancia;
                resultado[1] = indice1;
                resultado[2] = indice2;

                return resultado;
            }
        }

        resultado1 = BuscarDistanciaMinimaRec(iz, indiceParticion);

        resultado2 = BuscarDistanciaMinimaRec(indiceParticion + 1, de);

        retorno = combinar(resultado1, resultado2);

        //hacer una lista de puntos e ir almacenando los valores intermedios y luego comparar con la distancia minima
        //combinar dentro del if del caso base

        return retorno;
    }

    public static void quickSortPorX()
    {
        quickSort(0, matrizPuntos.length - 1);
    }

    private static void quickSort(int iz, int de)
    {
        if(iz < de)
        {
            int indPivote = particion(iz, de);
            quickSort(iz, indPivote - 1);
            quickSort(indPivote + 1, de);
        }
    }

    private static int particion(int iz, int de)
    {
        double pivote = matrizPuntos[de][0]; //queremos comparar por la x
        int i = iz - 1;

        for(int j = iz; j < de; j++)
        {
            if(matrizPuntos[j][0] <= pivote)
            {
                i++;
                intercambiar(i, j);
            }
        }

        intercambiar(i + 1, de);
        return i + 1;
    }

    private static void intercambiar(int i, int j)
    {
        double[] temp = matrizPuntos[i];
        matrizPuntos[i] = matrizPuntos[j];
        matrizPuntos[j] = temp;
    }

    public static Object[] combinar(Object[] resultado1, Object[] resultado2)
    {
        double distancia1 = (Double) resultado1[0];
        double distancia2 = (Double) resultado2[0];

        return distancia1 < distancia2? resultado1 : resultado2;
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
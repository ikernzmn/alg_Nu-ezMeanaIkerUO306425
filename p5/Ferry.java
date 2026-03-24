import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;

public static void main(String[] args)
{
     Ferry.leerFichero(args[0]);
    // Ferry.leerFichero("test07.txt");

    Ferry.inicializarMatriz();

    Ferry.programacionDinamica();

    Ferry.imprimirResultado();
}


public class Ferry
{

    static boolean[][] matrizEstado;
    static int longitudCarril;
    static int cochesCargados;
    static List<Integer> filaCoches = new ArrayList<>();

    public static void leerFichero(String nombreFichero)
    {
        BufferedReader br;
        boolean isFirstLine = true;
        String linea;

        filaCoches.add(0); //Como empezamos a recorrer en 1, para que no

        try
        {
            br = new BufferedReader( new FileReader( nombreFichero ) );

            while((linea = br.readLine()) != null)
            {
                String[] arrayLinea = linea.split(" ");

                if(isFirstLine)
                {
                    longitudCarril = Integer.parseInt(arrayLinea[0]);
                    isFirstLine = false;
                } else
                {
                    for(int i = 0; i < arrayLinea.length; i++)
                    {
                        filaCoches.add(Integer.parseInt(arrayLinea[i]));
                    }
                }
            }
        } catch(IOException ioe)
        {
            System.err.println(
                "Error al intentar abrir el fichero ");
        }
    }

    public static void inicializarMatriz()
    {
        int nVehiculos = filaCoches.size();

        // nVehiculos filas y longitudEntrada columnas
        matrizEstado = new boolean[nVehiculos][longitudCarril + 1];
    }

    public static void programacionDinamica()
    {
        // i -> nCochesProcesados
        // j -> ocupacion de babor  

        int sumaLongCochesProcesados = 0; 
        int longEstribor;
        int longVeh;
        boolean pudoAlmacenarse;

        //caso base -> sin vehiculos cargados, tienes 0m ocupados
        matrizEstado[0][0] = true;
        // el resto de columnas de la fila 0 se inicializan a F ya que representan el estado inicial
        
        for(int i = 1; i < filaCoches.size(); i++)
        {
            longVeh = filaCoches.get(i);
            sumaLongCochesProcesados += longVeh;
            pudoAlmacenarse = false;
            
            for(int j = 0; j < matrizEstado[0].length; j++)
            {
                longEstribor = sumaLongCochesProcesados - j;

                //¿Puedes meter en babor?
                if(j >= longVeh && matrizEstado[i - 1][j - longVeh]) //se comprueba que la columna sea al menos igual que la longitud del veh
                //que vas a meter, y despues que la fila anterior y la columna j - la long del veh fuera true
                {
                    matrizEstado[i][j] = true;
                }

                //Si no, ¿puedo meter en estribor?
                else if(matrizEstado[i-1][j] && longEstribor <= longitudCarril) //el estado en babor no cambia si metes en estribor, de ahi
                //que se compruebe (i-1,j). Despues mira que el nuevo coche coja en estribor
                {
                    matrizEstado[i][j] = true;
                }
            }

            
            for(int k = 0; k < matrizEstado[i].length; k++)
            {
                if(matrizEstado[i][k])
                {
                    pudoAlmacenarse = true;
                }
            }

            if(!pudoAlmacenarse)
            {
                return; //si el ultimo vehiculo no se pudo cargar, acaba el algoritmo, ya que no se puede saltar al siguiente
            } else
            {
                cochesCargados++;
            }
        }
    }

    public static void imprimirResultado()
    {
        System.out.printf("Han llegado un total de %d vehiculos (%d cargados)\n", cochesCargados, cochesCargados);

        System.out.println("Tabla de memorizacion: ");

        System.out.print("V/L\t");
        for(int i = 0; i < matrizEstado[0].length; i++)
        {
            if(i == matrizEstado[0].length - 1)
            {
                System.out.printf("%d\n", i);
            } else
            {
                System.out.printf("%d\t", i);
            }
            
        }
        
        for(int j = 0; j < filaCoches.size(); j++)
        {
            System.out.print(j + "\t");
            for(int k = 0; k < matrizEstado[0].length; k++)
            {
                if(k == matrizEstado[0].length - 1)
                {
                    if(matrizEstado[j][k])
                    {
                        System.out.println("T");
                    } else
                    {
                        System.out.println("F");
                    }
                    
                } else
                {
                    if(matrizEstado[j][k])
                    {
                        System.out.print("T\t");
                    } else
                    {
                        System.out.print("F\t");
                    }
                }
            }
        }

        imprimirAsignacionCoches();
    }

    private static void imprimirAsignacionCoches() {
        //Recorremos en orden la lista de vehiculos
        System.out.println("Posible asignación: ");
        System.out.println();
        
        int sumaBabor = 0;
        int sumaEstribor = 0;

        for(int i = 1; i < cochesCargados + 1; i++)
        {
            int longVehiculo = filaCoches.get(i);

            if((sumaBabor + longVehiculo) <= longitudCarril)
            {
                System.out.printf("Vehiculo %d (longitud %d) a babor ",i, longVehiculo);
                System.out.println();
                sumaBabor += longVehiculo;
            } else 
            {
                sumaEstribor += longVehiculo;
                System.out.printf("Vehiculo %d (longitud %d) a estribor ",i, longVehiculo);
                System.out.println();
            }
        }

        System.out.printf("Ocupacion final: Babor %dm / Estribor %dm (válido <= %dm)", sumaBabor, sumaEstribor, longitudCarril);
        System.out.println();
    }

}

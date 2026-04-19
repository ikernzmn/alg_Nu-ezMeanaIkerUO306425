package p6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class AlmacenajeContenedores {

    private int capacityC;
    private Integer[] setsS;
    private boolean[] setsFlag;
    private int bestK; // minimum number of containers

    private int numberOfRecCalls = 0;

    private ArrayList<ArrayList<Integer>> bestDistribution;

    // Constructor
    public AlmacenajeContenedores(int capacity, Integer[] setsS) {
        this.capacityC = capacity;
        this.setsS = setsS;
        this.setsFlag = new boolean[setsS.length];

        // Descendant order
        // Arrays.sort(setsS, (a, b) -> b - a);

        this.bestDistribution = new ArrayList<ArrayList<Integer>>();
        bestK = setsS.length; // Initializes to the worst case
    }

    public static void main(String[] args) {
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(args[0]));
            int capacityC = Integer.parseInt(sc.nextLine());
            String[] parts = sc.nextLine().split(" ");
            Integer[] setsS = new Integer[parts.length];

            int i = 0;
            for (String s : parts) {
                setsS[i] = Integer.parseInt(s);
                i++;
            }

            new AlmacenajeContenedores(capacityC, setsS).searchSolution();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error while reading the file");
        }

    }

    // Solves the problem and logs the solution
    public void searchSolution() {
        // calls the solving algorithm
        backtracking(0, new ArrayList<ArrayList<Integer>>());

        // logs the solution
        logSolution();
    }

    // Solves the problem by backtracking
    private void backtracking(int indexS, ArrayList<ArrayList<Integer>> distribution) {
        numberOfRecCalls++;

        // Base case
        if (indexS == setsS.length) {
            Integer thisK = distribution.size();
            if (thisK < bestK) {
                copySolution(distribution);
            }
            return;
        }

        // Pruning: if current size es greater or equal than best one, recursive call
        // ends
        // if (distribution.size() >= bestK) {
        //     // stops the execution
        //     return;
        // }

        int size = distribution.size();
        for (int pos = 0; pos < size; pos++) {
            // tries to add it to an existant container
            if (!setsFlag[indexS] && sum(distribution.get(pos)) + setsS[indexS] <= capacityC) {
                distribution.get(pos).add(setsS[indexS]);
                setsFlag[indexS] = true;

                backtracking(indexS + 1, distribution);
                
                setsFlag[indexS] = false;
                if (distribution.size() != 0) {
                    distribution.get(pos).removeLast();
                }
            }
        }

        // could not add it to an existant one, creates a new container
        ArrayList<Integer> newContainer = new ArrayList<Integer>();
        newContainer.add(setsS[indexS]);
        setsFlag[indexS] = true;
        distribution.add(newContainer);

        backtracking(indexS + 1, distribution);

        setsFlag[indexS] = false;
        if (distribution.size() != 0) {
            distribution.remove(distribution.size() - 1);
        }
    }

    // Returns the sum of the elements in the container
    private int sum(ArrayList<Integer> container) {
        Integer sumContainer = 0;

        for (Integer number : container) {
            sumContainer += number;
        }

        return sumContainer;
    }

    // Copies the solution given by param to save it
    private void copySolution(ArrayList<ArrayList<Integer>> distribution) {
        bestDistribution.clear();

        for (ArrayList<Integer> container : distribution) {
            bestDistribution.add(new ArrayList(container));
        }

        bestK = distribution.size();
    }

    // Prints the solution: Containers distribution and number of containers
    // required
    public void logSolution() {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter("result.txt"));

            bf.write("Lista de contenedores y objetos contenidos:\n");

            for (int i = 0; i < bestDistribution.size(); i++) {
                bf.write("Contenedor " + i + ": ");
                for (int j = 0; j < bestDistribution.get(i).size(); j++) {
                    bf.write(bestDistribution.get(i).get(j) + " ");
                }
                bf.write("\n");
            }

            bf.write("El numero de contenedores necesarios es: " + bestK + "\n");
            bf.write("Se necesitaron " + numberOfRecCalls + " llamadas recursivas.\n");
        } catch (IOException ioe) {
            System.out.println("No se pudo abrir el fichero de log");
        } finally{
            try{
                if(bf != null) {
                    bf.close();
                }
            } catch(IOException ioe){
                System.out.println("Error: No se pudo cerrar el archivo");
            }   
        }

        // System.out.println("Lista de contenedores y objetos contenidos: ");
        // for (int i = 0; i < bestDistribution.size(); i++) {
        //     System.out.printf("Contenedor %d: ", i);
        //     for (int j = 0; j < bestDistribution.get(i).size(); j++) {
        //         if (j == bestDistribution.get(i).size() - 1) {
        //             System.out.printf("%d ", bestDistribution.get(i).get(j));
        //             System.out.println();
        //         } else {
        //             System.out.printf("%d ", bestDistribution.get(i).get(j));
        //         }
        //     }
        // }

        // System.out.printf("El numero de contenedores necesarios es: %d", bestK);
        // System.out.println();
        // System.out.printf("Se necesitaron %d llamadas recursivas para resolver el problema.", numberOfRecCalls);
        // System.out.println();
    }
}
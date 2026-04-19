package p7;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AlmacenajeContenedoresRyP {

    private int capacityC;
    private Integer[] setsS;
    private boolean[] setsFlag;
    private int bestK; // minimum number of containers

    private int numberOfRecCalls = 0;

    private ArrayList<ArrayList<Integer>> bestDistribution;

    // Constructor
    public AlmacenajeContenedoresRyP(int capacity, Integer[] setsS) {
        this.capacityC = capacity;
        this.setsS = setsS;
        Arrays.sort(this.setsS, Collections.reverseOrder());
        this.setsFlag = new boolean[setsS.length];

        this.bestDistribution = new ArrayList<ArrayList<Integer>>();
        bestK = getBestK(); // Initializes to the worst case
    }

    public static void main(String[] args) {
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(args[0]));
            // sc = new Scanner(new FileReader("test02.txt"));
            int capacityC = Integer.parseInt(sc.nextLine());
            String[] parts = sc.nextLine().split(" ");
            Integer[] setsS = new Integer[parts.length];

            int i = 0;
            for (String s : parts) {
                setsS[i] = Integer.parseInt(s);
                i++;
            }

            new AlmacenajeContenedoresRyP(capacityC, setsS).searchSolution();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error while reading the file");
        }
    }

    //Gets the initial "bestK"
    private int getBestK(){
        int pairSum = 0;
        int bestK = 0;

        if(setsS.length % 2 == 0){
            for(int i = 0; i < setsS.length - 1; i += 2){
                pairSum = setsS[i] + setsS[i+1];
                bestK += (pairSum % capacityC == 0)? pairSum / capacityC : (pairSum / capacityC) + 1;
            }

            return bestK;
        } else{
            for(int i = 0; i < setsS.length - 2; i += 2){
                pairSum = setsS[i] + setsS[i+1];
                bestK += (pairSum % capacityC == 0)? pairSum / capacityC : (pairSum / capacityC) + 1;
            }

            // add the last one
            int last = setsS[setsS.length - 1];
            bestK += (last % capacityC == 0)? last / capacityC : 
                (last / capacityC) + 1;

            return bestK;
        }
    }

    // Solves the problem and logs the solution
    public void searchSolution() {
        // calls the solving algorithm
        backtracking(0, new ArrayList<ArrayList<Integer>>(), totalSumS());

        // logs the solution
        logSolution();
    }

    // Solves the problem by backtracking
    private void backtracking(int indexS, ArrayList<ArrayList<Integer>> distribution, int sumaRestante) {
        numberOfRecCalls++;

        // Lower bound
        // Calculate the minimum theoric number of containers for the space remaining
        int lowerBound = (sumaRestante + capacityC - 1)/capacityC;

        // if the number of containers + lowerBound is greater than bestK, stops this branch execution
        if(distribution.size() + lowerBound > bestK) return;

        // Base case
        if (indexS == setsS.length) {
            Integer thisK = distribution.size();
            if (thisK < bestK) {
                bestK = thisK; // updates the bestK
                copySolution(distribution);
            }
            return;
        }

        int size = distribution.size();
        for (int pos = 0; pos < size; pos++) {
            // tries to add it to an existant container
            if (!setsFlag[indexS] && sum(distribution.get(pos)) + setsS[indexS] <= capacityC) {
                distribution.get(pos).add(setsS[indexS]);
                setsFlag[indexS] = true;

                backtracking(indexS + 1, distribution, sumaRestante - setsS[indexS]);

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

        backtracking(indexS + 1, distribution, sumaRestante - setsS[indexS]);
        
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

    private int totalSumS(){
        Integer totalSum = 0;
        for(Integer number : setsS){
            totalSum += number;
        }

        return totalSum;
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
        // BufferedWriter bf = null;
        // try {
        //     bf = new BufferedWriter(new FileWriter("result.txt"));

        //     bf.write("Lista de contenedores y objetos contenidos:\n");

        //     for (int i = 0; i < bestDistribution.size(); i++) {
        //         bf.write("Contenedor " + i + ": ");
        //         for (int j = 0; j < bestDistribution.get(i).size(); j++) {
        //             bf.write(bestDistribution.get(i).get(j) + " ");
        //         }
        //         bf.write("\n");
        //     }

        //     bf.write("El numero de contenedores necesarios es: " + bestK + "\n");
        //     bf.write("Se necesitaron " + numberOfRecCalls + " llamadas recursivas.\n");
        // } catch (IOException ioe) {
        //     System.out.println("No se pudo abrir el fichero de log");
        // } finally{
        //     try{
        //         if(bf != null) {
        //             bf.close();
        //         }
        //     } catch(IOException ioe){
        //         System.out.println("Error: No se pudo cerrar el archivo");
        //     }   
        // }

        System.out.println("Lista de contenedores y objetos contenidos: ");
        for (int i = 0; i < bestDistribution.size(); i++) {
            System.out.printf("Contenedor %d: ", i);
            for (int j = 0; j < bestDistribution.get(i).size(); j++) {
                if (j == bestDistribution.get(i).size() - 1) {
                    System.out.printf("%d ", bestDistribution.get(i).get(j));
                    System.out.println();
                } else {
                    System.out.printf("%d ", bestDistribution.get(i).get(j));
                }
            }
        }

        System.out.printf("El numero de contenedores necesarios es: %d", bestK);
        System.out.println();
        System.out.printf("Se necesitaron %d llamadas recursivas para resolver el problema.", numberOfRecCalls);
        System.out.println();
    }
}
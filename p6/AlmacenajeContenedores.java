import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmacenajeContenedores {
    
    private int capacityC;
    private Integer[] setsS;
    private int bestK; //minimum number of containers

    private List<List<Integer>> bestDistribution;

    //Constructor
    public AlmacenajeContenedores(int capacity, Integer[] setsS) 
    {
        this.capacityC = capacity;
        this.setsS = setsS;

        //Descendant order
        Arrays.sort(setsS, (a, b) -> b - a);

        bestK = setsS.length; //Initializes to the worst case 
    }

    public static void main(String[] args)
    {
        Scanner sc;
        try{
            sc = new Scanner( new FileReader(args[0]));
            int capacityC = Integer.parseInt(sc.nextLine());
            String[] parts = sc.nextLine().split(" ");
            Integer[] setsS = new Integer[parts.length];

            int i = 0;
            for(String s : parts){
                setsS[i] = Integer.parseInt(s);
                i++;
            }

            new AlmacenajeContenedores(capacityC,setsS).searchSolution();
        } catch(FileNotFoundException fnfe){
            System.out.println("Error while reading the file");
        }
        
    }

    //Solves the problem and shows the solution
    public void searchSolution()
    {
        //calls the solving algorithm
        backtracking(0, new ArrayList<List<Integer>>());

        //shows the solution
        showSolution();
    }

    //Solves the problem by backtracking
    private void backtracking(int indexS, List<List<Integer>> distribution) 
    {
        //Base case
        if(indexS == setsS.length)
        {
            Integer thisK = distribution.size();
            if(thisK < bestK){
                copySolution(distribution);
                return;
            }
        }

        //Pruning: if current size es greater or equal than best one, recursive call stops
        if(distribution.size() >= bestK)
        {
            // stops the execution
            return;
        }

        for(int pos = 0; pos < setsS.length; pos++)
        {
            // tries to add it to an existant container
            //falta condicion de si lista no vacia -> casca
            if(sum(distribution.get(pos)) + setsS[indexS] <= capacityC)
            {
                distribution.get(pos).add(setsS[indexS]);
                backtracking(indexS + 1, distribution);
                distribution.remove(indexS - 1);
            }
        }

        //could not add it to an existant one, creates a new container
        List<Integer> newContainer  = new ArrayList<Integer>();
        newContainer.add(setsS[indexS]);
        distribution.add(newContainer);
        backtracking(indexS + 1, distribution);
        distribution.remove(indexS - 1);
    }

    //Returns the sum of the elements in the container
    private int sum(List<Integer> container)
    {
        Integer sumContainer = 0;

        for(Integer number : container){
            sumContainer += number;
        }

        return sumContainer;
    }

    //Copies the solution given by param to save it 
    private void copySolution(List<List<Integer>> distribution) 
    {
        for(List<Integer> container : distribution)
        {
            bestDistribution.add(container);
        }  

        bestK = distribution.size();
    }

    //Prints the solution: Containers distribution and number of containers required
    public void showSolution()
    {
        System.out.println("Lista de contenedores y objetos contenidos: ");
        for(int i = 0; i < bestDistribution.size(); i++)
        {
            System.out.printf("Contenedor %i: ");
            for(int j = 0; j < bestDistribution.get(i).size(); j++)
            {
                if(j == bestDistribution.get(i).size() - 1)
                {
                    System.out.printf("%d ", bestDistribution.get(i).get(j));
                    System.out.println();
                } else
                {
                    System.out.printf("%d ", bestDistribution.get(i).get(j));
                }
            }
        }

        System.out.printf("El numero de contenedores necesarios es: %d", bestK);
        System.out.println();
    }
}
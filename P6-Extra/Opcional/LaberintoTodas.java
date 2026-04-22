package p6Extra;

public class LaberintoTodas {
    private int[][] laberinto;
    private int nLlamadasRecursivas = 0;

    public void leerFichero(){
        //TODO
    }

    public void resolver(int[][] laberinto, int fila, int columna){
        nLlamadasRecursivas++;

        // Caso base
        if(fila == columna && fila == laberinto.length){
            // Guardar solucion (copiar matriz)
            return;
        }

        // Backtracking
        if(esValido(fila, columna + 1)){
            laberinto[fila][columna+1] = 2;

            resolver(laberinto, fila, columna + 1);

            laberinto[fila][columna+1] = 0;
        }

        if(esValido(fila + 1, columna) ){
            laberinto[fila+1][columna] = 2;

            resolver(laberinto, fila + 1, columna);

            laberinto[fila+1][columna] = 0;
        }

        if(esValido(fila, columna - 1)){
            laberinto[fila][columna-1] = 2;

            resolver(laberinto, fila, columna - 1);

            laberinto[fila][columna-1] = 0;
        }

        if(esValido(fila - 1, columna) ){
            laberinto[fila-1][columna] = 2;

            resolver(laberinto, fila - 1, columna);

            laberinto[fila-1][columna] = 0;
        }

        return; // Si no tienes movimientos, deshaces la llamada
    }

    private boolean esValido(int fila, int columna){
        return (fila >= 0 && fila < laberinto.length) && 
            (columna >= 0 && columna < laberinto.length) && laberinto[fila][columna] != 1 
            && laberinto[fila][columna] != 2;
    }
}

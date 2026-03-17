import auxiliar as aux
from time import time
import json

def main():
    ficheros = ["g4.json", "g8.json", "g16.json", "g32.json", "g64.json", "g128.json",
            "g256.json","g512.json","g1024.json","g2048.json","g4096.json","g8192.json",
            "g16384.json", "g32768.json","g65536.json"]
    
    t1 = 0
    t2 = 0
    tiempo = 0

    for fichero in ficheros:
        nombreFichero = "sols/"+fichero
        with open(nombreFichero) as f:
            mapa = json.load(f)
            f.close()

        t1 = time()

        aux.realizar_voraz(mapa["grafo"])

        t2 = time()

        tiempo = (t2 - t1)*1000

        print("Fichero: ", fichero, " tiempo: ",tiempo )
        print("\n")

if __name__ == "__main__":
    main()
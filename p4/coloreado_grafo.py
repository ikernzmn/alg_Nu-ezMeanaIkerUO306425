import json

import auxiliar as aux

if __name__ == "__main__":
    n = 32
    # mapa = aux.generar_mapa_grafo(n)
    with open('sols/g65536.json') as f:
        mapa = json.load(f)
        f.close()
    
    solucion = aux.realizar_voraz(mapa["grafo"])

    if solucion:
        print("Solución encontrada:", solucion)
        aux.dibujar_mapa_coloreado(mapa, solucion)
        with open('sols/solucion.json', 'w') as f:
            json.dump(solucion, f)
            f.close()
    else:
        print("No se encontró solución.")

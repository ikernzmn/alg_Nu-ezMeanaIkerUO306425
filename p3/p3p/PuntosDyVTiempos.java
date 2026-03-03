package p3p;

import p3p.PuntosDyV;

public class PuntosDyVTiempos
{
    public static void main(String[] args)
    {
        long t1,t2;
        String[] resultado;
        
	    for (int i = 1024; i <= 10000000; i *= 2)
	    {
            PuntosDyV.generarMatrizPuntosAleatoria(i);

            t1 = System.currentTimeMillis ();

		    resultado = PuntosDyV.BuscarDistanciaMinima();
            System.out.println(resultado[0]);
            System.out.println(resultado[1]);
		
		    t2 = System.currentTimeMillis ();

		    System.out.println (" i="+i+ "**TIEMPO="+(t2-t1));
            
	    }
    }
}
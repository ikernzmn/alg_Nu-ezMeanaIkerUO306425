package p3p;

import p3p.PuntosTrivial;

public class PuntosTrivialTiempos
{
    public static void main(String[] args)
    {
        long t1,t2;
        String[] resultado;
        
	    for (int i = 1024; i <= 1000000; i *= 2)
	    {
            PuntosTrivial.generarMatrizPuntosAleatoria(i);

            t1 = System.currentTimeMillis ();

		    resultado = PuntosTrivial.BuscarDistanciaMinima();
            System.out.println(resultado[0]);
            System.out.println(resultado[1]);
		
		    t2 = System.currentTimeMillis ();

		    System.out.println (" i="+i+ "**TIEMPO="+(t2-t1));
            
	    }
    }
}
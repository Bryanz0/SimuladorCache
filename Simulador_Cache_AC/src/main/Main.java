package main;
import noname.*;
import servicios.*;
public class Main {
	//Clase utilizada para el diseño y pruebas de funcionamiento
	
	public static void main (String[] args){
		Reemplazo r = new Reemplazo();
		MainMemory mm = new MainMemory();
		MissAndHit mh= new MissAndHit();
		mm.llenarMM();
		mm.imprimirMM();
		Cache c = new Cache();
		c.llenarCache(mm);
		c.imprimirCache();
		
		System.out.println("---------------------------------------------------");
		//r.reemplazo(14);
		
		int [] result=new int[2];
		result=mh.result(mm,c);
		
		System.out.println("perdidas"+result[0]);
		System.out.println("exitos"+result[1]);
		String direccion="";
		boolean res=false;
		while (res==false){
		int addressRandom= (int) (Math.random()*mm.getSizeMM()+1);
		String bloque=mm.MainMemory[addressRandom-1];
		res=mh.direccionEnMM(c, bloque);
		direccion=bloque;
		
		}
		System.out.println("Seccion LRU");
		System.out.println("La direccion a remplazar en la memoria cache es: "+direccion);
		r.reemplazoLRU(c, direccion);
		
		c.imprimirCache();
		String word="aa";
		String address=c.cache[(int) (Math.random()*c.getSizeCache())];
		System.out.println("Seccion Write Through");
		System.out.println("Se va a reemplazar los 2 bits de palabra de la direccion: "+address+" por 'aa'");
		r.writeThrough(mm, c, word, address);
		
		mm.imprimirMM();
		c.imprimirCache();
		
	}
	
}

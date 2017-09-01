package noname;
import noname.MainMemory;
import servicios.Int_Hex;
public class Cache {
	//numero de direcciones
	private int numDirecciones=5;
	//numero de direcciones por linea
	private int direccionPorLinea=8;
	//tamaño de la cache
	private int sizeCache=numDirecciones*direccionPorLinea;
	public String[] cache= new String [sizeCache];
	private MainMemory MM= new MainMemory();
	
	//Metodo para llenar la memoria cache con las direcciones de la memoria principal
	public void llenarCache(MainMemory MM){
		String set="";
		
		for(int i=0;i<sizeCache;i++){
			//se agrega un bit de uso que servira para el algoritmo de LRU
			int bitUso= (int) (Math.random()*2+1)-1;
			set=MM.MainMemory[i].substring(0,1);
			cache[i]=set+MM.MainMemory[i].substring(1)+String.valueOf(bitUso);
		}
	}
	//Metodo para imprimir la memoria cache
	public String imprimirCache(){
		String texto="";
		for (int i=0;i<sizeCache;i++){
			texto+=cache[i]+"\t";
			if((i+1)%direccionPorLinea==0)
				texto+="\n";
		}
		return texto;
	}
	public int getSizeCache(){
		return sizeCache;
	}
	
	
}

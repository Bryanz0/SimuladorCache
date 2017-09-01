package noname;
import servicios.Int_Hex;
public class MainMemory {
	//numero de direcciones
	private int numDirecciones=16;
	//numero de direcciones por linea
	private int direccionPorLinea=8;
	private int sizeMM=numDirecciones*direccionPorLinea;
	public  String [] MainMemory= new String [sizeMM];
	
	//Metodo que llena la memoria principal con direcciones creadas
	public void llenarMM (){
		int j=0;
		int tag=0;
	for (int i=0; i<sizeMM; i++){
		int pal=i+j;
		MainMemory[i]=j+Int_Hex.int_to_hexTAG(tag)+Int_Hex.int_to_hex(i)+Int_Hex.int_to_hexWord(pal);
		tag++;
		if((i+1)%direccionPorLinea==0){
			j++;tag=0;}
	}
	}
	
	//Metodo que imprime la memoria principal
	public String imprimirMM(){
		String texto="";
			for (int i=0; i<sizeMM; i++){
			texto+=MainMemory[i]+"\t";
			if((i+1)%direccionPorLinea==0)
				texto+="\n";
		}
			return texto;
	}
	
	public int getSizeMM(){
		return sizeMM;
	}
	
	/*public static void main (String [] args){
		llenarMM();
		imprimirMM();
	}*/
}

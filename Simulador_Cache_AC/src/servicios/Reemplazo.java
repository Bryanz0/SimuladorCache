package servicios;
/**
 * Esta es la clase mas importante del proyecto ya que aqui es donde se realizan todas las 
 * operaciones de reemplazo.
 * El primer Metodo de esta clase utiliza LRU. Con el bit de uso que hemos seteado en la clase
 * de cache ya que este nos servira si existe mas de un bit de uso seteado en 0 para aplicar
 * el reemplazo de tipo cola (FIFO) 
 * El segundo metodo implemeta la politica de escritura write through, reemplazando la direccion
 * simultaneamente en MM y en cache
 * 
 */
import noname.*;
import servicios.*;
public class Reemplazo {
	
	public void reemplazoLRU (Cache c, String direccion){
		//como se utiliza un indice para las direcciones este puede aumentar el tamaño de la direccion
		//por lo que se debe controlar si es mas grande de lo  normal
		String set=direccion.substring(0,1);
		if (direccion.length()>16)
			set=direccion.substring(0,1);
		
		//Una vez controlado el esa parte se procede a obtener el set
		int n=Integer.parseInt(set);
		int bitUso=0;
		//se verifica si existe mas de un bit seteado en 1
		for (int i=0;i<8;i++){
			bitUso=bitUso+Integer.parseInt(c.cache[(n%4)*8+i].substring(c.cache[(n%4)*8+i].length()-1));
		}
		//si existe mas de uno se utiliza el agoritmo FIFO
		if (bitUso>=1){
			for (int i=0;i<8;i++){
				if(Integer.parseInt(c.cache[(n%4)*8+i].substring(c.cache[(n%4)*8+i].length()-1))==0){
					//se controla de nuevo el tamaño de la direccion
					if (direccion.length()>16){
						n=Integer.parseInt(direccion.substring(0,1));
				c.cache[(n%4)*8+i]=(n%4)+Int_Hex.int_to_hexTAG(i)+direccion.substring(7, direccion.length())+"1";
				break;
					}else{
					c.cache[(n%4)*8+i]=(n%4)+Int_Hex.int_to_hexTAG(i)+direccion.substring(6, direccion.length())+"1";	
					break;
					}
				}
			}	
		}else{//Caso contrario
			c.cache[(n%4)*8]=(n%4)+Int_Hex.int_to_hexTAG(1)+direccion.substring(5, direccion.length())+"1";
		}
		
	}
	
	public void writeThrough(MainMemory mm, Cache c, String word, String address){
		//variables que almacenaran la posicion de la direccion a cambiar 
		int posC=0;
		int posM=0;
		//se obtiene la posicion de la direccion a cambiar de la memoria cache
		for(int i=0;i<c.getSizeCache();i++){
			if(c.cache[i].equals(address))
				posC=i;
		}
		//se obtiene la posicion de la direccion a cambiar de la memoria principal
		for(int i=0;i<mm.getSizeMM();i++){
			if(address.substring(1,address.length()-1).equals(mm.MainMemory[i].substring(1)))
				posM=i;
		}
		//se procede a reemplazar tanto en memoria principal como en cache la palabra que nos envian
		mm.MainMemory[posM]=mm.MainMemory[posM].substring(0, mm.MainMemory [posM].length()-2)+word;
		c.cache[posC]=c.cache[posC].substring(0, c.cache[posC].length()-3)+word+"1";
		
		
	}
}

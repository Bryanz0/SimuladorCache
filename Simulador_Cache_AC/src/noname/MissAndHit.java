package noname;
/** Clase de tipo utilitaria que servira para comprobar el exito o la perdidas de la memoria cache
 * la clase cuenta con dos metodos clase resultado y la clase direccionenMM
 * la primera clase lo que hace es generar direcciones aleatorias dentro de la memoria Principal 
 * para verificar si esa direccion existe en la memoria cache para asi contabilizar los exitos
 * y las perdidas.
 * La segunda clase lo que hace es buscar una direccion de MM con la condicion que esta no este
 * la memoria cache. Esta clase sera utilizada para el algoritmo de Write Through
 */
public class MissAndHit {
		
		public int[] result (MainMemory mm,Cache c){
			// aqui se podra modificar el numero de pruebas que se quiera hacer
			int numPruebas=10;
			int[] result=new int[2];
			for(int j=0; j<numPruebas;j++){
				//se genera indices aleatorios
				int addressRandom= (int) (Math.random()*mm.getSizeMM()+1);
				String bloque="";
				//se almacena en el string bloque la direccion de la MM aleatoria
				bloque=mm.MainMemory[addressRandom-1].substring(1);
				
				//Se compara el nuestra direccion aleatoria para verificar si esta en la memoria cache
				for(int i=0;i<c.getSizeCache();i++){
					String bloqueCache=c.cache[i].substring(1,c.cache[i].length()-1);
					if(bloqueCache.equals(bloque)){
						//se contabilizan los resultados 
						result[1]++;
						break;
					}
				}
					result[0]=numPruebas-result[1];
		}
			return result;
		}
		//Metodo auxiliar que sirve para el Write Through
		public boolean direccionEnMM( Cache c,String bloque){
			bloque=bloque.substring(1);
			for(int i=0;i<c.getSizeCache();i++){
				String bloqueCache=c.cache[i].substring(1,c.cache[i].length()-1);
				if(bloqueCache.equals(bloque)){
					return false;
				}
			}
			return true;
			
		}
}





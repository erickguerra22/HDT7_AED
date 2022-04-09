package Dictionary;
/**
 * 
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * @author erick
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean mainEnd = false;
		Dictionary dictionary = new Dictionary();
		
		System.out.println("Bienvenido al diccionario de ingles o frances a espaniol");
		while(!mainEnd) {
			boolean end = false;
			System.out.println("Buscando el archivo diccionario.txt...");
			boolean found = false;
			while(!found) {
				String[] fileContent = null;
				try { //Se encuentra el archivo
					fileContent = FileController.readFile("diccionario");
					System.out.println("El archivo diccionario.txt se ha encontrado y es valido.");
					dictionary.fillDictionary(fileContent);
					found = true;
				} catch (IOException e) { //Si no se encuentra el archivo
					System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo diccionario.txt sea valido y se encuentre en la carpeta del programa.");
					System.out.println("Presione enter para volver a buscar el archivo.");
					scan.nextLine();
				}
			}	
			System.out.println("\nA continuacion se muestra el contenido de ambos diccionarios:");
			System.out.println(dictionary.getEnglish());
			System.out.println(dictionary.getFrench());
			while(!end) {
				String menu = """
						\n1. Agregar una palabra al diccionario.
						2. Eliminar una palabra existente.
						3. Modificar una palabra existente.
						4. Traducir un texto completo.
						5. Salir.""";
				int option = pregunta(menu, 5, scan);
				
				String esp;
				String eng;
				String fren;
				switch(option) { 
				case 1:
					System.out.println("Ingresa la palabra en espaniol:");
					esp = scan.nextLine();
					System.out.println("Ingresa su traduccion a ingles:");
					eng= scan.nextLine();
					System.out.println("Ingresa su traduccion a frances:");
					fren = scan.nextLine();
					System.out.println(dictionary.newWord(esp, eng, fren));
					break;
				case 2:
					System.out.println("Ingresa la palabra en ingles:");
					eng = scan.nextLine();
					System.out.println("Ingresa la palabra en frances:");
					fren = scan.nextLine();
					System.out.println(dictionary.removeWord(eng,fren));
					break;
				case 3:
					System.out.println("Ingresa la palabra actual en ingles");
					eng = scan.nextLine();
					System.out.println("Ingresa la palabra actual en frances");
					fren = scan.nextLine();
					if(dictionary.wordFound(eng,fren)) {
						System.out.println("Ingrese la nueva palabra en espanol (si no desea modificarla, ingrese la que existe actualmente)");
						esp = scan.nextLine();
						System.out.println("Ingrese la nueva palabra en ingles (si no desea modificarla, ingrese la que existe actualmente)");
						eng = scan.nextLine();
						System.out.println("Ingrese la nueva palabra en frances (si no desea modificarla, ingrese la que existe actualmente)");
						fren = scan.nextLine();
						System.out.println(dictionary.updateWord(esp, eng, fren));
					}else 
						System.out.println("La palabra deseada no existe en ninguno de los dos diccionarios.");
					break;
				case 4:
					found = false;
					while(!found) {
						String[] fileContent = null;
						try { //Se encuentra el archivo
							fileContent = FileController.readFile("texto");
							System.out.println("El archivo texto.txt se ha encontrado y es valido.\nTraduciendo...\nTraduccion:\n");
							System.out.println(dictionary.translateText(fileContent));
							found = true;
						} catch (IOException e) { //Si no se encuentra el archivo
							System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo text.txt sea valido y se encuentre en la carpeta del programa.");
							System.out.println("Presione enter para volver a buscar el archivo.");
							scan.nextLine();
						}
					}	
					break;
				case 5:
					System.out.println("Gracias por utilizar el programa!"); 
					end = true;
					mainEnd = true;
					dictionary.updateDictionary();
					break;
				default: //Opcion no valida
					System.out.println("Opcion no valida");
					break;
				}				
			}
		}
	}

	public static int pregunta(String pregunta, int opciones, Scanner scan)
	  {
	      boolean bucle = true;
	      int respuesta = 0;
	      try 
	      {
	          while(bucle)
	          {
	              System.out.println(pregunta);
	              respuesta = scan.nextInt();
	              scan.nextLine();
	              if(respuesta > 0 && respuesta <= opciones) bucle = false;
	              else System.out.println("\nRepuesta no valida.\n");
	          }    
	      } catch (Exception e) {
	          System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
	          respuesta = pregunta(pregunta, opciones, scan);
	      }
	      return respuesta;
	  }
	
	public static int numeroEntero(String pregunta, Scanner scan) {
		boolean bucle = true;
		int num = 0;
		try 
		{
			while(bucle)
			{
				System.out.println(pregunta);
				num = scan.nextInt();
				scan.nextLine();
				if(num > 0) bucle = false;
				else System.out.println("\nRepuesta no valida.\n");
			}    
		} catch (Exception e) {
			scan.nextLine();
			System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
			num = numeroEntero(pregunta, scan);
		}
		return num;
	}

}

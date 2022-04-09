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
				boolean repeat = true;
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
						2. Traducir un texto completo.
						3. Salir.""";
				int option = pregunta(menu, 3, scan);
				
				boolean repeat = true;
				switch(option) { 
				case 1:
					menu = """
							Seleccione un idioma:
							1. Ingles
							2. Frances
							""";
					int language = pregunta(menu,2,scan);
					System.out.println("Ingresa la palabra en espaniol:");
					String newWord = scan.nextLine();
					System.out.println("Ingresa su traduccion al idioma que elegiste:");
					String newTrans = scan.nextLine();
					if(language == 1)
						System.out.println(dictionary.addEnglish(newWord, newTrans));
					else
						System.out.println(dictionary.addFrench(newWord, newTrans));
					break;
				case 2:
					found = false;
					while(!found) {
						String[] fileContent = null;
						repeat = true;
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
				case 3:
					System.out.println("Gracias por utilizar el programa!"); 
					end = true;
					mainEnd = true;
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

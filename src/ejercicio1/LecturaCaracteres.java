package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaCaracteres {
	
	//throw está puesto porque el try y catch me estaba dando un error y la solucion que me dio eclipse 
	//fue que lo sustituyese por eso
    public static void main(String[] args) throws IOException {
    	//para guardar la ruta de los archivos, de esta manera es mas facil usarlo
        String archivoEntrada = "src"+File.separator+"ficheros"+File.separator+"ficheroEntrada.txt";
        String archivoSalida = "src"+File.separator+"ficheros"+File.separator+"ficheroSalida.txt";
        //ahora creo el contador y 
        int contadorCaracteresNumericos = contarCaracteresNumericos(archivoEntrada);
        System.out.println("Número de caracteres numéricos: " + contadorCaracteresNumericos);
        //llamo al metodo para guardar los archivos y meto la ruta del archivo de entrada y el numero del contador
        //para que escriba en el fichero de salida la cantidad de numeros
        guardarEnArchivo(contadorCaracteresNumericos, archivoSalida);
    }

    public static int contarCaracteresNumericos(String archivo) {
        FileReader lectorFichero = null;
        int contador = 0;

        try {
        	//que lea el archivo de la ruta que le he pasado
            lectorFichero = new FileReader(archivo);
            //esto es para que cuente los numeros que hay en el fichero
            int caracter;
            while ((caracter = lectorFichero.read()) != -1) {
            	//para que convierta la informacion guardada en el int a un caracter
                char charCaracter = (char) caracter;
                if (Character.isDigit(charCaracter)) {
                	//lo guarda en el contador
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//para que deje de leer el archivo
            try {
                if (lectorFichero != null) {
                    lectorFichero.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contador;
    }

    public static void guardarEnArchivo(int contador, String archivo) {
        FileWriter escritorFichero = null;

        try {
        	//creo un FileWriter con la ruta del archivo de salida
            escritorFichero = new FileWriter(archivo);
            //y escribe en el archivo de salida cuantos caracteres de tipo numerico hay
            escritorFichero.write("Número de caracteres numéricos: " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//para que deje de escribir
            try {
                if (escritorFichero != null) {
                    escritorFichero.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

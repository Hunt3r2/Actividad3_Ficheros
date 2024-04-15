package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaCaracteres {
	
	//throw está puesto porque el try y catch me estaba dando un error y la solucion que me dio fue que lo sustituyese por eso
    public static void main(String[] args) throws IOException {
    	//creo los archivos
        String archivoEntrada = "ficheros/ficheroEntrada.txt";
        String archivoSalida = "ficheros/ficheroSalida.txt";
        //ahora creo el contador
        int contadorCaracteresNumericos = contarCaracteresNumericos(archivoEntrada);
        System.out.println("Número de caracteres numéricos: " + contadorCaracteresNumericos);
        //llamo al metodo para guardar los archivos
        guardarEnArchivo(contadorCaracteresNumericos, archivoSalida);
    }

    public static int contarCaracteresNumericos(String archivo) {
        FileReader lectorFichero = null;
        BufferedReader lectorBuffered = null;
        int contador = 0;

        try {
        	//que lea el archivo
            lectorFichero = new FileReader(archivo);
            //se supone que el BUfferedReader es mas eficiente a la hora de leer archivos
            lectorBuffered = new BufferedReader(lectorFichero);
            //esto es para que cuente los numeros que hay en el fichero
            int caracter;
            while ((caracter = lectorBuffered.read()) != -1) {
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
                if (lectorBuffered != null) {
                    lectorBuffered.close();
                }
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
        BufferedWriter escritorBuffered = null;

        try {
        	//lo guarda en el string
            escritorFichero = new FileWriter(archivo);
            escritorBuffered = new BufferedWriter(escritorFichero);
            //y escribe en el archivo de salida cuantos caracteres de tipo numerico hay
            escritorBuffered.write("Número de caracteres numéricos: " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//para que deje de escribir
            try {
                if (escritorBuffered != null) {
                    escritorBuffered.close();
                }
                if (escritorFichero != null) {
                    escritorFichero.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

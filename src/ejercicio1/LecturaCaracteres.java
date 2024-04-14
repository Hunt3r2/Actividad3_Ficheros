package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaCaracteres {
	
	//throw está puesto porque el try y catch me estaba dando un error y la solucion que me dio fue que lo sustituyese por eso
    public static void main(String[] args) throws IOException {
    	//creo los archivos
        String archivoEntrada = "ficheroEntrada.txt";
        String archivoSalida = "ficheroSalida.txt";
        //ahora creo el contador
        int contadorCaracteresNumericos = contarCaracteresNumericos(archivoEntrada);
        System.out.println("Número de caracteres numéricos: " + contadorCaracteresNumericos);
        //llamo al metodo para guardar los archivos
        guardarEnArchivo(contadorCaracteresNumericos, archivoSalida);
    }

    public static int contarCaracteresNumericos(String archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        int contador = 0;

        try {
        	//que lea el archivo
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //esto es para que cuente los numeros que hay en el fichero
            int caracter;
            while ((caracter = br.read()) != -1) {
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
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contador;
    }

    public static void guardarEnArchivo(int contador, String archivo) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
        	//lo guarda en el string
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);
            //y muestra por pantalla cuantos caracteres de tipo numerico hay
            bw.write("Número de caracteres numéricos: " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//para que deje de escribir
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

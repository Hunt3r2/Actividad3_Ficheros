package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaCaracteres {

    public static void main(String[] args) throws IOException {
        String archivoEntrada = "ficheroEntrada.txt";
        String archivoSalida = "ficheroSalida.txt";

        int contadorCaracteresNumericos = contarCaracteresNumericos(archivoEntrada);
        System.out.println("Número de caracteres numéricos: " + contadorCaracteresNumericos);

        guardarEnArchivo(contadorCaracteresNumericos, archivoSalida);
        System.out.println("Información guardada en " + archivoSalida);
    }

    public static int contarCaracteresNumericos(String archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        int contador = 0;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            int caracter;
            while ((caracter = br.read()) != -1) {
                char charCaracter = (char) caracter;
                if (Character.isDigit(charCaracter)) {
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);
            bw.write("Número de caracteres numéricos: " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

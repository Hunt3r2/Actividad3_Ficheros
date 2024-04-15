package ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FicheroInterfaz extends JFrame {
    private JTextArea textArea;

    public FicheroInterfaz() {
        setTitle("Editor de Texto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton guardarButton = new JButton("Guardar Texto");
        JButton textoGuardadoButton = new JButton("Texto Guardado");

        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarTexto();
            }
        });

        textoGuardadoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTextoGuardado();
            }
        });

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);
        buttonPanel.add(textoGuardadoButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void guardarTexto() {
        try {
        	//primero creo el escritor de texto
            FileWriter escritorTexto = new FileWriter("ficheros/texto.txt", true);
            BufferedWriter escritorBuffered = new BufferedWriter(escritorTexto);
            //pongo en el archivo de texto el texto escrito
            escritorBuffered.write(textArea.getText());
            escritorBuffered.newLine();
            escritorBuffered.close();
            // pongo el cuadro de texto en blanco después de guardar
            textArea.setText(""); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarTextoGuardado() {
        try {
        	//leo el archivo
            FileReader lectorTexto = new FileReader("ficheros/texto.txt");
            BufferedReader lectorBuffered = new BufferedReader(lectorTexto);
            JTextArea textAreaMostrado = new JTextArea();
            //creo un scroll pane y dentro de ahí meto el texto guardado
            JScrollPane scrollPane = new JScrollPane(textAreaMostrado);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            String linea;
            while ((linea = lectorBuffered.readLine()) != null) {
                textAreaMostrado.append(linea + "\n");
            }

            lectorBuffered.close();

            //ahora creo la ventana de texto guardado
            JFrame ventanaTextoGuardado = new JFrame("Texto Guardado");
            ventanaTextoGuardado.setSize(400, 300);
            ventanaTextoGuardado.setLocationRelativeTo(this);
            ventanaTextoGuardado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaTextoGuardado.add(scrollPane);
            ventanaTextoGuardado.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FicheroInterfaz ventana = new FicheroInterfaz();
                ventana.setVisible(true);
            }
        });
    }
}

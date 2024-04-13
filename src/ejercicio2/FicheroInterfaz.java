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

        // Crear el JTextArea
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Crear botones
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

        // Crear contenedor y agregar componentes
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
            FileWriter fw = new FileWriter("texto.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textArea.getText());
            bw.newLine();
            bw.close();
            textArea.setText(""); // Limpiar el JTextArea después de guardar
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarTextoGuardado() {
        try {
            FileReader fr = new FileReader("texto.txt");
            BufferedReader br = new BufferedReader(fr);
            JTextArea textAreaMostrado = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textAreaMostrado);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            String line;
            while ((line = br.readLine()) != null) {
                textAreaMostrado.append(line + "\n");
            }

            br.close();

            // Crear la ventana de texto guardado
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

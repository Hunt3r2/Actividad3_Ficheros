package ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.LineBorder;

public class FicheroInterfaz extends JFrame {
    private JTextArea textArea;

    public FicheroInterfaz() {
    	//para que cuando se abra, no lo puedan cambiar de tamaño.
    	setResizable(false);
        setTitle("Editor de Texto");
        setSize(536, 306);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setBackground(new Color(224, 255, 255));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new LineBorder(new Color(176, 196, 222), 3, true));
        scrollPane.setBounds(0, 0, 520, 228);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton btnGuardar = new JButton("Guardar Texto");
        btnGuardar.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        JButton btnTextoGuardado = new JButton("Texto Guardado");
        btnTextoGuardado.setFont(new Font("Century Gothic", Font.PLAIN, 11));

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarTexto();
            }
        });

        btnTextoGuardado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTextoGuardado();
            }
        });

        Container container = getContentPane();
        getContentPane().setLayout(null);
        container.add(scrollPane);
        //le puse colores para que sea mas agradable a la vista
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new LineBorder(new Color(0, 206, 209), 3, true));
        buttonPanel.setBounds(0, 228, 520, 39);
        buttonPanel.setBackground(new Color(173, 216, 230));
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnTextoGuardado);
        container.add(buttonPanel);
    }

    private void guardarTexto() {
        try {
        	//primero creo el escritor de texto
            FileWriter escritorTexto = new FileWriter("src"+File.separator+"ficheros"+File.separator+"texto.txt", true);
            //el buffered lo uso esta vez porque hace que sea mas eficaz a la hora de leer archivos
            BufferedWriter escritorBuffered = new BufferedWriter(escritorTexto);
            //pongo en el archivo de texto el texto escrito
            escritorBuffered.write(textArea.getText());
            escritorBuffered.newLine();
            escritorBuffered.close();
            //pongo el cuadro de texto en blanco después de guardar
            textArea.setText(""); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarTextoGuardado() {
        try {
        	//leo el archivo
            FileReader lectorTexto = new FileReader("src"+File.separator+"ficheros" + File.separator + "texto.txt");
            BufferedReader lectorBuffered = new BufferedReader(lectorTexto);
            JTextArea textAreaMostrado = new JTextArea();
            //creo un scroll pane y dentro de ahí meto el texto guardado
            JScrollPane scrollPane = new JScrollPane(textAreaMostrado);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            String linea;
            //el bucle se ejecuta hasta que encuentra alguna una linea en null
            while ((linea = lectorBuffered.readLine()) != null) {
            	//mete la linea de texto y salta a la siguiente linea
                textAreaMostrado.append(linea + "\n");
            }

            lectorBuffered.close();

            //ahora creo la ventana de texto guardado
            JFrame ventanaTextoGuardado = new JFrame("Texto Guardado");
            ventanaTextoGuardado.setSize(400, 300);
            //para que aparezca encima de la ventana del texto
            ventanaTextoGuardado.setLocationRelativeTo(this);
            //para que si se cierra la ventana de mostrar texto, solo se cierra esa ventana y no la otra
            ventanaTextoGuardado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //para meter el scroll pane en la ventana de mostrar, de esta manera, si el texto es largo, que se pueda bajar
            ventanaTextoGuardado.getContentPane().add(scrollPane);
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

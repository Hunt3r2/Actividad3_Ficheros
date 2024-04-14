package ejercicio3;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Tres_en_raya_fichero extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Tres_en_raya juego;
    private ImageIcon jugador1Icon;
    private ImageIcon jugador2Icon;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tres_en_raya_fichero frame = new Tres_en_raya_fichero();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Tres_en_raya_fichero() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJuego();
            }
        });
        btnJugar.setBounds(75, 50, 100, 30);
        contentPane.add(btnJugar);

        JButton btnHistorial = new JButton("Historial");
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verHistorial();
            }
        });
        btnHistorial.setBounds(250, 50, 100, 30);
        contentPane.add(btnHistorial);
    }

    private void abrirJuego() {
    	//fotos del jugador 1 y el jugador 2
        jugador1Icon = new ImageIcon(getClass().getResource("/ejercicio3/imagenes/Keanu_Reeves_2019.jpg"));
        jugador2Icon = new ImageIcon(getClass().getResource("/ejercicio3/imagenes/nicolas-cage-net-worth-through-the-years.jpg"));
        //esto es para que se escalen las fotos al tamaño del boton
        Image jugador1Image = jugador1Icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image jugador2Image = jugador2Icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon jugador1Icon = new ImageIcon(jugador1Image);
        ImageIcon jugador2Icon = new ImageIcon(jugador2Image);
        juego = new Tres_en_raya(this, jugador1Icon, jugador2Icon);
        juego.mostrarVentana();
    }



    private void verHistorial() {
    	//creamos el archivo del historial
        File archivo = new File("historial.txt");
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {
        	//
            String linea;
            while ((linea = br.readLine()) != null) {
                model.addElement(linea);
            }
        } catch (IOException e) {
        	//si por alguna razon no se puede leer el historial, que avise al jugador
            JOptionPane.showMessageDialog(this, "Error al leer el historial", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(list);
        JOptionPane.showMessageDialog(this, scrollPane, "Historial de partidas", JOptionPane.PLAIN_MESSAGE);
    }

    public void cerrarJuego() {
        juego.dispose();
        juego = null;
    }
}

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Tres_en_raya_fichero.class.getResource("/ejercicio3/imagenes/nicolas-cage-net-worth-through-the-years.jpg")));
    	setResizable(false);
    	setTitle("Tres en raya 100% real no fake un link mega");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setFocusable(false);
        btnJugar.setBorder(new LineBorder(new Color(0, 128, 128), 3, true));
        btnJugar.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJuego();
            }
        });
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBackground(new Color(128, 0, 0));
        panel.setBounds(111, 44, 203, 50);
        contentPane.add(panel);
        
        JLabel lblTitulo = new JLabel("Menu Tres en raya");
        lblTitulo.setForeground(new Color(255, 255, 255));
        panel.add(lblTitulo);
        lblTitulo.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        btnJugar.setBounds(167, 105, 100, 30);
        contentPane.add(btnJugar);

        JButton btnHistorial = new JButton("Historial");
        btnHistorial.setFocusable(false);
        btnHistorial.setBorder(new LineBorder(new Color(0, 139, 139), 3, true));
        btnHistorial.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verHistorial();
            }
        });
        btnHistorial.setBounds(167, 146, 100, 30);
        contentPane.add(btnHistorial);
        //el fondo del menú
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon(Tres_en_raya_fichero.class.getResource("/ejercicio3/imagenes/fondo.gif")));
        lblFondo.setBounds(0, 0, 434, 261);
        contentPane.add(lblFondo);
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
        juego = new Tres_en_raya(this, jugador1Icon, jugador2Icon, null);
        juego.mostrarVentana();
    }



    private void verHistorial() {
    	//creamos el archivo del historial
        File archivo = new File("src"+File.separator+"ficheros"+File.separator+"historial.txt");
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);

        try (FileReader lectorFichero = new FileReader(archivo);
             BufferedReader lectorBuffered = new BufferedReader(lectorFichero)) {
        	//este string lo utilizo para almacenar las lineas de texto que lee
            String linea;
            //mientras la linea no sea igual a null, lo que significa que no esté vacia
            while ((linea = lectorBuffered.readLine()) != null) {
            	//que se añada a la lista
                model.addElement(linea);
            }
        } catch (IOException e) {
        	//si por alguna razon no se puede leer el historial, que avise al jugador
            JOptionPane.showMessageDialog(this, "Error al leer el historial", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(list);
        JOptionPane.showMessageDialog(this, scrollPane, "Historial de partidas", JOptionPane.PLAIN_MESSAGE);
    }
}

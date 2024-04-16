package ejercicio3;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Toolkit;

public class Tres_en_raya extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private boolean turno = true;
    private ImageIcon jugador1Icon, jugador2Icon;
    private JLabel lblNewLabel;
    private JButton[][] botones = new JButton[3][3];
    private Tres_en_raya_fichero tres_fichero;
    private JLabel lblLebron;
    private JLabel lblLebron_1;
    private JLabel lblLebron_2;
    private JLabel lblLebron_3;

    public Tres_en_raya(Tres_en_raya_fichero tres_fichero, ImageIcon jugador1Icon, ImageIcon jugador2Icon) {
    	//para que no pueda cambiarse el tamaño de la ventana
    	setResizable(false);
    	//un icono bien fresco para la ventana
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Tres_en_raya.class.getResource("/ejercicio3/imagenes/nicolas-cage-net-worth-through-the-years.jpg")));
    	setTitle("Tres en raya");
    	this.jugador1Icon = jugador1Icon;
        this.jugador2Icon = jugador2Icon;
    	
        this.tres_fichero = tres_fichero;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 834, 604);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(135, 206, 250), 4, true));
        panel.setBounds(122, 89, 576, 414);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(3, 3, 0, 0));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                panel.add(botones[i][j]);
            }
        }
        this.lblNewLabel = new JLabel("");
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        lblNewLabel.setBounds(361, -17, 171, 140);
        contentPane.add(lblNewLabel);
        
        lblLebron = new JLabel("");
        lblLebron.setBorder(new LineBorder(new Color(173, 216, 230), 3, true));
        lblLebron.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/LeBronJames-YouAreMySunshineJamaicanVersion-ezgif.com-resize.gif")));
        lblLebron.setBounds(10, 11, 102, 100);
        contentPane.add(lblLebron);
        
        lblLebron_1 = new JLabel("");
        lblLebron_1.setBorder(new LineBorder(new Color(173, 216, 230), 3, true));
        lblLebron_1.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/LeBronJames-YouAreMySunshineJamaicanVersion-ezgif.com-resize.gif")));
        lblLebron_1.setBounds(706, 11, 102, 100);
        contentPane.add(lblLebron_1);
        
        lblLebron_2 = new JLabel("");
        lblLebron_2.setBorder(new LineBorder(new Color(173, 216, 230), 3, true));
        lblLebron_2.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/LeBronJames-YouAreMySunshineJamaicanVersion-ezgif.com-resize.gif")));
        lblLebron_2.setBounds(706, 454, 102, 100);
        contentPane.add(lblLebron_2);
        
        lblLebron_3 = new JLabel("");
        lblLebron_3.setBorder(new LineBorder(new Color(173, 216, 230), 3, true));
        lblLebron_3.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/LeBronJames-YouAreMySunshineJamaicanVersion-ezgif.com-resize.gif")));
        lblLebron_3.setBounds(10, 454, 102, 100);
        contentPane.add(lblLebron_3);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(173, 216, 230));
        panel_1.setBorder(new LineBorder(new Color(0, 206, 209), 3, true));
        panel_1.setBounds(10, 122, 102, 321);
        contentPane.add(panel_1);
        
        JLabel lblEsqueleto = new JLabel("");
        lblEsqueleto.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/aimded-ezgif.com-resize.gif")));
        panel_1.add(lblEsqueleto);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(173, 216, 230));
        panel_1_1.setBorder(new LineBorder(new Color(0, 206, 209), 3, true));
        panel_1_1.setBounds(708, 122, 102, 321);
        contentPane.add(panel_1_1);
        
        JLabel lblEsqueleto_1 = new JLabel("");
        lblEsqueleto_1.setIcon(new ImageIcon(Tres_en_raya.class.getResource("/ejercicio3/imagenes/aimded-ezgif.com-resize.gif")));
        panel_1_1.add(lblEsqueleto_1);
        //pequeño boton que creé si no te gustan las imagenes
        JButton btnNewButton = new JButton("Quitar imagenes");
        btnNewButton.setBackground(new Color(240, 255, 255));
        btnNewButton.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
        btnNewButton.setFocusable(false);
        btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                
                if(btnNewButton.getText()=="Mostrar imagenes") {
                	btnNewButton.setText("Quitar imagenes");
                	panel_1.setVisible(true);
            		panel_1_1.setVisible(true);
                	lblLebron.setVisible(true);
                    lblLebron_2.setVisible(true);
                    lblLebron_3.setVisible(true);
                    lblLebron_1.setVisible(true);
                }else {
                	panel_1.setVisible(false);
            		panel_1_1.setVisible(false);
                    lblLebron_3.setVisible(false);
                    lblLebron_1.setVisible(false);
                    lblLebron_2.setVisible(false);
                    lblLebron.setVisible(false);
                    btnNewButton.setText("Mostrar imagenes");
                }
        		
        	}
        });
      
        
        btnNewButton.setBounds(340, 514, 140, 23);
        contentPane.add(btnNewButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    public void cerrarJuego() {
        tres_fichero.cerrarJuego();
    }

    public void Pasarturno() {
        turno = !turno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        if (turno) {
        	//cuando cambie el turno, que cambie tanto la etiqueta de arriba de texto, como el icono
            boton.setIcon(this.jugador1Icon);
            this.lblNewLabel.setText("Le toca al jugador 2");
        } else {
            boton.setIcon(this.jugador2Icon);
            this.lblNewLabel.setText("Le toca al jugador 1");
        }
        boton.setEnabled(false);
        quienGana();
        Pasarturno();
    }

    public void quienGana() {
        for (int i = 0; i < 3; i++) {
        	//este for verifica las filas, si los iconos en dichas posiciones son iguales, que muestre el mensaje de victoria
            if (botones[i][0].getIcon() == botones[i][1].getIcon() && botones[i][0].getIcon() == botones[i][2].getIcon()
                    && botones[i][0].getIcon() != null) {
                mostrarMensajeVictoria();
                return;
            }
        }
        //este for verifica las columnas, como en el de arriba, si los iconos en dichas posiciones son iguales, que muestre el mensaje de victoria
        for (int j = 0; j < 3; j++) {
            if (botones[0][j].getIcon() == botones[1][j].getIcon() && botones[0][j].getIcon() == botones[2][j].getIcon()
                    && botones[0][j].getIcon() != null) {
                mostrarMensajeVictoria();
                return;
            }
        }
        //esto es para que lo verifique diagonalmente, si los iconos en dichas posiciones son iguales, que muestre el mensaje de victoria
        if (botones[0][0].getIcon() == botones[1][1].getIcon() && botones[0][0].getIcon() == botones[2][2].getIcon()
                && botones[0][0].getIcon() != null) {
            mostrarMensajeVictoria();
            return;
        }

        if (botones[0][2].getIcon() == botones[1][1].getIcon() && botones[0][2].getIcon() == botones[2][0].getIcon()
                && botones[0][2].getIcon() != null) {
            mostrarMensajeVictoria();
            return;
        }
    }

    private void mostrarMensajeVictoria() {
        if (turno) {
            JOptionPane.showMessageDialog(null, "Gana el jugador 1", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            // para guardar resultado en historial
            guardarResultado("Gana el jugador 1");
            resetearJuego();
        } else {
            JOptionPane.showMessageDialog(null, "Gana el jugador 2", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            // para guardar resultado en historial
            guardarResultado("Gana el jugador 2");
            resetearJuego();
        }
    }

    public void resetearJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	//pone el icono a null y vuelve a activar los botones que estaban presionados
                botones[i][j].setIcon(null);
                botones[i][j].setEnabled(true);
            }
        }

        turno = true;

        lblNewLabel.setText("Le toca al jugador 1");
    }

    private void guardarResultado(String resultado) {
    	//creo el archivo, meto la ruta del archivo y meto la linea recibida que 
    	//mando desde el metodo de mostrar el mensaje de victoria en el historial
        File archivo = new File("src"+File.separator+"ficheros" + File.separator + "historial.txt");
        try (FileWriter escritorFichero = new FileWriter(archivo, true)) {
        	// el resultado con \n es para que cambie de linea cada vez que escriba
            escritorFichero.write(resultado + "\n");
        } catch (IOException e) {
        	//si por alguna razon no se puede guardar el resultado, que salga un mensaje de error al jugador
            JOptionPane.showMessageDialog(this, "Error al guardar el resultado en el historial", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

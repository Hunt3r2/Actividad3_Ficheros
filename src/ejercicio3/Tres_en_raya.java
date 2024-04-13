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

public class Tres_en_raya extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private boolean turno = true;
    private ImageIcon jugador1, jugador2;
    private JLabel lblNewLabel;
    private JButton[][] botones = new JButton[3][3];
    private Tres_en_raya_fichero tres_fichero;

    public Tres_en_raya(Tres_en_raya_fichero tres_fichero) {
    	
    	
        this.tres_fichero = tres_fichero;
        this.jugador1 = new ImageIcon("C:\\Users\\david\\Downloads\\CRUZ.png");
        this.jugador2 = new ImageIcon("C:\\Users\\david\\Downloads\\circulo.png");

        Image image1 = jugador1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image image2 = jugador2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.jugador1 = new ImageIcon(image1);
        this.jugador2 = new ImageIcon(image2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 834, 604);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(64, 0, 128)));
        panel.setBounds(150, 89, 576, 414);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(3, 3, 0, 0));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                panel.add(botones[i][j]);
            }
        }
        this.lblNewLabel = new JLabel("turno de Jugador 1");
        lblNewLabel.setBounds(361, -17, 171, 140);
        contentPane.add(lblNewLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarJuego();
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
            boton.setIcon(this.jugador1);
            this.lblNewLabel.setText("Ronda de jugador 1");
        } else {
            boton.setIcon(this.jugador2);
            this.lblNewLabel.setText("Ronda de jugador 2");
        }
        boton.setEnabled(false);
        quienGana();
        Pasarturno();
    }

    public void quienGana() {
        for (int i = 0; i < 3; i++) {
            if (botones[i][0].getIcon() == botones[i][1].getIcon() && botones[i][0].getIcon() == botones[i][2].getIcon()
                    && botones[i][0].getIcon() != null) {
                mostrarMensajeVictoria();
                return;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (botones[0][j].getIcon() == botones[1][j].getIcon() && botones[0][j].getIcon() == botones[2][j].getIcon()
                    && botones[0][j].getIcon() != null) {
                mostrarMensajeVictoria();
                return;
            }
        }
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
            JOptionPane.showMessageDialog(null, "Victoria de Jugador 1", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            // Guardar resultado en historial
            guardarResultado("Victoria de Jugador 1");
            resetearJuego();
        } else {
            JOptionPane.showMessageDialog(null, "Victoria de Jugador 2", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            // Guardar resultado en historial
            guardarResultado("Victoria de Jugador 2");
            resetearJuego();
        }
    }

    public void resetearJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setIcon(null);
                botones[i][j].setEnabled(true);
            }
        }

        turno = true;

        lblNewLabel.setText("Turno de Jugador 1");
    }

    private void guardarResultado(String resultado) {
        File archivo = new File("historial.txt");
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(resultado + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el resultado en el historial", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

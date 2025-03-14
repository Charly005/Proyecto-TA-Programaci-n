package App_Paciente.Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Style.EstilosUI;

public class Recomendaciones extends JPanel {
    JPanel pnlPrincipal;
    JPanel pnlElementos;
    JPanel pnlFlow;
    JPanel e1, e2, e3;
    JLabel borde1, borde2, borde3, borde4;
    JLabel l1;
    JLabel img;
    JLabel icono;

    JLabel txt1, txt2, txt3;
    JLabel p1, p2, p3;

    public Recomendaciones() {
        this.setLayout(new BorderLayout());
        // this.setSize(800, 600);
        init();
        this.setVisible(true);
    }

    private static JPanel crearTarjeta(Color color, String titulo, String texto) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new GridLayout(2, 1));
        tarjeta.setBackground(color);
        tarjeta.setPreferredSize(new Dimension(300, 300));
        tarjeta.setMinimumSize(new Dimension(200, 200));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        EstilosUI.aplicarEstiloSubtitulo(lblTitulo); //ESTILO TITULO
        JLabel lblTexto = new JLabel(texto, SwingConstants.CENTER);

        tarjeta.add(lblTitulo);
        tarjeta.add(lblTexto);

        return tarjeta;
    }

    public void init() {

        // label panel superior
        img = new JLabel();
        img.setText("LOGOTIPO");

        icono = new JLabel();
        icono.setText("ICONO");

        // Panel Principal
        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new BorderLayout());
        // pnlPrincipal.setBackground(Color.LIGHT_GRAY);

        // Panel elementos
        pnlElementos = new JPanel();
        pnlElementos.setLayout(new GridBagLayout());
        pnlElementos.setOpaque(false);
        pnlFlow = new JPanel();
        pnlFlow.setOpaque(false);
        pnlFlow.setLayout(new GridBagLayout());
        // pnlFlow.setBackground(Color.WHITE);

        // Flow } e1,e2,e3
        e1 = crearTarjeta(Color.BLUE, "ALIMENTOS", "PARRAFO");
        e2 = crearTarjeta(Color.BLUE, "ALIMENTOS", "PARRAFO");
        e3 = crearTarjeta(Color.BLUE, "ALIMENTOS", "PARRAFO");

        Dimension minSize = new Dimension(150, 300);
        e1.setMinimumSize(minSize);
        e2.setMinimumSize(minSize);
        e3.setMinimumSize(minSize);

        // Agregó al panel principal
        pnlPrincipal.add(pnlElementos, "Center");

        GridBagConstraints centrado = new GridBagConstraints();
        centrado.gridx = 0;
        centrado.gridy = 0;
        centrado.weightx = 1;
        centrado.weighty = 1;
        centrado.anchor = GridBagConstraints.CENTER;
        centrado.fill = GridBagConstraints.BOTH;

        pnlElementos.add(pnlFlow, centrado);

        // agregar label en elementos para bordes de e1,e2,e
        agregarTarjeta(pnlFlow, e1, 0);
        agregarTarjeta(pnlFlow, e2, 1);
        agregarTarjeta(pnlFlow, e3, 2);

        // AGREGAR ESTILOS
        EstilosUI.aplicarEstiloPanelPrincipal(pnlPrincipal);
        

        // agg el widget
        this.add(pnlPrincipal, "Center");

    }

    private void agregarTarjeta(JPanel panel, JPanel tarjeta, int gridx) {
        GridBagConstraints cHorizontal = new GridBagConstraints();
        cHorizontal.gridx = gridx;
        cHorizontal.gridy = 0;
        cHorizontal.weightx = 1;
        cHorizontal.weighty = 1.0;
        cHorizontal.anchor = GridBagConstraints.CENTER;
        cHorizontal.fill = GridBagConstraints.NONE;
        cHorizontal.insets = new Insets(5, 5, 5, 5);

        panel.add(tarjeta, cHorizontal);

    }

}
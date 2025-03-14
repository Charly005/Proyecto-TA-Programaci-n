package Style;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EstilosUI {
    // Colores principales
    public static final Color COLOR_PRINCIPAL = new Color(42, 109, 176); // #2A6DB0
    public static final Color COLOR_SECUNDARIO = new Color(203, 203, 203); // rgb(203, 203, 203)
    public static final Color COLOR_FONDOPANEL = new Color(255, 255, 255); // rgb(255, 255, 255)
    public static final Color COLOR_BOTON = new Color(76, 156, 207); // #rgb(76, 156, 207)
    public static final Color COLOR_BOTON_HOVER = new Color(0, 153, 255); // #rgb(0, 153, 255)
    public static final Color COLOR_TEXTO = new Color(51, 51, 51); // #333

    public static final Color COLOR_BTN_RESALTAR = new Color(33, 143, 216);// #rgb(33, 143, 216)
    public static final Color COLOR_BTN_MENU = new Color(7, 71, 161);// rgb(7, 71, 161)

    // Fuente general
    public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO = new Font("Arial", Font.BOLD, 16);
    public static final Font FUENTE_TEXTO = new Font("Arial", Font.PLAIN, 14);

    // Método para aplicar estilos a botones
    public static void aplicarEstiloBoton(JButton boton) {
        boton.setFont(FUENTE_TEXTO);
        boton.setBackground(COLOR_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(COLOR_BOTON, 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BOTON_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BOTON);
            }
        });
    }

    // Botón menú
    public static void aplicarEstiloBotonMenu(JButton boton) {
        boton.setFont(FUENTE_TEXTO);
        boton.setBackground(COLOR_BTN_MENU);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(COLOR_BTN_MENU, 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BTN_RESALTAR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BTN_MENU);
            }
        });
    }

    // Método para aplicar estilos a etiquetas
    public static void aplicarEstiloTitulo(JLabel label) {
        label.setFont(FUENTE_TITULO);
        label.setForeground(COLOR_TEXTO);
    }

    public static void aplicarEstiloSubtitulo(JLabel label) {
        label.setFont(FUENTE_SUBTITULO);
        label.setForeground(COLOR_TEXTO);
    }

    public static void aplicarEstiloTexto(JLabel label) {
        label.setFont(FUENTE_TEXTO);
        label.setForeground(COLOR_TEXTO);
    }

    // Método para aplicar estilos a un JTextField
    public static void aplicarEstiloCampoTexto(JTextField campo) {
        campo.setFont(FUENTE_TEXTO);
        // campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        campo.setBackground(COLOR_SECUNDARIO);
    }

    // Método para aplicar estilos a JTextArea (chat o comentarios)
    public static void aplicarEstiloAreaTexto(JTextArea area) {
        area.setFont(FUENTE_TEXTO);
        area.setBorder(BorderFactory.createLineBorder(COLOR_PRINCIPAL, 2));
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    // Método para aplicar estilos a un JPanel
    public static void aplicarEstiloPanelPrincipal(JPanel panel) {
        panel.setBackground(COLOR_FONDOPANEL);
    }

    public static void aplicarEstiloPanelSecundario(JPanel panel) {
        panel.setBackground(COLOR_SECUNDARIO);
    }

    public static void panelApp_Bar(JPanel panel) {
        panel.setBackground(COLOR_BTN_MENU);
    }

}
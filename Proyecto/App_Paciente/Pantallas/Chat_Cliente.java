package App_Paciente.Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Style.EstilosUI;

public class Chat_Cliente extends JPanel implements ActionListener, KeyListener {
    JPanel container_chat;
    JScrollPane scroll_chat;
    JTextField text;
    JButton enviar;
    JButton adjuntar;
    FileDialog documentos_adjuntos;
    ImageIcon enviar_icono;

    public Chat_Cliente() {
        init();
    }

    public void init() {
        container_chat = new JPanel();
        container_chat.setName("Container_Chat");
        scroll_chat = new JScrollPane(container_chat);
        text = new JTextField();
        enviar = new JButton();
        enviar_icono = new ImageIcon(getClass().getResource("/App_Paciente/img/enviar.png"));
        Image enviar_escalado = enviar_icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        enviar.setIcon(new ImageIcon(enviar_escalado));
        enviar.setPreferredSize(new Dimension(50, 50));
        adjuntar = new JButton("📎\r\n" + //
                "Adjuntar");
        documentos_adjuntos = new FileDialog((Frame) SwingUtilities.getWindowAncestor(this), "Select an  archive",
                FileDialog.LOAD);

        container_chat.setLayout(new GridBagLayout());

        scroll_chat.setPreferredSize(new Dimension(300, 400));
        scroll_chat.getVerticalScrollBar().setUnitIncrement(16);

        enviar.addActionListener(this);
        adjuntar.addActionListener(this);

        JPanel acciones = new JPanel();
        // acciones.setBackground(Color.LIGHT_GRAY);//#rgba(255, 255, 255, 0)
        acciones.setLayout(new GridBagLayout());
        GridBagConstraints actions = new GridBagConstraints();

        actions.gridx = 0;
        actions.gridy = 0;
        actions.weightx = 0.09;
        actions.fill = GridBagConstraints.BOTH;
        acciones.add(adjuntar, actions);

        actions.gridx = 1;
        actions.weightx = 0.8;
        acciones.add(text, actions);

        actions.gridx = 2;
        actions.weightx = 0.1;
        acciones.add(enviar, actions);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(scroll_chat, gbc);

        gbc.gridy = 1;
        gbc.weightx = 0.1;
        this.add(acciones, gbc);

        // APLICAR ESTILOS
        EstilosUI.aplicarEstiloPanelPrincipal(container_chat);
        EstilosUI.aplicarEstiloPanelPrincipal(acciones);
        EstilosUI.aplicarEstiloCampoTexto(text);

        text.addKeyListener(this);
        text.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enviar) {
            String message = text.getText().trim();
            if (!message.isEmpty()) {
                JLabel mensaje = new JLabel(message);
                mensaje.setName("mensaje_dowloaded");
                agregarMessage(mensaje, true);
                text.setText("");
                mensaje.setText("Mensaje del doctor");
                ;
                Timer timer = new Timer(1000, evt -> agregarMessage(mensaje, false));
                timer.setRepeats(false);
                timer.start();
            }
        } else if (e.getSource() == adjuntar) {
            documentos_adjuntos.setVisible(true);
            String archivo = documentos_adjuntos.getFile();
            if (archivo != null) {
                File archive_selected = new File(documentos_adjuntos.getDirectory(), archivo);
                Mostrar_Doc(archive_selected, true);
            }
        }
    }

    public void agregarMessage(JLabel mensaje_recibido, boolean usuario) {
        JLabel message = null;
        JLabel mensaje = null;
        if (mensaje_recibido.getName() == "mensaje_dowloaded") {
            mensaje = new JLabel(mensaje_recibido.getText());
        } else {
            message = mensaje_recibido;
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = usuario ? 1 : 0;
        gbc.gridy = container_chat.getComponentCount();
        gbc.anchor = usuario ? GridBagConstraints.LINE_END : GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, usuario ? 20 : 5, 5, usuario ? 5 : 20);

        JPanel mensajePanel = new JPanel(new BorderLayout());
        mensajePanel.setBackground(usuario ? new Color(255, 200, 200) : new Color(200, 200, 255));
        mensajePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        if (message != null) {
            mensajePanel.add(message);
        } else {
            mensajePanel.add(mensaje);
        }
        container_chat.add(mensajePanel, gbc);
        container_chat.revalidate();
        container_chat.repaint();
    }

    public void Mostrar_Doc(File archivo, boolean usuario) {
        String extension = extension_Documento(archivo.getName()).toLowerCase();

        if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {
            ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
            Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon imagen_Label = new ImageIcon(image);
            JLabel imagL = new JLabel(imagen_Label);
            agregarMessage(imagL, usuario);
        } else {
            JLabel mensaje = new JLabel("📄 " + archivo.getName());
            agregarMessage(mensaje, usuario);
        }
    }

    public String extension_Documento(String nomb_Archivo) {
        int punto = nomb_Archivo.lastIndexOf(".");
        return (punto == -1) ? "" : nomb_Archivo.substring(punto + 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String message = text.getText().trim();
            if (!message.isEmpty()) {
                JLabel mensaje = new JLabel(message);
                mensaje.setName("mensaje_dowloaded");
                agregarMessage(mensaje, true);
                text.setText("");
                mensaje.setText("Mensaje del doctor");
                ;
                Timer timer = new Timer(1000, evt -> agregarMessage(mensaje, false));
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
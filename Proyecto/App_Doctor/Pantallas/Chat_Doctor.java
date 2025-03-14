package App_Doctor.Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Chat_Doctor extends JPanel implements ActionListener, KeyListener {
    private JPanel container_chat, chatListPanel;
    private JScrollPane scroll_chat, scroll_chatList;
    private JTextField text;
    private JButton enviar, adjuntar;
    private FileDialog documentos_adjuntos;
    private ImageIcon enviar_icono;
    private JSplitPane splitPane;
    private Map<String, JPanel> chatHistorial;
    private String chatActual = "Chat 1";

    public Chat_Doctor() {
        init();
    }

    public void init() {
        chatHistorial = new HashMap<>();

        chatListPanel = new JPanel();
        chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
        scroll_chatList = new JScrollPane(chatListPanel);
        scroll_chatList.setPreferredSize(new Dimension(150, 400));

        for (int i = 1; i <= 5; i++) {
            String chatName = "Chat " + i;
            JButton chatButton = new JButton(chatName);
            chatButton.setMaximumSize(new Dimension(140, 40));
            chatButton.addActionListener(e -> cambiarChat(chatName));
            chatListPanel.add(chatButton);
            chatHistorial.put(chatName, new JPanel(new GridBagLayout()));
        }

        container_chat = chatHistorial.get(chatActual);
        scroll_chat = new JScrollPane(container_chat);
        text = new JTextField();
        enviar = new JButton();
        adjuntar = new JButton("📎 Adjuntar");
        documentos_adjuntos = new FileDialog((Frame) SwingUtilities.getWindowAncestor(this), "Select a file", FileDialog.LOAD);

        enviar_icono = new ImageIcon("ruta_correcta/enviar.png");
        if (enviar_icono.getImage() != null) {
            Image enviar_escalado = enviar_icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            enviar.setIcon(new ImageIcon(enviar_escalado));
        }

        enviar.setPreferredSize(new Dimension(50, 50));
        enviar.addActionListener(this);
        adjuntar.addActionListener(this);

        JPanel acciones = new JPanel(new GridBagLayout());
        GridBagConstraints actions = new GridBagConstraints();
        actions.gridx = 0; actions.gridy = 0; actions.weightx = 0.09; actions.fill = GridBagConstraints.BOTH;
        acciones.add(adjuntar, actions);

        actions.gridx = 1; actions.weightx = 0.8;
        acciones.add(text, actions);

        actions.gridx = 2; actions.weightx = 0.1;
        acciones.add(enviar, actions);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(scroll_chat, BorderLayout.CENTER);
        chatPanel.add(acciones, BorderLayout.SOUTH);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll_chatList, chatPanel);
        splitPane.setDividerLocation(150);
        splitPane.setResizeWeight(0.2);

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
        text.addKeyListener(this);
    }

    private void cambiarChat(String nuevoChat) {
        chatActual = nuevoChat;
        scroll_chat.setViewportView(chatHistorial.get(chatActual));
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enviar) {
            enviarMensaje();
        } else if (e.getSource() == adjuntar) {
            documentos_adjuntos.setVisible(true);
            String archivo = documentos_adjuntos.getFile();
            if (archivo != null) {
                File archive_selected = new File(documentos_adjuntos.getDirectory(), archivo);
                mostrarDocumento(archive_selected, true);
            }
        }
    }

    private void enviarMensaje() {
        String message = text.getText().trim();
        if (!message.isEmpty()) {
            JLabel mensaje = new JLabel(message);
            agregarMensaje(mensaje, true);
            text.setText("");
            Timer timer = new Timer(1000, evt -> agregarMensaje(new JLabel("Mensaje del doctor"), false));
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void agregarMensaje(JLabel mensaje, boolean usuario) {
        JPanel chatPanel = chatHistorial.get(chatActual);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = usuario ? 1 : 0;
        gbc.gridy = chatPanel.getComponentCount();
        gbc.anchor = usuario ? GridBagConstraints.LINE_END : GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, usuario ? 20 : 5, 5, usuario ? 5 : 20);

        JPanel mensajePanel = new JPanel(new BorderLayout());
        mensajePanel.setBackground(usuario ? new Color(255, 200, 200) : new Color(200, 200, 255));
        mensajePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        mensajePanel.add(mensaje);

        chatPanel.add(mensajePanel, gbc);
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    public void mostrarDocumento(File archivo, boolean usuario) {
        String extension = obtenerExtension(archivo.getName()).toLowerCase();
        if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {
            ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
            Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imagL = new JLabel(new ImageIcon(image));
            agregarMensaje(imagL, usuario);
        } else {
            agregarMensaje(new JLabel("📄 " + archivo.getName()), usuario);
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        int punto = nombreArchivo.lastIndexOf(".");
        return (punto == -1) ? "" : nombreArchivo.substring(punto + 1);
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enviarMensaje();
        }
    }
}
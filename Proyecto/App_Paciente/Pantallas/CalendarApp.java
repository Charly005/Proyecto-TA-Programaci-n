package App_Paciente.Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Style.EstilosUI;

public class CalendarApp extends JPanel {
    private JTable calendarTable;
    private DefaultTableModel calendarModel;
    private JButton btnSolicitar, btnCancelar, btnReprogramar;
    private JPanel sidePanel;
    private JTextArea infoArea;
    private JList<String> listaDiasDisponibles;
    private DefaultListModel<String> listModel;
    private ArrayList<String> citasSolicitadas;
    private Map<String, String> citasConfirmadas;

    public CalendarApp() {
        setLayout(new BorderLayout());

        citasSolicitadas = new ArrayList<>();
        citasConfirmadas = new HashMap<>();

        String[] columnNames = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom" };
        calendarModel = new DefaultTableModel(null, columnNames);
        calendarTable = new JTable(calendarModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        calendarTable.setDefaultRenderer(Object.class, new CalendarRenderer());
        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.getSelectedRow();
                int col = calendarTable.getSelectedColumn();
                String key = row + "," + col;
                mostrarDetallesCita(key);
            }
        });
        JScrollPane calendarScroll = new JScrollPane(calendarTable);
        calendarScroll.setOpaque(false);

        btnSolicitar = new JButton("Solicitar cita");
        btnSolicitar.setPreferredSize(new Dimension(200, 50));
        btnSolicitar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSolicitar.addActionListener(e -> mostrarFechasDisponibles());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(e -> JOptionPane.showMessageDialog(null, "¿Está seguro de cancelar la cita?"));

        btnReprogramar = new JButton("Reprogramar");
        btnReprogramar.setEnabled(false);
        btnReprogramar.addActionListener(e -> JOptionPane.showMessageDialog(null, "¿Desea reprogramar la cita"));

        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(250, 150));

        listModel = new DefaultListModel<>();
        listaDiasDisponibles = new JList<>(listModel);
        listaDiasDisponibles.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !listaDiasDisponibles.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Esperando confirmación...", "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE);
                listaDiasDisponibles.clearSelection();
            }
        });

        infoArea = new JTextArea();
        infoArea.setEditable(false);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setOpaque(false);
        listPanel.add(new JLabel("Fechas disponibles"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(listaDiasDisponibles), BorderLayout.CENTER);

        sidePanel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        sidePanel.add(listPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnReprogramar);
        sidePanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel calendarPanel = new JPanel(new BorderLayout());
        // calendarPanel.setOpaque(false);
        calendarPanel.add(calendarScroll, BorderLayout.CENTER);
        calendarPanel.add(btnSolicitar, BorderLayout.SOUTH);

        add(calendarPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        // APLICAR ESTILOS
        EstilosUI.aplicarEstiloPanelPrincipal(sidePanel);
        EstilosUI.aplicarEstiloPanelPrincipal(calendarPanel);

        EstilosUI.aplicarBotonGeneral(btnCancelar);
        EstilosUI.aplicarBotonGeneral(btnReprogramar);
        EstilosUI.aplicarBotonGeneral(btnSolicitar);

        llenarEjemploCitas();
        llenarCalendario();
        setVisible(true);
    }

    private void llenarEjemploCitas() {
        citasConfirmadas.put("1,1", "Consulta con Dr. Pérez a las 10:00 AM");
        citasConfirmadas.put("2,2", "Consulta con Dra. Gómez a las 12:00 PM");
        citasConfirmadas.put("3,3", "Chequeo general 3:00 PM");
    }

    private void llenarCalendario() {
        calendarModel.setRowCount(0);
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (startDay < 0)
            startDay += 7;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Object[][] monthData = new Object[6][7];
        int day = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < startDay) {
                    monthData[i][j] = "";
                } else if (day > daysInMonth) {
                    monthData[i][j] = "";
                } else {
                    monthData[i][j] = day;
                    day++;
                }
            }
        }
        for (Object[] row : monthData) {
            calendarModel.addRow(row);
        }
    }

    private void mostrarFechasDisponibles() {
        listModel.clear();
        for (int i = 0; i < calendarModel.getRowCount(); i++) {
            for (int j = 0; j < calendarModel.getColumnCount(); j++) {
                Object value = calendarModel.getValueAt(i, j);
                if (value instanceof Integer) {
                    String key = i + "," + j;
                    if (!citasConfirmadas.containsKey(key)) {
                        listModel.addElement("Día " + value);
                    }
                }
            }
        }
    }

    private void mostrarDetallesCita(String key) {
        infoArea.setText("");
        if (citasConfirmadas.containsKey(key)) {
            infoArea.setText("Detalles de la cita:\n" + citasConfirmadas.get(key));
            btnCancelar.setEnabled(true);
            btnReprogramar.setEnabled(true);
        } else {
            infoArea.setText("No hay citas registradas en esta fecha.");
            btnCancelar.setEnabled(false);
            btnReprogramar.setEnabled(false);
        }
    }

    class CalendarRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String key = row + "," + column;
            if (citasConfirmadas.containsKey(key)) {
                c.setBackground(Color.RED);
            } else if (citasSolicitadas.contains(key)) {
                c.setBackground(Color.YELLOW);
            } else {
                c.setBackground(Color.GREEN);
            }
            return c;
        }
    }

    /*
     * public static void main(String[] args) {
     * new CalendarApp();
     * }
     */
}

package App_Doctor.Pantallas.SubPantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MedicamentoInfo extends JPanel implements ActionListener {
    JButton volver;
    JButton editarNombre, editarDosis, editarFechaIni, editarFechaFin;
    JButton guardarNombre, cancelarNombre, guardarDosis, cancelarDosis, guardarFechaIni, cancelarFechaIni, guardarFechaFin, cancelarFechaFin;
    JLabel nombreMedicamento, dosisMedicamento, fechaIniMedicamento, fechaFinMedicament;
    JLabel nombre, dosis, fechaIni, fechaFin;
    JTextField nombreField, dosisField, fechaIniField, fechaFinField;
    JPanel container;
    Medicamentos parent;

    public MedicamentoInfo(String n, String d, String fIni, String fFin, Medicamentos parent) {
        this.parent = parent;
        init(n, d, fIni, fFin);
    }

    public void init(String n, String d, String fIni, String fFin) {
        volver = new JButton("Volver");
        volver.setFont(new Font("Arial", Font.BOLD, 12));
        volver.setBackground(new Color(59, 89, 182));
        volver.setForeground(Color.WHITE);
        volver.setFocusPainted(false);

        editarNombre = new JButton("Editar Nombre");
        editarDosis = new JButton("Editar Dosis");
        editarFechaIni = new JButton("Editar Fecha Inicio");
        editarFechaFin = new JButton("Editar Fecha Fin");

        guardarNombre = new JButton("Guardar");
        cancelarNombre = new JButton("Cancelar");
        guardarDosis = new JButton("Guardar");
        cancelarDosis = new JButton("Cancelar");
        guardarFechaIni = new JButton("Guardar");
        cancelarFechaIni = new JButton("Cancelar");
        guardarFechaFin = new JButton("Guardar");
        cancelarFechaFin = new JButton("Cancelar");

        Font buttonFont = new Font("Arial", Font.PLAIN, 12);
        Color buttonColor = new Color(100, 150, 200);
        Color cancelColor = new Color(200, 100, 100);

        editarNombre.setFont(buttonFont);
        editarNombre.setBackground(buttonColor);
        editarNombre.setForeground(Color.WHITE);
        editarDosis.setFont(buttonFont);
        editarDosis.setBackground(buttonColor);
        editarDosis.setForeground(Color.WHITE);
        editarFechaIni.setFont(buttonFont);
        editarFechaIni.setBackground(buttonColor);
        editarFechaIni.setForeground(Color.WHITE);
        editarFechaFin.setFont(buttonFont);
        editarFechaFin.setBackground(buttonColor);
        editarFechaFin.setForeground(Color.WHITE);

        guardarNombre.setFont(buttonFont);
        guardarNombre.setBackground(buttonColor);
        guardarNombre.setForeground(Color.WHITE);
        cancelarNombre.setFont(buttonFont);
        cancelarNombre.setBackground(cancelColor);
        cancelarNombre.setForeground(Color.WHITE);

        guardarDosis.setFont(buttonFont);
        guardarDosis.setBackground(buttonColor);
        guardarDosis.setForeground(Color.WHITE);
        cancelarDosis.setFont(buttonFont);
        cancelarDosis.setBackground(cancelColor);
        cancelarDosis.setForeground(Color.WHITE);

        guardarFechaIni.setFont(buttonFont);
        guardarFechaIni.setBackground(buttonColor);
        guardarFechaIni.setForeground(Color.WHITE);
        cancelarFechaIni.setFont(buttonFont);
        cancelarFechaIni.setBackground(cancelColor);
        cancelarFechaIni.setForeground(Color.WHITE);

        guardarFechaFin.setFont(buttonFont);
        guardarFechaFin.setBackground(buttonColor);
        guardarFechaFin.setForeground(Color.WHITE);
        cancelarFechaFin.setFont(buttonFont);
        cancelarFechaFin.setBackground(cancelColor);
        cancelarFechaFin.setForeground(Color.WHITE);

        guardarNombre.setVisible(false);
        cancelarNombre.setVisible(false);
        guardarDosis.setVisible(false);
        cancelarDosis.setVisible(false);
        guardarFechaIni.setVisible(false);
        cancelarFechaIni.setVisible(false);
        guardarFechaFin.setVisible(false);
        cancelarFechaFin.setVisible(false);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        nombreMedicamento = new JLabel("Nombre: ");
        nombreMedicamento.setFont(labelFont);
        nombre = new JLabel(n);
        nombre.setFont(new Font("Arial", Font.PLAIN, 14));

        dosisMedicamento = new JLabel("Dosis: ");
        dosisMedicamento.setFont(labelFont);
        dosis = new JLabel(d);
        dosis.setFont(new Font("Arial", Font.PLAIN, 14));

        fechaIniMedicamento = new JLabel("Fecha de inicio: ");
        fechaIniMedicamento.setFont(labelFont);
        fechaIni = new JLabel(fIni);
        fechaIni.setFont(new Font("Arial", Font.PLAIN, 14));

        fechaFinMedicament = new JLabel("Fecha de fin: ");
        fechaFinMedicament.setFont(labelFont);
        fechaFin = new JLabel(fFin);
        fechaFin.setFont(new Font("Arial", Font.PLAIN, 14));

        nombreField = new JTextField(n, 15);
        dosisField = new JTextField(d, 15);
        fechaIniField = new JTextField(fIni, 15);
        fechaFinField = new JTextField(fFin, 15);
        nombreField.setVisible(false);
        dosisField.setVisible(false);
        fechaIniField.setVisible(false);
        fechaFinField.setVisible(false);

        container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(nombreMedicamento, gbc);

        gbc.gridx = 1;
        container.add(nombre, gbc);
        container.add(nombreField, gbc); 

        gbc.gridx = 2;
        container.add(editarNombre, gbc);
        container.add(guardarNombre, gbc); 
        container.add(cancelarNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(dosisMedicamento, gbc);

        gbc.gridx = 1;
        container.add(dosis, gbc);
        container.add(dosisField, gbc);

        gbc.gridx = 2;
        container.add(editarDosis, gbc);
        container.add(guardarDosis, gbc); 
        container.add(cancelarDosis, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(fechaIniMedicamento, gbc);

        gbc.gridx = 1;
        container.add(fechaIni, gbc);
        container.add(fechaIniField, gbc); 

        gbc.gridx = 2;
        container.add(editarFechaIni, gbc);
        container.add(guardarFechaIni, gbc); 
        container.add(cancelarFechaIni, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(fechaFinMedicament, gbc);

        gbc.gridx = 1;
        container.add(fechaFin, gbc);
        container.add(fechaFinField, gbc); 

        gbc.gridx = 2;
        container.add(editarFechaFin, gbc);
        container.add(guardarFechaFin, gbc); 
        container.add(cancelarFechaFin, gbc); 

        this.setLayout(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(10, 10, 10, 10); 

        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(volver, mainGbc);

        mainGbc.gridx = 0;
        mainGbc.gridy = 1;
        mainGbc.weightx = 1;
        mainGbc.weighty = 1;
        mainGbc.fill = GridBagConstraints.BOTH;
        this.add(container, mainGbc);

        volver.addActionListener(this);
        editarNombre.addActionListener(this);
        editarDosis.addActionListener(this);
        editarFechaIni.addActionListener(this);
        editarFechaFin.addActionListener(this);
        guardarNombre.addActionListener(this);
        cancelarNombre.addActionListener(this);
        guardarDosis.addActionListener(this);
        cancelarDosis.addActionListener(this);
        guardarFechaIni.addActionListener(this);
        cancelarFechaIni.addActionListener(this);
        guardarFechaFin.addActionListener(this);
        cancelarFechaFin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volver) {
            parent.restablecer();
        } else if (e.getSource() == editarNombre) {
            toggleEditMode(nombre, nombreField, editarNombre, guardarNombre, cancelarNombre);
        } else if (e.getSource() == editarDosis) {
            toggleEditMode(dosis, dosisField, editarDosis, guardarDosis, cancelarDosis);
        } else if (e.getSource() == editarFechaIni) {
            toggleEditMode(fechaIni, fechaIniField, editarFechaIni, guardarFechaIni, cancelarFechaIni);
        } else if (e.getSource() == editarFechaFin) {
            toggleEditMode(fechaFin, fechaFinField, editarFechaFin, guardarFechaFin, cancelarFechaFin);
        } else if (e.getSource() == cancelarNombre || e.getSource() == guardarNombre) {
            toggleEditMode(nombre, nombreField, editarNombre, guardarNombre, cancelarNombre);
        } else if (e.getSource() == cancelarDosis || e.getSource() == guardarDosis) {
            toggleEditMode(dosis, dosisField, editarDosis, guardarDosis, cancelarDosis);
        } else if (e.getSource() == cancelarFechaIni || e.getSource() == guardarFechaIni) {
            toggleEditMode(fechaIni, fechaIniField, editarFechaIni, guardarFechaIni, cancelarFechaIni);
        } else if (e.getSource() == cancelarFechaFin || e.getSource() == guardarFechaFin) {
            toggleEditMode(fechaFin, fechaFinField, editarFechaFin, guardarFechaFin, cancelarFechaFin);
        }
    }

    private void toggleEditMode(JLabel label, JTextField field, JButton editButton, JButton saveButton, JButton cancelButton) {
        label.setVisible(!label.isVisible());
        field.setVisible(!field.isVisible()); 
        editButton.setVisible(!editButton.isVisible());
        saveButton.setVisible(!saveButton.isVisible()); 
        cancelButton.setVisible(!cancelButton.isVisible()); 

        if (field.isVisible()) {
            field.setText(label.getText()); 
            field.requestFocus(); 
        } else {
            label.setText(field.getText()); 
        }
    }
}
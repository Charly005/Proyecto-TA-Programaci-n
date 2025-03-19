package App_Paciente.Pantallas.SubPantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Paciente.Pantallas.Medicamentos;
import Style.EstilosUI;

public class MedicamentoInfo extends JPanel implements ActionListener {
    JButton volver;
    JLabel nombreMedicamento, nombre;
    JLabel dosisMedicamento, dosis;
    JLabel fechaIniMedicamento, fechaIni;
    JLabel fechaFinMedicament, fechaFin;
    JPanel container;
    Medicamentos parent;

    public MedicamentoInfo(String n, String d, String fIni, String fFin, Medicamentos parent) {
        this.parent = parent;
        init(n, d, fIni, fFin);
    }

    public void init(String n, String d, String fIni, String fFin) {
        volver = new JButton("Volver");
        nombreMedicamento = new JLabel("Nombre: ");
        nombre = new JLabel(n);
        dosisMedicamento = new JLabel("Dosis: ");
        dosis = new JLabel(d);
        fechaIniMedicamento = new JLabel("Fecha de inicio: ");
        fechaIni = new JLabel(fIni);
        fechaFinMedicament = new JLabel("Fecha de fin: ");
        fechaFin = new JLabel(fFin);
        container = new JPanel();

        container.setLayout(new GridLayout(4, 2));
        container.add(nombreMedicamento);
        container.add(nombre);
        container.add(dosisMedicamento);
        container.add(dosis);
        container.add(fechaIniMedicamento);
        container.add(fechaIni);
        container.add(fechaFinMedicament);
        container.add(fechaFin);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(volver, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        this.add(container, gbc);

        EstilosUI.aplicarBotonGeneral(volver);
        volver.addActionListener(this);

        // Aplicar estilos
        EstilosUI.aplicarEstiloPanelPrincipal(container);
        // REVISAR TAMAÑO DEL CONTENEDOR DE MEDICAMENTOS Y VISTA INFO,
        // AL IGUAL QUE EL TAMAÑO DEL BOTÓN VOLVER
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volver) {
            parent.restablecer();
        }
    }

}

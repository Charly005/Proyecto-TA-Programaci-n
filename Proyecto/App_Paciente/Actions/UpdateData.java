package App_Paciente.Actions;
import javax.swing.*;
import java.awt.*;

public class UpdateData extends JPanel {
    private String name;
    private int age;
    private String address;
    private String phone;
    private String medicalHistory;
    private String allergies;

    JLabel titleLabel;
    JPanel pnlData;
    TextField txtName, txtAge, txtAddress, txtPhone;
    TextArea txtaMedicalHistory, txtaAllergies;
    JButton btnUpdate;

    public UpdateData(String name, int age, String address, String phone, String medicalHistory, String allergies) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;

        this.setSize(400, 300);
        this.setVisible(true);
        init();
    }

    public void init() {
        titleLabel = new JLabel("Actualiza tu información", SwingConstants.CENTER);
        pnlData = new JPanel();
        txtName = new TextField(name);
        txtAge = new TextField(String.valueOf(age));
        txtAddress = new TextField(address);
        txtPhone = new TextField(phone);
        txtaMedicalHistory = new TextArea(medicalHistory);
        txtaAllergies = new TextArea(allergies);
        btnUpdate = new JButton("Actualizar");

        this.setLayout(new BorderLayout());
        pnlData.setLayout(new GridLayout(6, 2));

        pnlData.add(new JLabel("Nombre: "));
        pnlData.add(txtName);
        pnlData.add(new JLabel("Edad: "));
        pnlData.add(txtAge);
        pnlData.add(new JLabel("Dirección: "));
        pnlData.add(txtAddress);
        pnlData.add(new JLabel("Teléfono: "));
        pnlData.add(txtPhone);
        pnlData.add(new JLabel("Antecedentes Médicos: "));
        pnlData.add(txtaMedicalHistory);
        pnlData.add(new JLabel("Alergias: "));
        pnlData.add(txtaAllergies);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(pnlData, BorderLayout.CENTER);
        this.add(btnUpdate, BorderLayout.SOUTH);
    }
}

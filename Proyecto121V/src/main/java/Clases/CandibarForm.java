package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CandibarForm extends JFrame {
    private JTextField campoCineId;
    private JTextField campoEmpleadoEncargadoId;
    private JButton botonGuardar, botonVolver;
    private Candibar candibar;

    public CandibarForm(Candibar candibar) {
        this.setLocation(400, 200);
        this.candibar = candibar;
        setTitle("Formulario Candibar");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("ID Cine:"));
        campoCineId = new JTextField(candibar != null ? String.valueOf(candibar.getCineId()) : "");
        add(campoCineId);

        add(new JLabel("ID Empleado Encargado:"));
        campoEmpleadoEncargadoId = new JTextField(candibar != null ? String.valueOf(candibar.getEmpleadoEncargadoId()) : "");
        add(campoEmpleadoEncargadoId);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveCandibar());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveCandibar() {
        int cineId = Integer.parseInt(campoCineId.getText());
        int empleadoEncargadoId = Integer.parseInt(campoEmpleadoEncargadoId.getText());

        if (candibar == null) {
            candibar = new Candibar(0, cineId, empleadoEncargadoId);
        } else {
            candibar.setCineId(cineId);
            candibar.setEmpleadoEncargadoId(empleadoEncargadoId);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Candibar (cine_id, empleado_encargado_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, candibar.getCineId());
                statement.setInt(2, candibar.getEmpleadoEncargadoId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar candibar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Candibar guardado correctamente!");
    }
}

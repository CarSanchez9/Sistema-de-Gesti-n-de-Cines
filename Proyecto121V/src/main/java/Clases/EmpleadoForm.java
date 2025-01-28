package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoForm extends JFrame {
    private JTextField campoNombre, campoPuesto, campoSalario;
    private JButton botonGuardar, botonVolver;
    private Empleado empleado;

    public EmpleadoForm(Empleado empleado) {
        this.setLocation(400, 200);
        this.empleado = empleado;
        setTitle("Formulario Empleado");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nombre:"));
        campoNombre = new JTextField(empleado != null ? empleado.getNombre() : "");
        add(campoNombre);

        add(new JLabel("Puesto:"));
        campoPuesto = new JTextField(empleado != null ? empleado.getPuesto() : "");
        add(campoPuesto);

        add(new JLabel("Salario:"));
        campoSalario = new JTextField(empleado != null ? String.valueOf(empleado.getSalario()) : "");
        add(campoSalario);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveEmpleado());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveEmpleado() {
        String nombre = campoNombre.getText();
        String puesto = campoPuesto.getText();
        double salario = Double.parseDouble(campoSalario.getText());

        if (empleado == null) {
            empleado = new Empleado(0, nombre, puesto, salario);
        } else {
            empleado.setNombre(nombre);
            empleado.setPuesto(puesto);
            empleado.setSalario(salario);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Empleado (cine_id, nombre, puesto, salario) VALUES (1, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, empleado.getNombre());
                statement.setString(2, empleado.getPuesto());
                statement.setDouble(3, empleado.getSalario());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Empleado guardado correctamente!");
    }
}

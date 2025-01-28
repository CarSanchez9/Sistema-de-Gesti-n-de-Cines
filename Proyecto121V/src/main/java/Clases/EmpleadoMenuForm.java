
package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoMenuForm extends JFrame {
    public EmpleadoMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Empleados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID del Empleado a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarEmpleadoButton = new JButton("Eliminar Empleado");
        eliminarEmpleadoButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarEmpleado(id);
        });
        add(eliminarEmpleadoButton);

        JButton agregarEmpleadoButton = new JButton("Agregar Empleado");
        agregarEmpleadoButton.addActionListener(e -> {
            new EmpleadoForm(null).setVisible(true);
            dispose();
        });
        add(agregarEmpleadoButton);

        JButton mostrarEmpleadosButton = new JButton("Mostrar Empleados");
        mostrarEmpleadosButton.addActionListener(e -> {
            new EmpleadoListForm().setVisible(true);
            dispose();
        });
        add(mostrarEmpleadosButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarEmpleado(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Empleado WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

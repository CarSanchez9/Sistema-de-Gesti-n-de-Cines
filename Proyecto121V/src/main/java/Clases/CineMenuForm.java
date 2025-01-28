
package Clases;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CineMenuForm extends JFrame {
    public CineMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Cines");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID del Cine a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarCineButton = new JButton("Eliminar Cine");
        eliminarCineButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarCine(id);
        });
        add(eliminarCineButton);

        JButton agregarCineButton = new JButton("Agregar Cine");
        agregarCineButton.addActionListener(e -> {
            new CineForm(null).setVisible(true);
            dispose();
        });
        add(agregarCineButton);

        JButton mostrarCinesButton = new JButton("Mostrar Cines");
        mostrarCinesButton.addActionListener(e -> {
            new CineListForm().setVisible(true);
            dispose();
        });
        add(mostrarCinesButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarCine(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Cine WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Cine eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cine no encontrado.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar cine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

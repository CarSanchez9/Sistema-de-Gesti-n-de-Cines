
package Clases;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalaMenuForm extends JFrame {
    public SalaMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Salas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID de la Sala a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarSalaButton = new JButton("Eliminar Sala");
        eliminarSalaButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarSala(id);
        });
        add(eliminarSalaButton);

        JButton agregarSalaButton = new JButton("Agregar Sala");
        agregarSalaButton.addActionListener(e -> {
            new SalaForm(null).setVisible(true);
            dispose();
        });
        add(agregarSalaButton);

        JButton mostrarSalasButton = new JButton("Mostrar Salas");
        mostrarSalasButton.addActionListener(e -> {
            new SalaListForm().setVisible(true);
            dispose();
        });
        add(mostrarSalasButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new FuncionMenuForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarSala(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Sala WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Sala eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Sala no encontrada.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar sala: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

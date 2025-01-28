
package Clases;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PeliculaMenuForm extends JFrame {
    public PeliculaMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Películas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Campo y botón para eliminar película
        add(new JLabel("ID de la Película a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarPeliculaButton = new JButton("Eliminar Película");
        eliminarPeliculaButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarPelicula(id);
        });
        add(eliminarPeliculaButton);

        // Botón para agregar nueva película
        JButton agregarPeliculaButton = new JButton("Agregar Película");
        agregarPeliculaButton.addActionListener(e -> {
            new PeliculaForm(null).setVisible(true);
            dispose();
        });
        add(agregarPeliculaButton);

        // Botón para mostrar películas existentes
        JButton mostrarPeliculasButton = new JButton("Mostrar Películas");
        mostrarPeliculasButton.addActionListener(e -> {
            new PeliculaListForm().setVisible(true);
            dispose();
        });
        add(mostrarPeliculasButton);

        // Botón para volver al menú principal
        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarPelicula(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Pelicula WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Película eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Película no encontrada.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar película: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

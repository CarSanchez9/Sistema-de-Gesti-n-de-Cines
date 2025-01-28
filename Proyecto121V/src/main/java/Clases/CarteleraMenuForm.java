
package Clases;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarteleraMenuForm extends JFrame {
    public CarteleraMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Carteleras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID de la Cartelera a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarCarteleraButton = new JButton("Eliminar Cartelera");
        eliminarCarteleraButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarCartelera(id);
        });
        add(eliminarCarteleraButton);

        JButton agregarCarteleraButton = new JButton("Agregar Cartelera");
        agregarCarteleraButton.addActionListener(e -> {
            new CarteleraForm(null).setVisible(true);
            dispose();
        });
        add(agregarCarteleraButton);

        JButton mostrarCartelerasButton = new JButton("Mostrar Carteleras");
        mostrarCartelerasButton.addActionListener(e -> {
            new CarteleraListForm().setVisible(true);
            dispose();
        });
        add(mostrarCartelerasButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new FuncionMenuForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarCartelera(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Cartelera WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Cartelera eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cartelera no encontrada.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar cartelera: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

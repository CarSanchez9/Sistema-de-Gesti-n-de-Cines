
package Clases;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CandyBarMenuForm extends JFrame {
    public CandyBarMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de CandyBar");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID del Producto a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarProductoButton = new JButton("Eliminar Producto");
        eliminarProductoButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarProducto(id);
        });
        add(eliminarProductoButton);

        JButton agregarProductoButton = new JButton("Agregar Producto");
        agregarProductoButton.addActionListener(e -> {
            new ProductoForm(null).setVisible(true);
            dispose();
        });
        add(agregarProductoButton);

        JButton mostrarProductosButton = new JButton("Mostrar Productos");
        mostrarProductosButton.addActionListener(e -> {
            new ProductoListForm().setVisible(true);
            dispose();
        });
        add(mostrarProductosButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarProducto(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Producto WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

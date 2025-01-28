
package Clases;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionMenuForm extends JFrame {
    public FuncionMenuForm() {
        this.setLocation(400, 200);
        setTitle("Menú de Funciones");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        
        add(new JLabel("ID de la Función a eliminar:"));
        JTextField campoIdEliminar = new JTextField();
        add(campoIdEliminar);

        JButton eliminarFuncionButton = new JButton("Eliminar Función");
        eliminarFuncionButton.addActionListener(e -> {
            int id = Integer.parseInt(campoIdEliminar.getText());
            eliminarFuncion(id);
        });
        add(eliminarFuncionButton);
        
        JButton agregarFuncionButton = new JButton("Agregar Función");
        agregarFuncionButton.addActionListener(e -> {
            new FuncionForm(null).setVisible(true);
            dispose();
        });
        add(agregarFuncionButton);

        
        JButton mostrarFuncionesButton = new JButton("Mostrar Funciones");
        mostrarFuncionesButton.addActionListener(e -> {
            new FuncionListForm().setVisible(true);
            dispose();
        });
        add(mostrarFuncionesButton);

        
        JButton carteleraButton = new JButton("Administrar Carteleras");
        carteleraButton.addActionListener(e -> {
            new CarteleraMenuForm().setVisible(true);
            dispose();
        });
        add(carteleraButton);

        
        JButton salaButton = new JButton("Administrar Salas");
        salaButton.addActionListener(e -> {
            new SalaMenuForm().setVisible(true);
            dispose();
        });
        add(salaButton);

        
        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(volverButton);
    }

    private void eliminarFuncion(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Funcion WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Función eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Función no encontrada.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar función: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

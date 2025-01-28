package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoForm extends JFrame {
    private JTextField campoCandibarId, campoNombre, campoTipo, campoPrecio;
    private JButton botonGuardar, botonVolver;
    private Producto producto;

    public ProductoForm(Producto producto) {
        this.setLocation(400, 200);
        this.producto = producto;
        setTitle("Formulario Producto");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID Candibar:"));
        campoCandibarId = new JTextField(producto != null ? String.valueOf(producto.getCandibarId()) : "");
        add(campoCandibarId);

        add(new JLabel("Nombre:"));
        campoNombre = new JTextField(producto != null ? producto.getNombre() : "");
        add(campoNombre);

        add(new JLabel("Tipo:"));
        campoTipo = new JTextField(producto != null ? producto.getTipo() : "");
        add(campoTipo);

        add(new JLabel("Precio:"));
        campoPrecio = new JTextField(producto != null ? String.valueOf(producto.getPrecio()) : "");
        add(campoPrecio);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveProducto());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveProducto() {
        int candibarId = Integer.parseInt(campoCandibarId.getText());
        String nombre = campoNombre.getText();
        String tipo = campoTipo.getText();
        double precio = Double.parseDouble(campoPrecio.getText());

        if (producto == null) {
            producto = new Producto(0, candibarId, nombre, tipo, precio);
        } else {
            producto.setCandibarId(candibarId);
            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setPrecio(precio);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Producto (candibar_id, nombre, tipo, precio) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, producto.getCandibarId());
                statement.setString(2, producto.getNombre());
                statement.setString(3, producto.getTipo());
                statement.setDouble(4, producto.getPrecio());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Producto guardado correctamente!");
    }
}

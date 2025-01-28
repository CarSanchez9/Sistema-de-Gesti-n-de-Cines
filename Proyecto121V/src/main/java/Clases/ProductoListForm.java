package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoListForm extends JFrame {
    private JList<String> productoList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public ProductoListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        productoList = new JList<>(listModel);
        add(new JScrollPane(productoList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadProductos();
    }

    private void loadProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, candibar_id, nombre, tipo, precio FROM Producto";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int candibarId = resultSet.getInt("candibar_id");
                    String nombre = resultSet.getString("nombre");
                    String tipo = resultSet.getString("tipo");
                    double precio = resultSet.getDouble("precio");

                    Producto producto = new Producto(id, candibarId, nombre, tipo, precio);
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Producto producto : productos) {
            listModel.addElement(
                "ID: " + producto.getId() +
                " | Candibar: " + producto.getCandibarId() +
                " | Nombre: " + producto.getNombre() +
                " | Tipo: " + producto.getTipo() +
                " | Precio: $" + producto.getPrecio()
            );
        }
    }
}

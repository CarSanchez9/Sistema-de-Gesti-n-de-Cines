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

public class EmpleadoListForm extends JFrame {
    private JList<String> empleadoList;
    private DefaultListModel<String> listModel;
    private JTextField campoBusqueda;
    private JButton botonBuscar, botonRegresar;

    public EmpleadoListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Empleados");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBusqueda = new JPanel(new BorderLayout());
        campoBusqueda = new JTextField();
        botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEmpleado(campoBusqueda.getText());
            }
        });
        panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);
        panelBusqueda.add(botonBuscar, BorderLayout.EAST);
        add(panelBusqueda, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        empleadoList = new JList<>(listModel);
        add(new JScrollPane(empleadoList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadEmpleados();
    }

    private void loadEmpleados() {
        listModel.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nombre, puesto, salario FROM Empleado";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String puesto = resultSet.getString("puesto");
                    double salario = resultSet.getDouble("salario");
                    listModel.addElement("ID: " + id + " | " + nombre + " - " + puesto + " - $" + salario);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarEmpleado(String criterio) {
        listModel.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nombre, puesto, salario FROM Empleado WHERE id = ? OR nombre LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try {
                    statement.setInt(1, Integer.parseInt(criterio));
                } catch (NumberFormatException ex) {
                    statement.setInt(1, -1); // ID inválido
                }
                statement.setString(2, "%" + criterio + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String puesto = resultSet.getString("puesto");
                    double salario = resultSet.getDouble("salario");
                    listModel.addElement("ID: " + id + " | " + nombre + " - " + puesto + " - $" + salario);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

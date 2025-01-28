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

public class CineListForm extends JFrame {
    private JList<String> cineList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public CineListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Cines");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        cineList = new JList<>(listModel);
        add(new JScrollPane(cineList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadCines();
    }

    private void loadCines() {
        List<Cine> cines = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nombre, ubicacion FROM Cine";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String ubicacion = resultSet.getString("ubicacion");
                    Cine cine = new Cine(id, nombre, ubicacion);
                    cines.add(cine);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener cines: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Cine cine : cines) {
            listModel.addElement(cine.getNombre() + " - " + cine.getUbicacion());
        }
    }
}

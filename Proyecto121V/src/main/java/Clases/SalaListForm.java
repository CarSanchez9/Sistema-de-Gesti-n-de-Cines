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

public class SalaListForm extends JFrame {
    private JList<String> salaList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public SalaListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Salas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        salaList = new JList<>(listModel);
        add(new JScrollPane(salaList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadSalas();
    }

    private void loadSalas() {
        listModel.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nro_sala, tipo, nro_total_asientos FROM Sala";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int nroSala = resultSet.getInt("nro_sala");
                    String tipo = resultSet.getString("tipo");
                    int nroTotalAsientos = resultSet.getInt("nro_total_asientos");
                    listModel.addElement("Sala " + nroSala + " - " + tipo + " - Asientos: " + nroTotalAsientos);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener salas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

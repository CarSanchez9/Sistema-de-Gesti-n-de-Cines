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

public class CandibarListForm extends JFrame {
    private JList<String> candibarList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public CandibarListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Candibares");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        candibarList = new JList<>(listModel);
        add(new JScrollPane(candibarList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadCandibares();
    }

    private void loadCandibares() {
        List<Candibar> candibares = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, cine_id, empleado_encargado_id FROM Candibar";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cineId = resultSet.getInt("cine_id");
                    int empleadoEncargadoId = resultSet.getInt("empleado_encargado_id");
                    Candibar candibar = new Candibar(id, cineId, empleadoEncargadoId);
                    candibares.add(candibar);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener candibares: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Candibar candibar : candibares) {
            listModel.addElement("ID: " + candibar.getId() + " | Cine: " + candibar.getCineId() + " | Empleado Encargado: " + candibar.getEmpleadoEncargadoId());
        }
    }
}

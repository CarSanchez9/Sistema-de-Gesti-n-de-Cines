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

public class CarteleraListForm extends JFrame {
    
    private JList<String> carteleraList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public CarteleraListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Carteleras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        carteleraList = new JList<>(listModel);
        add(new JScrollPane(carteleraList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadCarteleras();
    }

    private void loadCarteleras() {
        List<Cartelera> carteleras = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, cine_id FROM Cartelera";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cineId = resultSet.getInt("cine_id");
                    Cartelera cartelera = new Cartelera(id, cineId);
                    carteleras.add(cartelera);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener carteleras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Cartelera cartelera : carteleras) {
            listModel.addElement("Cartelera ID: " + cartelera.getId() + " - Cine ID: " + cartelera.getCineId());
        }
    }
}

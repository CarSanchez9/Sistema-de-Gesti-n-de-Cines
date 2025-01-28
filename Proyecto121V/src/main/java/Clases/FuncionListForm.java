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

public class FuncionListForm extends JFrame {
    private JList<String> funcionList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public FuncionListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Funciones");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        funcionList = new JList<>(listModel);
        add(new JScrollPane(funcionList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadFunciones();
    }

    private void loadFunciones() {
        List<Funcion> funciones = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, cartelera_id, pelicula_id, sala_id, fecha, hora FROM Funcion";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int carteleraId = resultSet.getInt("cartelera_id");
                    int peliculaId = resultSet.getInt("pelicula_id");
                    int salaId = resultSet.getInt("sala_id");
                    java.sql.Date fecha = resultSet.getDate("fecha");
                    java.sql.Time hora = resultSet.getTime("hora");

                    Funcion funcion = new Funcion(id, carteleraId, peliculaId, salaId, fecha, hora);
                    funciones.add(funcion);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener funciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Funcion funcion : funciones) {
            listModel.addElement(
                "ID: " + funcion.getId() +
                " | Cartelera: " + funcion.getIdCartelera() +
                " | Película: " + funcion.getIdPelicula() +
                " | Sala: " + funcion.getIdSala() +
                " | Fecha: " + funcion.getFecha() +
                " | Hora: " + funcion.getHora()
            );
        }
    }
}

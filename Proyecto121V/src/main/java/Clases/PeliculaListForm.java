package Clases;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PeliculaListForm extends JFrame {
    private JList<String> peliculaList;
    private DefaultListModel<String> listModel;
    private JButton botonRegresar;

    public PeliculaListForm() {
        this.setLocation(400, 200);
        setTitle("Listado de Películas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        peliculaList = new JList<>(listModel);
        add(new JScrollPane(peliculaList), BorderLayout.CENTER);

        botonRegresar = new JButton("Volver");
        botonRegresar.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonRegresar, BorderLayout.SOUTH);

        loadPeliculas();
    }

    private void loadPeliculas() {
        listModel.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, titulo, genero, duracion, clasificacion FROM Pelicula";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String genero = resultSet.getString("genero");
                    int duracion = resultSet.getInt("duracion");
                    String clasificacion = resultSet.getString("clasificacion");
                    listModel.addElement(titulo + " - " + genero + " - " + duracion + " min - " + clasificacion);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener películas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

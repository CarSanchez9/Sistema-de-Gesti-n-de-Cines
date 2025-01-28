package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PeliculaForm extends JFrame {
    private JTextField campoTitulo, campoGenero, campoDuracion, campoClasificacion;
    private JButton botonGuardar, botonVolver;
    private Pelicula pelicula;

    public PeliculaForm(Pelicula pelicula) {
        this.setLocation(400, 200);
        this.pelicula = pelicula;
        setTitle("Formulario Película");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Título:"));
        campoTitulo = new JTextField(pelicula != null ? pelicula.getTitulo() : "");
        add(campoTitulo);

        add(new JLabel("Género:"));
        campoGenero = new JTextField(pelicula != null ? pelicula.getGenero() : "");
        add(campoGenero);

        add(new JLabel("Duración (min):"));
        campoDuracion = new JTextField(pelicula != null ? String.valueOf(pelicula.getDuracion()) : "");
        add(campoDuracion);

        add(new JLabel("Clasificación:"));
        campoClasificacion = new JTextField(pelicula != null ? pelicula.getClasificacion() : "");
        add(campoClasificacion);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> savePelicula());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void savePelicula() {
        String titulo = campoTitulo.getText();
        String genero = campoGenero.getText();
        int duracion = Integer.parseInt(campoDuracion.getText());
        String clasificacion = campoClasificacion.getText();

        if (pelicula == null) {
            pelicula = new Pelicula(0, titulo, genero, duracion, clasificacion);
        } else {
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            pelicula.setDuracion(duracion);
            pelicula.setClasificacion(clasificacion);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Pelicula (titulo, genero, duracion, clasificacion) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pelicula.getTitulo());
                statement.setString(2, pelicula.getGenero());
                statement.setInt(3, pelicula.getDuracion());
                statement.setString(4, pelicula.getClasificacion());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar película: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Película guardada correctamente!");
    }
}

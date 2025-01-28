package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionForm extends JFrame {
    private JTextField campoCarteleraId, campoPeliculaId, campoSalaId, campoFecha, campoHora;
    private JButton botonGuardar, botonVolver;
    private Funcion funcion;

    public FuncionForm(Funcion funcion) {
        this.setLocation(400, 200);
        this.funcion = funcion;
        setTitle("Formulario Función");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("ID Cartelera:"));
        campoCarteleraId = new JTextField(funcion != null ? String.valueOf(funcion.getIdCartelera()) : "");
        add(campoCarteleraId);

        add(new JLabel("ID Película:"));
        campoPeliculaId = new JTextField(funcion != null ? String.valueOf(funcion.getIdPelicula()) : "");
        add(campoPeliculaId);

        add(new JLabel("ID Sala:"));
        campoSalaId = new JTextField(funcion != null ? String.valueOf(funcion.getIdSala()) : "");
        add(campoSalaId);

        add(new JLabel("Fecha (YYYY-MM-DD):"));
        campoFecha = new JTextField(funcion != null ? String.valueOf(funcion.getFecha()) : "");
        add(campoFecha);

        add(new JLabel("Hora (HH:MM:SS):"));
        campoHora = new JTextField(funcion != null ? String.valueOf(funcion.getHora()) : "");
        add(campoHora);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveFuncion());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveFuncion() {
        int carteleraId = Integer.parseInt(campoCarteleraId.getText());
        int peliculaId = Integer.parseInt(campoPeliculaId.getText());
        int salaId = Integer.parseInt(campoSalaId.getText());
        String fecha = campoFecha.getText();
        String hora = campoHora.getText();

        if (funcion == null) {
            funcion = new Funcion(0, carteleraId, peliculaId, salaId, java.sql.Date.valueOf(fecha), java.sql.Time.valueOf(hora));
        } else {
            funcion.setIdCartelera(carteleraId);
            funcion.setIdPelicula(peliculaId);
            funcion.setIdSala(salaId);
            funcion.setFecha(java.sql.Date.valueOf(fecha));
            funcion.setHora(java.sql.Time.valueOf(hora));
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Funcion (cartelera_id, pelicula_id, sala_id, fecha, hora) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, funcion.getIdCartelera());
                statement.setInt(2, funcion.getIdPelicula());
                statement.setInt(3, funcion.getIdSala());
                statement.setDate(4, funcion.getFecha());
                statement.setTime(5, funcion.getHora());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar función: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Función guardada correctamente!");
    }
}

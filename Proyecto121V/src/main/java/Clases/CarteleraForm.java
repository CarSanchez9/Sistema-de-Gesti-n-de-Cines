package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarteleraForm extends JFrame {
    private JTextField campoIdCine;
    private JButton botonGuardar, botonVolver;
    private Cartelera cartelera;

    public CarteleraForm(Cartelera cartelera) {
        this.setLocation(400, 200);
        this.cartelera = cartelera;
        setTitle("Formulario Cartelera");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("ID Cine:"));
        campoIdCine = new JTextField(cartelera != null ? String.valueOf(cartelera.getCineId()) : "");
        add(campoIdCine);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveCartelera());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveCartelera() {
        int cineId = Integer.parseInt(campoIdCine.getText());

        if (cartelera == null) {
            cartelera = new Cartelera(0, cineId);
        } else {
            cartelera.setCineId(cineId);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Cartelera (cine_id) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cartelera.getCineId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar cartelera: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Cartelera guardada correctamente!");
    }
}

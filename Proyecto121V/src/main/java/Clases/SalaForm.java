package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalaForm extends JFrame {
    private JTextField campoNumeroSala, campoTipoSala, campoAsientos;
    private JButton botonGuardar, botonVolver;
    private Sala sala;

    public SalaForm(Sala sala) {
        this.setLocation(400, 200);
        this.sala = sala;
        setTitle("Formulario Sala");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Número de Sala:"));
        campoNumeroSala = new JTextField(sala != null ? String.valueOf(sala.getNroSala()) : "");
        add(campoNumeroSala);

        add(new JLabel("Tipo de Sala:"));
        campoTipoSala = new JTextField(sala != null ? sala.getTipo() : "");
        add(campoTipoSala);

        add(new JLabel("Número de Asientos:"));
        campoAsientos = new JTextField(sala != null ? String.valueOf(sala.getNroTotalAsientos()) : "");
        add(campoAsientos);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> saveSala());
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new SelectionForm().setVisible(true);
            dispose();
        });
        add(botonVolver);
    }

    private void saveSala() {
        int numeroSala = Integer.parseInt(campoNumeroSala.getText());
        String tipoSala = campoTipoSala.getText();
        int asientos = Integer.parseInt(campoAsientos.getText());

        if (sala == null) {
            sala = new Sala(0, numeroSala, tipoSala, asientos);
        } else {
            sala.setNroSala(numeroSala);
            sala.setTipo(tipoSala);
            sala.setNroTotalAsientos(asientos);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Sala (cine_id, nro_sala, tipo, nro_total_asientos) VALUES (1, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, sala.getNroSala());
                statement.setString(2, sala.getTipo());
                statement.setInt(3, sala.getNroTotalAsientos());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar sala: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Sala guardada correctamente!");
    }
}

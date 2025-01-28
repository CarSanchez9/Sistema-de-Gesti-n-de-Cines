package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CineForm extends JFrame {
    private JTextField campoNombre;
    private JTextField campoUbicacion;
    private JButton botonGuardar, botonVolver;
    private Cine cine;

    public CineForm(Cine cine) {
        this.setLocation(400, 200);
        this.cine = cine;
        setTitle("Formulario Cine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nombre:"));
        campoNombre = new JTextField(cine != null ? cine.getNombre() : "");
        add(campoNombre);

        add(new JLabel("Ubicación:"));
        campoUbicacion = new JTextField(cine != null ? cine.getUbicacion() : "");
        add(campoUbicacion);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCine();
            }
        });
        add(botonGuardar);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionForm().setVisible(true);
                dispose();
            }
        });
        add(botonVolver);
    }

    private void saveCine() {
        String nombre = campoNombre.getText();
        String ubicacion = campoUbicacion.getText();

        if (cine == null) {
            cine = new Cine(nombre, ubicacion);
        } else {
            cine.setNombre(nombre);
            cine.setUbicacion(ubicacion);
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Cine (nombre, ubicacion) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cine.getNombre());
                statement.setString(2, cine.getUbicacion());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar cine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Cine guardado correctamente!");
    }
}

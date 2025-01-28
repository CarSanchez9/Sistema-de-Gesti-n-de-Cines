package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionForm extends JFrame {
    public SelectionForm() {
        this.setLocation(400, 200);
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        

        JButton funcionesButton = new JButton("Administrar Funciones");
        funcionesButton.addActionListener(e -> {
            new FuncionMenuForm().setVisible(true);
            dispose();
        });
        add(funcionesButton);

        JButton empleadosButton = new JButton("Administrar Empleados");
        empleadosButton.addActionListener(e -> {
            new EmpleadoMenuForm().setVisible(true);
            dispose();
        });
        add(empleadosButton);

        JButton candyBarButton = new JButton("Administrar CandyBar");
        candyBarButton.addActionListener(e -> {
            new CandyBarMenuForm().setVisible(true);
            dispose();
        });
        add(candyBarButton);

        JButton cinesButton = new JButton("Administrar Cines");
        cinesButton.addActionListener(e -> {
            new CineMenuForm().setVisible(true);
            dispose();
        });
        add(cinesButton);

        JButton peliculasButton = new JButton("Administrar Películas");
        peliculasButton.addActionListener(e -> {
            new PeliculaMenuForm().setVisible(true);
            dispose();
        });
        add(peliculasButton);

        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(e -> System.exit(0));
        add(salirButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectionForm().setVisible(true));
    }
}

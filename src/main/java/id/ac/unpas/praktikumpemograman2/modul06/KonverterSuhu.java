/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author KEVIN
 */
public class KonverterSuhu {
     public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        // Komponen GUI
        JLabel lblCelcius = new JLabel("Celcius:");
        JTextField txtCelcius = new JTextField(10);
        JButton btnKonversi = new JButton("Konversi");
        JLabel lblFahrenheit = new JLabel("");
        JLabel lblHasil = new JLabel("");

        // Tambahkan komponen ke frame
        frame.add(lblCelcius);
        frame.add(txtCelcius);
        frame.add(btnKonversi);
        frame.add(lblFahrenheit);
        frame.add(lblHasil);

        // Tambahkan event listener ke tombol
        btnKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celcius = Double.parseDouble(txtCelcius.getText());
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    lblHasil.setText(String.format("Fahrenheit: %.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    lblHasil.setText("Masukkan angka yang valid!");
                        }
                    }
                 });
        
        frame.setVisible(true);
    }
}


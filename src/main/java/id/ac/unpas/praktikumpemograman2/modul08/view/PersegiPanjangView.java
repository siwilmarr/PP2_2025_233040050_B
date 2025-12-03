/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul08.view;
    import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author KEVIN
 */

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-");
    private JButton btnHitung = new JButton("Hasil");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(new GridLayout(6, 2, 10, 10)); // Grid 4 baris
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);
        
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);
        
      
        this.add(btnHitung);
        this.add(btnReset);
    }

    // Mengambil nilai panjang dari Textfield
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari Textfield
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }
    
     public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }
     
         public void clearInput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
        txtPanjang.requestFocus(); // Fokuskan kursor kembali ke kolom panjang
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
      public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}


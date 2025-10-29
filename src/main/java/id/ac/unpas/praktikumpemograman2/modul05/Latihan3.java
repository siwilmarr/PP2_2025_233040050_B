/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;

/**
 *
 * @author KEVIN
 */
import java.awt.FlowLayout;
     import javax.swing.*;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()  {
        public void run() {
            // buat objek frame
            JFrame frame = new JFrame("Jendela Pertama Siwill");
            // atur ukuran jendela (lebar 400, tinggi 3000
            frame.setSize(400, 300);
            // atur aksi saat tombol close ditekan
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
//            atur layout manager
//            flowlayout akan menyusun komponen dari kiri ke kanan 
              frame.setLayout(new FlowLayout());

//            buat komponen GUI
            JLabel label = new JLabel("Teks awal");
            JButton button = new JButton("Klik saya");
              
//            tambahkan aksi (ActionListener) ke tombol
//            penambahan isi menggunakan lambda untuk  mempersingkat 
//            penggunaan anonymous inner class
            button.addActionListener(e -> {
//                aksi ini akan mengubah pada label
                  label.setText("Tombol berhasil diklik!");
            });
            
//          tambahkan komponen ke frame karena kita menggunakan Flowlayout,
//          keduanya akan tampil berdampingan
            frame.add(label);
            frame.add(button);
            
             // buat jendela terlihat
            frame.setVisible(true);
            
        }
       });    
    }
}

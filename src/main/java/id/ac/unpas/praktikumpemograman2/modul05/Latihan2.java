/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;
import javax.swing.*;
/**
 *
 * @author KEVIN
 */
public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()  {
        public void run() {
            // 1. buat objek frame
            JFrame frame = new JFrame("Jendela Pertama Siwill");
            // 2. atur ukuran jendela (lebar 400, tinggi 3000
            frame.setSize(400, 300);
            // 3. atur aksi saat tombol close ditekan
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // 4. buat jendela terlihat

//            buat komponen Jlabel
    JLabel label = new JLabel ("ini adalah Jlabel");
    
//    tambahkan Jlabel ke frame scr default, JFrame menggunakan borderlayout
            frame.add(label);
            
            frame.setVisible(true);
            
        }
       });    
    }
}

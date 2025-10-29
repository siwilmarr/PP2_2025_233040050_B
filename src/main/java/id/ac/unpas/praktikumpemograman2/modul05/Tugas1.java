/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/**
 *
 * @author KEVIN
 */
public class Tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        public void run() {
            JFrame frame = new JFrame("Contoh BorderLayout");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setLayout(new BorderLayout());
            
            JLabel label = new JLabel("Label ada diatas (NORTH)");
            JButton SOUTH = new JButton("Tombol ada dibawah SOUTH");
            JButton WEST = new JButton("Tombol WEST");
            JButton EAST = new JButton("Tombol EAST");
            JButton CENTER = new JButton("Tombol CENTER");
            SOUTH.addActionListener(e -> {
                label.setText("Tombol di SOUTH diklik!");
            });
            
            WEST.addActionListener(e -> {
                label.setText("Tombol di WEST diklik!");
            });
            
            EAST.addActionListener(e -> {
                label.setText("Tombol di EAST diklik!");
            });
             
            CENTER.addActionListener(e -> {
                label.setText("Tombol di CENTER diklik!");
            });
            
            frame.add(label, BorderLayout.NORTH);
            frame.add(SOUTH, BorderLayout.SOUTH);
            frame.add(WEST, BorderLayout.WEST);
            frame.add(EAST, BorderLayout.EAST);
            frame.add(CENTER, BorderLayout.CENTER);
            
            frame.setVisible(true);
            }    
        });
    }
    
}


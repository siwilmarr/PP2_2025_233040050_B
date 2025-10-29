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
public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        public void run() {
            JFrame frame = new JFrame("Contoh BorderLayout");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setLayout(new BorderLayout());
            
            JLabel label = new JLabel("Label ada diatas (NORTH)");
            JButton siwil = new JButton("Tombol ada dibawah SOUTH");
          
            siwil.addActionListener(e -> {
                label.setText("Tombol di SOUTH diklik!");
            });
            
            frame.add(label, BorderLayout.NORTH);
            frame.add(siwil, BorderLayout.SOUTH);
            frame.add(new JButton("WEST"), BorderLayout.WEST);
            frame.add(new JButton("EAST"), BorderLayout.EAST);
            frame.add(new JButton("CENTER"), BorderLayout.CENTER);
            
            frame.setVisible(true);
            }    
        });
    }
    
}

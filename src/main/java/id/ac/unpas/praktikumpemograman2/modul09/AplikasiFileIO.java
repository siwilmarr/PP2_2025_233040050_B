/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul09;

/**
 *
 * @author KEVIN
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame{
    
//    komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary,btnAppendText;
    private JFileChooser fileChooser;
    
    public AplikasiFileIO() {
        
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
         textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
         fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
         btnOpenText = new JButton("Buka Text");
         btnSaveText = new JButton("Simpan Text");
         btnSaveBinary = new JButton("Simpan Config (Binary)");
         btnLoadBinary = new JButton("Muat Config (Binary)");
         btnAppendText = new JButton("Tambah Text(Append)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling ---

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());
        
        btnAppendText.addActionListener(e -> appendFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        muatNotesOtomatis();
        setVisible(true);
    }
    
    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
private void bukaFileTeks() {
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

        try {
            // Membuka stream
            reader = new BufferedReader(new FileReader(file));
            textArea.setText(""); // Kosongkan area

            String line;
            // Baca baris demi baris
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
        } finally {
            // Blok Finally: Selalu dijalankan untuk menutup resource
            try {
                if (reader != null) {
                    reader.close(); // PENTING: Menutup stream
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
private void simpanFileTeks() {
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        // Try-with-resources otomatis menutup stream tanpa blok finally manual
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
        }
    }
}

// Latihan 4. Menambahkan Teks (Append)
private void appendFileTeks() {
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        // Menggunakan konstruktor FileWriter(File file, boolean append) dengan 'true'
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            // Tambahkan baris baru sebelum konten baru
            writer.newLine();
            writer.write(textArea.getText());

            JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan (Append)!");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
        }
    }
}

// Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
private void simpanConfigBinary() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
        // Ambil ukuran font saat ini (integer)
        int fontSize = textArea.getFont().getSize();
        dos.writeInt(fontSize);

        JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
    }
}

// Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
private void muatConfigBinary() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
        // Membaca data Integer mentah
        int fontSize = dis.readInt();

        // Terapkan ke aplikasi
        textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
    }
}

    private void muatNotesOtomatis() {
    try (BufferedReader reader = new BufferedReader (new FileReader("last_notes.txt"))) {
        textArea.setText("");
        String line;
        
        while ((line = reader.readLine()) != null) {
            textArea.append(line + "/n");
        }
        
    } catch (FileNotFoundException ex) {
//        biarkan kosong
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca last_notes.txt: " + ex.getMessage());
}
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new AplikasiFileIO().setVisible(true);
    });
    }
}

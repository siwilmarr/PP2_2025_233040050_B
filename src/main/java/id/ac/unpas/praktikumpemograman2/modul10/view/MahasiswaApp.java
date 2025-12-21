package id.ac.unpas.praktikumpemograman2.modul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * View untuk tampilan GUI Mahasiswa
 */
public class MahasiswaApp extends JFrame {
    // Komponen GUI
    private JTextField txtNama, txtNIM, txtJurusan;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JTable tableMahasiswa;
    private DefaultTableModel model;
    
    // Komponen untuk Pencarian
    private JTextField txtCari; 
    private JButton btnCari;

    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC - MVC");
        setSize(600, 550); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol CRUD
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        
        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Gabungkan Panel Form, Tombol CRUD, dan Pencarian
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH); 
        
        JPanel panelControls = new JPanel(new BorderLayout());
        panelControls.add(panelTombol, BorderLayout.NORTH); 
        panelControls.add(panelCari, BorderLayout.SOUTH); 
        
        panelAtas.add(panelControls, BorderLayout.CENTER);
        
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel Data
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Getter untuk mengambil data dari TextField
    public String getNama() {
        return txtNama.getText().trim();
    }

    public String getNIM() {
        return txtNIM.getText().trim();
    }

    public String getJurusan() {
        return txtJurusan.getText().trim();
    }

    public String getKeywordCari() {
        return txtCari.getText().trim();
    }

    // Setter untuk mengisi data ke TextField
    public void setNama(String nama) {
        txtNama.setText(nama);
    }

    public void setNIM(String nim) {
        txtNIM.setText(nim);
    }

    public void setJurusan(String jurusan) {
        txtJurusan.setText(jurusan);
    }

    // Method untuk mengosongkan form
    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtNama.requestFocus();
    }

    // Method untuk mendapatkan model tabel
    public DefaultTableModel getTableModel() {
        return model;
    }

    // Method untuk mendapatkan tabel
    public JTable getTable() {
        return tableMahasiswa;
    }

    // Method untuk menampilkan pesan
    public void tampilkanPesan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void tampilkanPesanWarning(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    // Method untuk menambahkan ActionListener ke tombol-tombol
    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void addCariListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    // Method untuk menambahkan MouseListener ke tabel
    public void addTableMouseListener(MouseAdapter adapter) {
        tableMahasiswa.addMouseListener(adapter);
    }
}
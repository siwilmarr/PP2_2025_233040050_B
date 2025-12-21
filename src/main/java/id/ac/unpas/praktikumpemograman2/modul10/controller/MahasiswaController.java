package id.ac.unpas.praktikumpemograman2.modul10.controller;

import id.ac.unpas.praktikumpemograman2.modul10.model.KoneksiDB;
import id.ac.unpas.praktikumpemograman2.modul10.model.MahasiswaModel;
import id.ac.unpas.praktikumpemograman2.modul10.view.MahasiswaApp;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Controller untuk menghubungkan Model dan View
 */
public class MahasiswaController {
    private MahasiswaModel model;
    private MahasiswaApp view;

    public MahasiswaController(MahasiswaModel model, MahasiswaApp view) {
        this.model = model;
        this.view = view;

        // Menghubungkan event listener dari View ke Controller
        this.view.addSimpanListener(new SimpanListener());
        this.view.addEditListener(new EditListener());
        this.view.addHapusListener(new HapusListener());
        this.view.addClearListener(new ClearListener());
        this.view.addCariListener(new CariListener());
        this.view.addTableMouseListener(new TableMouseListener());

        // Load data pertama kali
        loadData();
    }

    // Inner class untuk menangani event klik tombol Simpan
    class SimpanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tambahData();
        }
    }

    // Inner class untuk menangani event klik tombol Edit
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ubahData();
        }
    }

    // Inner class untuk menangani event klik tombol Hapus
    class HapusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            hapusData();
        }
    }

    // Inner class untuk menangani event klik tombol Clear
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearForm();
        }
    }

    // Inner class untuk menangani event klik tombol Cari
    class CariListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cariData();
        }
    }

    // Inner class untuk menangani event klik pada tabel
    class TableMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            DefaultTableModel tableModel = view.getTableModel();
            
            view.setNama(tableModel.getValueAt(row, 1).toString());
            view.setNIM(tableModel.getValueAt(row, 2).toString());
            view.setJurusan(tableModel.getValueAt(row, 3).toString());
        }
    }

    // --- LOGIKA CRUD ---

    // 1. READ (Menampilkan Data)
    private void loadData() {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);
        
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

            int no = 1;
            while (res.next()) {
                tableModel.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            view.tampilkanPesanError("Gagal Load Data: " + e.getMessage());
        }
    }

    // 2. CREATE (Menambah Data)
    private void tambahData() {
        String nama = view.getNama();
        String nim = view.getNIM();
        String jurusan = view.getJurusan();

        // Set data ke model
        model.setNama(nama);
        model.setNim(nim);
        model.setJurusan(jurusan);

        // 1. Validasi Data Kosong
        if (nama.isEmpty() || nim.isEmpty()) {
            view.tampilkanPesanWarning("Nama dan NIM tidak boleh kosong!");
            return;
        }

        // 2. Validasi Nama (Harus String/Alfabet)
        if (!model.isAlpha(nama)) {
            view.tampilkanPesanWarning("Nama harus diisi dengan huruf (alfabet)!");
            return;
        }
        
        // 3. Validasi NIM (Harus Angka/Numerik)
        if (!model.isNumeric(nim)) {
            view.tampilkanPesanWarning("NIM harus diisi dengan angka!");
            return;
        }
        
        // 4. Pengecekan Duplikasi NIM
        if (isNIMExists(nim)) {
            view.tampilkanPesanError("NIM (" + nim + ") sudah terdaftar di database!");
            return;
        }

        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, jurusan);

            pst.execute();

            view.tampilkanPesan("Data Berhasil Disimpan");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.tampilkanPesanError("Gagal Simpan: " + e.getMessage());
        }
    }

    // 3. UPDATE (Mengubah Data berdasarkan NIM)
    private void ubahData() {
        String nama = view.getNama();
        String nim = view.getNIM();
        String jurusan = view.getJurusan();

        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jurusan);
            pst.setString(3, nim);

            pst.executeUpdate();

            view.tampilkanPesan("Data Berhasil Diubah");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.tampilkanPesanError("Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE (Menghapus Data berdasarkan NIM)
    private void hapusData() {
        String nim = view.getNIM();

        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nim);

            pst.execute();
            
            view.tampilkanPesan("Data Berhasil Dihapus");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.tampilkanPesanError("Gagal Hapus: " + e.getMessage());
        }
    }
    
    // 5. SEARCH (Mencari Data berdasarkan Nama)
    private void cariData() {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);
        
        String keyword = view.getKeywordCari();
        
        if (keyword.isEmpty()) {
            loadData();
            return;
        }

        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, "%" + keyword + "%");
            
            ResultSet res = pst.executeQuery();

            int no = 1;
            while (res.next()) {
                tableModel.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            view.tampilkanPesanError("Gagal Cari Data: " + e.getMessage());
        }
    }

    // Pengecekan Duplikasi NIM di Database
    private boolean isNIMExists(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getInt(1) > 0;
            }
        } catch (Exception e) {
            view.tampilkanPesanError("Error saat cek NIM: " + e.getMessage());
        }
        return false;
    }
}
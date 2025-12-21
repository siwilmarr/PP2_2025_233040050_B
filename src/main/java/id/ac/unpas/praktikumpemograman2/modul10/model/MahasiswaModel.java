package id.ac.unpas.praktikumpemograman2.modul10.model;

/**
 * Model untuk menyimpan data Mahasiswa
 */
public class MahasiswaModel {
    private String nama;
    private String nim;
    private String jurusan;

    // Constructor
    public MahasiswaModel() {
    }

    public MahasiswaModel(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getters dan Setters
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    // Validasi: Memeriksa apakah string hanya mengandung huruf dan spasi
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }

    // Validasi: Memeriksa apakah string hanya mengandung angka (numerik)
    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }
}
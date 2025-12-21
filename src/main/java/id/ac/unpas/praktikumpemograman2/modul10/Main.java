package id.ac.unpas.praktikumpemograman2.modul10;

import id.ac.unpas.praktikumpemograman2.modul10.controller.MahasiswaController;
import id.ac.unpas.praktikumpemograman2.modul10.model.MahasiswaModel;
import id.ac.unpas.praktikumpemograman2.modul10.view.MahasiswaApp;

/**
 * Main class untuk menjalankan aplikasi
 */
public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        MahasiswaModel model = new MahasiswaModel();

        // 2. Instansiasi View
        MahasiswaApp view = new MahasiswaApp();

        // 3. Instansiasi Controller (Hubungkan Model & View)
        MahasiswaController controller = new MahasiswaController(model, view);

        // 4. Tampilkan View
        view.setVisible(true);
    }
}
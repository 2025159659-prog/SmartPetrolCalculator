package com.example.individualproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // TAMBAHAN: Isytihar pembolehubah untuk komponen XML yang baru
    private RadioGroup radioGroupPetrol;
    private EditText idtextPetrol, idtextfuel;
    private Spinner spinnerBudiEligibility;
    private TextView tvTotalCost, tvBudiRebate, tvTotalSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Panggil super.onCreate dan aktifkan EdgeToEdge terlebih dahulu
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 2. SET CONTENT VIEW - Ini mesti dilakukan sebelum findViewById
        setContentView(R.layout.activity_main);

        // 3. Setup Window Insets (Padding untuk status bar/navigation bar)
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 4. Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // =================================================================
        // TAMBAHAN: Hubungkan pembolehubah dengan ID komponen di XML baru
        // =================================================================
        radioGroupPetrol = findViewById(R.id.radioGroupPetrol);
        idtextPetrol = findViewById(R.id.idtextPetrol);
        idtextfuel = findViewById(R.id.idtextfuel);
        spinnerBudiEligibility = findViewById(R.id.spinnerBudiEligibility);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        tvBudiRebate = findViewById(R.id.tvBudiRebate);
        tvTotalSaving = findViewById(R.id.tvTotalSaving);

        // TAMBAHAN: Masukkan pilihan YES dan NO ke dalam Spinner BUDI MADANI
        if (spinnerBudiEligibility != null) {
            String[] eligibilityOptions = {"YES", "NO"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, eligibilityOptions);
            spinnerBudiEligibility.setAdapter(adapter);
        }

        // 5. Inisialisasi Button dan Listener (Kini memanggil fungsi kiraKosPetrol)
        Button btncalculate = findViewById(R.id.btncalculate);
        if (btncalculate != null) {
            btncalculate.setOnClickListener(v -> {
                // Jalankan fungsi pengiraan apabila butang ditekan
                kiraKosPetrol();
            });
        }
    }

    // TAMBAHAN: Fungsi Baharu untuk Membuat Pengiraan Matematik Assignment
    private void kiraKosPetrol() {
        // Semak sekiranya EditText kosong untuk mengelakkan app crash
        if (idtextPetrol == null || idtextfuel == null ||
                idtextPetrol.getText().toString().isEmpty() || idtextfuel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Sila masukkan nilai harga dan liter terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ambil nilai daripada input pengguna
        double pricePerLiter = Double.parseDouble(idtextPetrol.getText().toString());
        double fuelUsage = Double.parseDouble(idtextfuel.getText().toString());
        String budiStatus = spinnerBudiEligibility.getSelectedItem().toString();

        // Cari tahu jenis petrol yang dipilih dari RadioGroup
        String petrolType = "";
        if (radioGroupPetrol != null) {
            int selectedPetrolId = radioGroupPetrol.getCheckedRadioButtonId();
            if (selectedPetrolId == R.id.btnRon95) {
                petrolType = "RON95";
            } else if (selectedPetrolId == R.id.btnRon97) {
                petrolType = "RON97";
            } else if (selectedPetrolId == R.id.btnDiesel) {
                petrolType = "Diesel";
            }
        }

        // 1. Formula: Total Petrol Cost = fuel usage x petrol price per liter
        double totalPetrolCost = fuelUsage * pricePerLiter;

        // 2. Formula: BUDI rebate = fuel usage x subsidy rate (RM1.99/l)
        // Syarat: Hanya untuk petrol jenis RON95 dan status kelayakan adalah YES
        double budiRebate = 0.0;
        if (petrolType.equals("RON95") && budiStatus.equals("YES")) {
            budiRebate = fuelUsage * 1.99;
        }

        // 3. Formula: Total Saving = Total petrol cost - BUDI rebate
        double totalSaving = totalPetrolCost - budiRebate;

        // Paparkan hasil pengiraan pada TextView (Format 2 angka perpuluhan / RM Sen)
        if (tvTotalCost != null) tvTotalCost.setText(String.format("Total Petrol Cost: RM %.2f", totalPetrolCost));
        if (tvBudiRebate != null) tvBudiRebate.setText(String.format("BUDI Rebate: RM %.2f", budiRebate));
        if (tvTotalSaving != null) tvTotalSaving.setText(String.format("Total Saving: RM %.2f", totalSaving));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Pastikan fail main_menu.xml wujud dalam folder res/menu/
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Logik untuk setiap item menu
        if (id == R.id.action_home) {
            Toast.makeText(this, "Home clicked!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_about_us) {
            // Menggunakan Intent asal awak untuk berpindah ke AboutUsActivity
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
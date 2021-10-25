package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import id.jyotisa.roomprogmoblanjut.Database.AppDatabase;
import id.jyotisa.roomprogmoblanjut.Database.Entity.Mahasiswa;

public class CreateActivity extends AppCompatActivity {
    private Button btnSubmit;
    private TextInputLayout name, location;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name = findViewById(R.id.inputName);
        location = findViewById(R.id.inputLocation);
        btnSubmit = (Button) findViewById(R.id.createButton);

        database = AppDatabase.getInstance(getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.nama = name.getEditText().getText().toString();
                mahasiswa.alamat = location.getEditText().getText().toString();
                database.mahasiswaDao().insertAll(mahasiswa);
                finish();
            }
        });
    }
}
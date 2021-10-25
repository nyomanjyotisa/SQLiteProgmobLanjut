package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import id.jyotisa.roomprogmoblanjut.Database.AppDatabase;
import id.jyotisa.roomprogmoblanjut.Database.Entity.Mahasiswa;

public class UpdateActivity extends AppCompatActivity {
    private Button btnSubmit;
    private TextInputLayout name, location;
    private AppDatabase database;
    private int idEdit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.inputName);
        location = findViewById(R.id.inputLocation);
        btnSubmit = (Button) findViewById(R.id.createButton);


        database = AppDatabase.getInstance(getApplicationContext());

        Intent data = getIntent();
        idEdit = data.getIntExtra("nim", 0);
        if(idEdit > 0){
            Mahasiswa mahasiswa = database.mahasiswaDao().get(idEdit);
            name.getEditText().setText(mahasiswa.nama);
            location.getEditText().setText(mahasiswa.alamat);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.mahasiswaDao().update(name.getEditText().getText().toString(), location.getEditText().getText().toString(), idEdit);
                Toast.makeText(UpdateActivity.this, "Data Telah Diperbaharui", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
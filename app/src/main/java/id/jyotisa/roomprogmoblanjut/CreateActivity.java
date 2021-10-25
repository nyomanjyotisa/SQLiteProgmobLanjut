package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import id.jyotisa.roomprogmoblanjut.Helper.Database;

public class CreateActivity extends AppCompatActivity {
    private Button btnSubmit;
    private TextInputLayout name, location;
    protected Cursor cursor;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        database = new Database(this);
        name = findViewById(R.id.inputName);
        location = findViewById(R.id.inputLocation);
        btnSubmit = (Button) findViewById(R.id.createButton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO tb_mahasiswa (name, location) VALUES('"+name.getEditText().getText().toString()+"', '"+location.getEditText().getText().toString()+"')");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
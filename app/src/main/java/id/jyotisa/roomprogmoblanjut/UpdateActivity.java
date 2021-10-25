package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import id.jyotisa.roomprogmoblanjut.Helper.Database;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    private Button btnSubmit;
    private TextInputLayout name, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new Database(this);
        name = findViewById(R.id.inputName);
        location = findViewById(R.id.inputLocation);
        btnSubmit = (Button) findViewById(R.id.createButton);

        SQLiteDatabase db = database.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM tb_mahasiswa WHERE id = '"+getIntent().getStringExtra("id")+"'", null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            name.getEditText().setText(cursor.getString(1).toString());
            location.getEditText().setText(cursor.getString(2).toString());
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE tb_mahasiswa SET name = '"+name.getEditText().getText().toString()+"', location = '"+location.getEditText().getText().toString()+"' WHERE id = '"+getIntent().getStringExtra("id")+"'");
                Toast.makeText(UpdateActivity.this, "Data Berhasil Diperbaharui", Toast.LENGTH_SHORT).show();
                MainActivity.mainActivity.RefreshList();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
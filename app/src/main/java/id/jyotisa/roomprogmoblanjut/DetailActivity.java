package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.jyotisa.roomprogmoblanjut.Helper.Database;

public class DetailActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button buttonBack;
    TextView nim, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        database = new Database(this);
        nim = findViewById(R.id.viewNIM);
        nama = findViewById(R.id.viewNama);
        alamat = findViewById(R.id.viewAlamat);
        buttonBack = findViewById(R.id.buttonBack);

        SQLiteDatabase db = database.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM tb_mahasiswa WHERE id = '"+getIntent().getStringExtra("id")+"'",null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nim.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.RefreshList();
                finish();
            }
        });
    }
}
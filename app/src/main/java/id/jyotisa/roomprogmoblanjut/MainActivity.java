package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.jyotisa.roomprogmoblanjut.Adapter.MahasiswaAdapter;
import id.jyotisa.roomprogmoblanjut.Database.AppDatabase;
import id.jyotisa.roomprogmoblanjut.Database.Entity.Mahasiswa;

public class MainActivity extends AppCompatActivity {
    private RecyclerView daftarMahasiswa;
    FloatingActionButton addButton;
    private AppDatabase database;
    private MahasiswaAdapter mahasiswaAdapter;
    private List<Mahasiswa> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daftarMahasiswa = findViewById(R.id.daftarMahasiswa);
        addButton = findViewById(R.id.addButon);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.mahasiswaDao().getAll());
        mahasiswaAdapter = new MahasiswaAdapter(getApplicationContext(), list);

        mahasiswaAdapter.setDialog(new MahasiswaAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case 0:
                                Intent editIntent = new Intent(MainActivity.this, UpdateActivity.class);
                                editIntent.putExtra("nim", list.get(position).nim);
                                startActivity(editIntent);
                                break;
                            case 1:
                                Mahasiswa mahasiswa = list.get(position);
                                database.mahasiswaDao().delete(mahasiswa);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        daftarMahasiswa.setLayoutManager(layoutManager);
        daftarMahasiswa.setAdapter(mahasiswaAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createActivity = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(createActivity);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.mahasiswaDao().getAll());
        mahasiswaAdapter.notifyDataSetChanged();
    }
}
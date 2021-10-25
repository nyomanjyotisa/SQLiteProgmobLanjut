package id.jyotisa.roomprogmoblanjut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.jyotisa.roomprogmoblanjut.Helper.Database;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    String[] label;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    Database database;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton addButton = findViewById(R.id.addButon);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createActivity = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(createActivity);
            }
        });

        mainActivity = this;
        database = new Database(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM  tb_mahasiswa", null);
        daftar = new String[cursor.getCount()];
        label = new String[cursor.getCount()];
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
            label[i] = cursor.getString(1).toString();
        }

        listView = (ListView) findViewById(R.id.daftarMahasiswa);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, label));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Show Company", "Update Company", "Delete Comapany"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                                i.putExtra("id", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateActivity.class);
                                in.putExtra("id", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM tb_mahasiswa WHERE id = '"+ selection +"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}
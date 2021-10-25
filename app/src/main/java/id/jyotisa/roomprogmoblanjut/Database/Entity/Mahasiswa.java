package id.jyotisa.roomprogmoblanjut.Database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    public int nim;

    public String nama;

    public String alamat;


}

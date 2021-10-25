package id.jyotisa.roomprogmoblanjut.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.jyotisa.roomprogmoblanjut.Database.Entity.Mahasiswa;

@Dao
public interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Query("UPDATE mahasiswa SET nama=:nama, alamat=:alamat WHERE nim=:old_nim")
    void update(String nama, String alamat, int old_nim);

    @Insert
    void insertAll(Mahasiswa... mahasiswas);

    @Query("SELECT * FROM mahasiswa WHERE nim=:nim")
    Mahasiswa get(int nim);

    @Delete
    void delete(Mahasiswa mahasiswa);
}

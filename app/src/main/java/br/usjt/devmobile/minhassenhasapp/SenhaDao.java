package br.usjt.devmobile.minhassenhasapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SenhaDao {


    @Query("SELECT * FROM senha;")
    List<Senha> getAll();

    @Query("SELECT * FROM senha WHERE id IN (:passIds)")
    List<Senha> loadAllByIds(int[] passIds);

    @Insert
    void insertAll(Senha... senhas);

    @Delete
    void delete(Senha senha);

}

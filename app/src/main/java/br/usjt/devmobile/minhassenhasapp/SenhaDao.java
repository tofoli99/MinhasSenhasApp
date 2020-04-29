package br.usjt.devmobile.minhassenhasapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SenhaDao {
    @Query("SELECT * FROM senha")
    List<Senha> getAll();

    @Query("SELECT * FROM senha WHERE uid IN (:senhaIds)")
    List<Senha> loadAllByIds(int[] senhaIds);

    @Query("SELECT * FROM senha WHERE nome LIKE :nome AND " +
            "usuario LIKE :usuario LIMIT 1")
    Senha findByName(String nome, String usuario);

    @Insert
    void insertAll(Senha... senhas);

    @Update
    void updateSenha(Senha senha);

    @Delete
    void delete(Senha senha);

    @Query("SELECT * FROM senha ORDER BY nome DESC")
    List<Senha> getAllAsc();

    @Query("SELECT * FROM senha ORDER BY nome ASC")
    List<Senha> getAllDesc();

    @Query("SELECT * FROM senha WHERE nome like :nome")
    List<Senha> getFiltroNome(String nome);
}

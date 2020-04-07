package br.usjt.devmobile.minhassenhasapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SenhaDao {

    @Query("SELECT * FROM senhaEntity")
    List<SenhaEntity> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(SenhaEntity... senhaEntities);

    @Delete
    void delete(SenhaEntity senhaEntity);
}

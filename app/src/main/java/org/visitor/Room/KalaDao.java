package org.visitor.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.visitor.Service.presenter.model.Kala;

import java.util.List;


@Dao
public abstract class KalaDao {


    ///// user2
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertKalas(Kala kala);


    @Update
    public abstract void updateKala(Kala kala);

    @Query("SELECT * FROM kala_info ")
    public abstract List<Kala> getAllKalaList();


    @Delete
    public abstract void deleteKalas(Kala kala);

    @Query("DELETE  FROM kala_info ")
    public abstract void deleteKala();


    @Query("SELECT * FROM kala_info WHERE kCode LIKE :kCode")
    public abstract Kala existItemInDatabase(Long kCode);

    @Query("SELECT * FROM kala_info WHERE kName LIKE :kName")
    public abstract Kala existItemInDatabaseName(String kName);

//    @Query("SELECT * FROM members WHERE groupId = :groupId")
//    public abstract List<MemberGroup> getMembers(String groupId);

}



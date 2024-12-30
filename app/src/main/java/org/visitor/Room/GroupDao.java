package org.visitor.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.visitor.Service.presenter.model.Groups;
import java.util.List;

@Dao
public abstract class GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertGroups(Groups groups);


    @Update
    public abstract void updateGroup(Groups Group);

    @Query("SELECT * FROM groups_info ")
    public abstract List<Groups> getAllGroupList();


    @Delete
    public abstract void deleteGroups(Groups group);

    @Query("DELETE  FROM groups_info ")
    public abstract void deleteGroup();


    @Query("SELECT * FROM groups_info WHERE id LIKE :gCode")
    public abstract Groups existItemInDatabase(int gCode);


}

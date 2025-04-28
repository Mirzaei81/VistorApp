package org.visitor.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.visitor.Service.presenter.model.GorohM;
import org.visitor.Service.presenter.model.Moshtari;

import java.util.List;

@Dao
public abstract  class MoshtariDao {

        @Query("SELECT * FROM gorohm_info")
        public abstract List<GorohM> getAllGorohMs();

        @Query("SELECT * FROM Moshtari")
        public abstract List<Moshtari> getAll();

        @Query("SELECT * FROM Moshtari WHERE mCode = :mCode LIMIT 1")
        public abstract Moshtari findByCode(int mCode);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public abstract void insert(Moshtari moshtari);

        @Insert(onConflict = (OnConflictStrategy.REPLACE))
        public abstract void insertGorohMs(List<GorohM> gorohMs);
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public abstract void insertAll(List<Moshtari> moshtariList);

        @Update
        public abstract void update(Moshtari moshtari);
        @Query("DELETE FROM Moshtari")
        public abstract void deleteAll();
        @Query("DELETE FROM gorohm_info")
        public abstract void deleteAllGorohMs();

        @Delete
        public abstract void delete(Moshtari moshtari);
}

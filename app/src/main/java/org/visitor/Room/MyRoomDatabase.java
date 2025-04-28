package org.visitor.Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import org.visitor.Service.presenter.model.GorohM;
import org.visitor.Service.presenter.model.Groups;
import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.Moshtari;


@Database(entities = {Kala.class, Groups.class, Moshtari.class, GorohM.class},
        version = 4, exportSchema = true)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static MyRoomDatabase INSTANCE;
    public abstract KalaDao kalaDao();
    public abstract GroupDao groupDao();
    public abstract MoshtariDao moshtariDao();
    public static synchronized MyRoomDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, "user-database1")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
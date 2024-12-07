package org.visitor.Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import org.visitor.Service.presenter.model.Kala;


@Database(entities = {Kala.class},
        version = 1, exportSchema = true)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static MyRoomDatabase INSTANCE;


    public abstract KalaDao kalaDao();
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
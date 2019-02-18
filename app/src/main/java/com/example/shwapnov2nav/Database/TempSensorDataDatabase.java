package com.example.shwapnov2nav.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = TempSensorData.class, version = 1, exportSchema = false)
public abstract class TempSensorDataDatabase extends RoomDatabase {

    public static volatile TempSensorDataDatabase INSTANCE;

    public abstract TempSensorDataDAO getTempSensorDataDAO();

    public static TempSensorDataDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (TempSensorDataDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TempSensorDataDatabase.class, "my_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}

package com.example.shwapnov2nav.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TempSensorDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TempSensorData> tempSensorData);

    @Query("SELECT * FROM sensor_data_table")
    Single<List<TempSensorData>> fetchAll();  //could be a list of  NotificationResponse if many rows exist in the database


    @Query("SELECT * FROM sensor_data_table WHERE row_id =:taskId")
    Single<TempSensorData> getById(int taskId);


    @Update
    void updateTask(List<TempSensorData> allListData);


    @Delete
    void deleteTask(List<TempSensorData> allListData);


    @Query("DELETE FROM sensor_data_table")
    void destroyTable();

}

package com.elecsiya.todo_success;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@SuppressWarnings("ALL")
@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(com.elecsiya.todo_success.Task task);

    @Delete
    void delete(com.elecsiya.todo_success.Task task);

    @Update
    void update(com.elecsiya.todo_success.Task task);

}

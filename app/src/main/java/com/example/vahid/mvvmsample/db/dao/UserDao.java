package com.example.vahid.mvvmsample.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vahid.mvvmsample.db.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by vahid on 1/31/18.
 */
@Dao
public interface UserDao {

    @Insert
    public void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUserList(User... user);

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("SELECT * FROM user")
    public Flowable<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE name LIKE :name ORDER BY id DESC LIMIT 1")
    public Flowable<User> getUsersByName(String name);
}

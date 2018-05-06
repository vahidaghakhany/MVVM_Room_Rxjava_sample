package com.example.vahid.mvvmsample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vahid.mvvmsample.db.dao.UserDao;
import com.example.vahid.mvvmsample.db.entity.User;


/**
 * Created by vahid on 1/31/18.
 */
@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;

    public abstract UserDao userDao();

    public static MyDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MyDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "UserDB.db").build();
                }
            }
        }
        return INSTANCE;
    }

}

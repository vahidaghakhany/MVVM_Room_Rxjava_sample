package com.example.vahid.mvvmsample;

import android.app.Application;

import com.example.vahid.mvvmsample.db.MyDatabase;
import com.example.vahid.mvvmsample.db.dao.UserDao;
import com.example.vahid.mvvmsample.db.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vahid on 1/31/18.
 */

public class DataRepository {

    private UserDao userDao;

    public DataRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        userDao = myDatabase.userDao();
    }

    public void insertUser(final User user){
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                userDao.insertUser(user);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Flowable<User> getUserByName(String name){
        return userDao.getUsersByName(name);
    }

    public Flowable<List<User>> getAllUser(){
        return userDao.getAllUsers();
    }
}

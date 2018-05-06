package com.example.vahid.mvvmsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Database;

import com.example.vahid.mvvmsample.db.entity.User;

import java.util.List;
import java.util.logging.Handler;

import io.reactivex.Flowable;

/**
 * Created by vahid on 1/20/18.
 */

public class MyViewModel extends AndroidViewModel {

    private MutableLiveData<String> data;
    private MutableLiveData<Integer> number;

    private DataRepository repository;

    public MyViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
    }

    public Flowable<User> getUserByName(String name){
        return repository.getUserByName(name);
    }

    public Flowable<List<User>> getAllUser(){
       return repository.getAllUser();
    }

    public void insertUser(User user){
        repository.insertUser(user);
    }




















    public LiveData<String> getData(){
        if (data == null){
            data = new MutableLiveData<String>();
        }
        return data;
    }

    public LiveData<Integer> getNumber(){
        if(number == null){
            number = new MutableLiveData<Integer>();
        }
        return number;
    }

    public void addValue(){
        if(number != null ) {
            if(number.getValue() != null) {
                number.setValue(number.getValue()+1);
            }else {
                number.setValue(0);
            }
        }
    }





}

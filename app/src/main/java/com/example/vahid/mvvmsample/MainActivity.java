package com.example.vahid.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vahid.mvvmsample.db.entity.User;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v4.widget.NestedScrollView.*;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;

    private final CompositeDisposable disposable = new CompositeDisposable();


    ScrollView view;
    TextView textView;
    TextView textView1;

    int i = 0;

    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView1 = (TextView)findViewById(R.id.textView2);
        view = (ScrollView)findViewById(R.id.scrollView);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        viewModel.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });

        final Rect rect = new Rect();
        view.getHitRect(rect);
        view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(textView.getLocalVisibleRect(rect)){
                    Log.e("a","its Visible");
                }else {
                    Log.e("a","its Gone");

                }
            }
        });



        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addValue();

                viewModel.insertUser(new User("vahid","Tehran,Iran"+i++,175, 2));
            }
        });

        disposable.add(viewModel.getUserByName("vahid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        textView1.setText(user.getAddress());
                    }
                })
        );

        disposable.add(viewModel.getAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        textView.setText("");
                        for(User user : users){
                            textView.append(user.getAddress());
                        }
                    }
                })
        );


    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.clear();
    }
}

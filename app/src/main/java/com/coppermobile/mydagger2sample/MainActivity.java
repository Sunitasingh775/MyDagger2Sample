package com.coppermobile.mydagger2sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coppermobile.mydagger2sample.adapter.RandomUserAdapter;
import com.coppermobile.mydagger2sample.application.RandomUserApplication;
import com.coppermobile.mydagger2sample.interfaces.RandomUsersApi;
import com.coppermobile.mydagger2sample.mainactivityfeature.DaggerMainActivityComponent;
import com.coppermobile.mydagger2sample.mainactivityfeature.MainActivityComponent;
import com.coppermobile.mydagger2sample.model.RandomUsers;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Inject
    RandomUserAdapter mAdapter;

    @Inject
    RandomUsersApi randomUsersApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Timber.plant(new Timber.DebugTree());

//        RandomUserComponent randomUserComponent = DaggerRandomUserComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//        picasso = randomUserComponent.getPicasso();
//        randomUsersApi = randomUserComponent.getRandomUserService();

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserApplicationComponent())
                .build();
//        randomUsersApi = mainActivityComponent.getRandomUserService();
//        mAdapter = mainActivityComponent.getRandomUserAdapter();

        mainActivityComponent.injectMainActivity(this);

        populateUsers();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = randomUsersApi.getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if (response.isSuccessful()) {
                    mAdapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }
}
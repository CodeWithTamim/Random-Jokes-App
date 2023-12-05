package com.cwtstudio.randomjokes.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cwtstudio.randomjokes.Adapter.RVAdapter;
import com.cwtstudio.randomjokes.Interfaces.ApiService;
import com.cwtstudio.randomjokes.Models.JokesModel;
import com.cwtstudio.randomjokes.R;
import com.cwtstudio.randomjokes.Retrofit.RetorofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RVAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;
    ProgressBar pgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = findViewById(R.id.recyclerView);
       refreshLayout = findViewById(R.id.refresh);
       pgbar = findViewById(R.id.pgbar);
       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       callApi();
       refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               callApi();
               Toast.makeText(MainActivity.this, "Loading New Jokes !", Toast.LENGTH_SHORT).show();
           }
       });




    }

    private void callApi() {
        ApiService apiService = RetorofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<JokesModel>>call = apiService.getJokes();
        call.enqueue(new Callback<List<JokesModel>>() {
            @Override
            public void onResponse(Call<List<JokesModel>> call, Response<List<JokesModel>> response) {
                if (response.isSuccessful()){
                    List<JokesModel>jokesList =  response.body();
                    adapter = new RVAdapter(MainActivity.this,jokesList);
                    recyclerView.setAdapter(adapter);
                    pgbar.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "Jokes Loaded !!!", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<List<JokesModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error! Please restart the app :)", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " +t);
                pgbar.setVisibility(View.GONE);


            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuPrivacyPolicy) {
            String websiteUrl = "https://docs.google.com/document/d/11_TlWTPfVaCVnF8eKgzXIuPATv0DzWPZZbQydXJSavA/edit?usp=sharing";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
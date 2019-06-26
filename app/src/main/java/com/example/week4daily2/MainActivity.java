package com.example.week4daily2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.example.week4daily2.model.datasource.FlickerRVAdapter;
import com.example.week4daily2.model.datasource.OnItemTouch;
import com.example.week4daily2.model.datasource.flickr.FlickrResponse;
import com.example.week4daily2.model.datasource.local.OnItemTouchHelper;
import com.example.week4daily2.model.datasource.remote.FlickrRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvFlicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFlicker = findViewById(R.id.rvFlickrRV);
        rvFlicker.addOnItemTouchListener(new OnItemTouch(getApplicationContext(), rvFlicker, new OnItemTouchHelper() {


            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return false;
            }

            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Small of Fullscreen Image");
                builder.setPositiveButton("FullScreen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Context context;
//                        Intent intent = new Intent(this , FullScreenActivity.class);
//                                startActivity(intent);
                        Log.d("TAG", "Works");

                    }
                });

            }


        }));


        FlickrRetroFit flickrRetroFit = new FlickrRetroFit();
        flickrRetroFit.getFlickrResponses("https://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1").enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                FlickrResponse flickrResponse = response.body();
                populateFlickrRV(flickrResponse);

            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {

            }
        });
    }

    private void populateFlickrRV(FlickrResponse flickrResponse) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        FlickerRVAdapter adapter = new FlickerRVAdapter(flickrResponse.getItems());
        rvFlicker.setLayoutManager(layoutManager);
        rvFlicker.setAdapter(adapter);
    }


}


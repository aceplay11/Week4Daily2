package com.example.week4daily2.model.datasource.remote;

import com.example.week4daily2.model.datasource.flickr.FlickrResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class FlickrRetroFit {
    public static final String ACTUAL_URL = "https://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1";
    public static final String BASE_URL = "https://api.flickr.com/services/";
    public static final String PATH = "photos_public.gne?";
    public static final String QUERY_RESULTS_AMOUNT = "tag=kitten&format=json&nojsoncallback=1";
    public static final String QUERY_TITLE = "title";
    public static final String QUERY_LINK = "link";
    public static final String QUERY_MEDIA = "media";

    private Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    private GetFlickrService getService(){
        return getRetrofitInstance().create(GetFlickrService.class);
    }

    public Call<FlickrResponse> getFlickrResponses(String string){
        return getService().getFlickrResponse(string);

    }

    public interface GetFlickrService {
        @GET
        Call<FlickrResponse> getFlickrResponse(@Url String url);
    }
}

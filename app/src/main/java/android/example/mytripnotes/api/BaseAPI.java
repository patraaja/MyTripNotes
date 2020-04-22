package android.example.mytripnotes.api;

import android.example.mytripnotes.model.ResultCityModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseAPI {
    @GET("city")
    Call<ResultCityModel> getCity(
            @Query("key") String key
    );
}

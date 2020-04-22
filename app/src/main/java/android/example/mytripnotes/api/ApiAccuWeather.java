package android.example.mytripnotes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAccuWeather {
    private static final String BASE_URL = "";
    private static Retrofit retrofit;
    private static ApiAccuWeather mInstance;

    private ApiAccuWeather() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiAccuWeather getInstance() {
        if (mInstance == null) {
            mInstance = new ApiAccuWeather();
        }
        return mInstance;
    }

    public BaseAPI baseAPI() {
        return retrofit.create(BaseAPI.class);
    }
}

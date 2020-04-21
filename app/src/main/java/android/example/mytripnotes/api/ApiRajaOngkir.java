package android.example.mytripnotes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRajaOngkir {
    private static final String BASE_URL = "https://api.rajaongkir.com/starter/";
    private static Retrofit retrofit;
    private static ApiRajaOngkir mInstance;

    private ApiRajaOngkir() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiRajaOngkir getInstance() {
        if (mInstance == null) {
            mInstance = new ApiRajaOngkir();
        }
        return mInstance;
    }

    public BaseAPI baseAPI() {
        return retrofit.create(BaseAPI.class);
    }
}

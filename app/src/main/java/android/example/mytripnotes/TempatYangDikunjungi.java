package android.example.mytripnotes;

import android.content.Context;
import android.content.Intent;
import android.example.mytripnotes.dbhelper.StateDataHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kwabenaberko.openweathermaplib.constants.Lang;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

public class TempatYangDikunjungi extends AppCompatActivity {

    private TextView tvKota, tvTanggal, tvSuhuKota, tvKeadaanCuaca;
    private EditText etNote;
    private String tujuan;
    private String tanggal;
    private String tipe;
    private String suhu;
    private String keadaan;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_yang_dikunjungi);
        tvKota = findViewById(R.id.kota);
        tvTanggal = findViewById(R.id.tanggal);
        etNote = findViewById(R.id.note);
        tvSuhuKota = findViewById(R.id.suhukota);
        tvKeadaanCuaca = findViewById(R.id.keadaan_cuaca);
        Button btnActivitas = findViewById(R.id.button_aktivitas);
        setData();
        cuacaTujuan();

        btnActivitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = etNote.getText().toString();

                StateDataHelper stateDataHelper = new StateDataHelper(context);
                stateDataHelper.deleteAll();
                stateDataHelper.insert(tujuan, tanggal, tipe, note, suhu, keadaan);

                Intent intent = new Intent(TempatYangDikunjungi.this, Activitas.class);
                startActivity(intent);
            }
        });
    }

    private void cuacaTujuan() {
        OpenWeatherMapHelper openWeatherMapHelper = new OpenWeatherMapHelper("9da00787a2fc30eceedc217a0f63d022");
        openWeatherMapHelper.setUnits(Units.METRIC);
        openWeatherMapHelper.setLang(Lang.ENGLISH);
        openWeatherMapHelper.getCurrentWeatherByCityName(tujuan, new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                tvSuhuKota.setText(String.valueOf(currentWeather.getMain().getTemp()));
                tvKeadaanCuaca.setText(String.valueOf(currentWeather.getWeather().get(0).getDescription()));

                suhu = String.valueOf(currentWeather.getMain().getTemp());
                keadaan = String.valueOf(currentWeather.getWeather().get(0).getDescription());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(TempatYangDikunjungi.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData() {
        Intent intent = getIntent();
        tujuan = intent.getStringExtra("tujuan");
        tanggal = intent.getStringExtra("tgl_keberangkatan") + " - " + intent.getStringExtra("tgl_kembali");
        tipe = intent.getStringExtra("tipe_perjalanan");

        tvKota.setText(tujuan);
        tvTanggal.setText(tanggal);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

package android.example.mytripnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TempatYangDikunjungi extends AppCompatActivity {

    private TextView tvKota, tvTanggal;
    private EditText etNote;
    private String tujuan, tanggal, tipe, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_yang_dikunjungi);
        tvKota = findViewById(R.id.kota);
        tvTanggal = findViewById(R.id.tanggal);
        etNote = findViewById(R.id.note);

        getData();
        setData();
    }

    private void getData() {
        note = etNote.getText().toString();
    }

    private void setData() {
        Intent intent = getIntent();
        tujuan = intent.getStringExtra("tujuan");
        tanggal = intent.getStringExtra("tgl_keberangkatan")+" - "+intent.getStringExtra("tgl_kembali");
        tipe = intent.getStringExtra("tipe_perjalanan");

        tvKota.setText(tujuan);
        tvTanggal.setText(tanggal);
    }

    public void activitas(View view) {
        Intent intent = new Intent(this, Activitas.class);
        startActivity(intent);
    }
}

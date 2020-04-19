package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class KategoriRekomendasi extends AppCompatActivity {
    private CardView cvLaut, cvPegunungan, cvSejarah, cvKuliner, cvBelanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_rekomendasi);
        cvLaut = findViewById(R.id.laut);
        cvPegunungan = findViewById(R.id.pegunungan);
        cvBelanja = findViewById(R.id.belanja);
        cvKuliner = findViewById(R.id.kuliner);
        cvSejarah = findViewById(R.id.sejarah);
        onClickKategori();
    }

    private void onClickKategori() {
        cvLaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KategoriRekomendasi.this, IsiRekomendasiLaut.class));
            }
        });

        cvPegunungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KategoriRekomendasi.this, IsiRekomendasiPegunungan.class));
            }
        });

        cvSejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KategoriRekomendasi.this, IsiRekomendasiSejarah.class));
            }
        });

        cvKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KategoriRekomendasi.this, IsiRekomendasiKuliner.class));
            }
        });

        cvBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KategoriRekomendasi.this, IsiRekomendasiBelanja.class));
            }
        });
    }
}

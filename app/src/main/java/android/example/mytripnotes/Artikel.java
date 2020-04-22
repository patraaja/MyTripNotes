package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Artikel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
    }

    public void rekomendasi(View view) {
        startActivity(new Intent(this, KategoriRekomendasi.class));
    }

    public void tipsntrik(View view) {
    }
}

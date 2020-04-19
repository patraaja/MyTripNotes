package android.example.mytripnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TempatYangDikunjungi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_yang_dikunjungi);
    }

    public void activitas(View view) {
        Intent intent = new Intent(this, Activitas.class);
        startActivity(intent);
    }
}

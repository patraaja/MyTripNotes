package android.example.mytripnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitas);
        // tambahan 
    }

    public void kustom_activitas(View view) {
        Intent intent = new Intent(this, Kustomactivitas.class);
        startActivity(intent);
    }
}

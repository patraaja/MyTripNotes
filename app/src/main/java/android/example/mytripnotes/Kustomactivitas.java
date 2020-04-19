package android.example.mytripnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Kustomactivitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kustomactivitas);
    }

    public void submit(View view) {
        Intent in = new Intent(this, Kustomactivitas.class);
        startActivity(in);
        finish();
    }
}

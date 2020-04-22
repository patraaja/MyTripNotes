package android.example.mytripnotes;

import android.content.Intent;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class KustomActivitas extends AppCompatActivity {

    private EditText etNamaActivitas;
    private CustomActivitasDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kustom_activitas);
        etNamaActivitas = findViewById(R.id.nama_activitas);
        dbHelper = new CustomActivitasDBHelper(this);
    }

    public void submit(View view) {
        String activitas = etNamaActivitas.getText().toString().trim();
        dbHelper.insert(activitas);
        Intent in = new Intent(this, Activitas.class);
        startActivity(in);
    }
}

package android.example.mytripnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.mytripnotes.adapter.ActivitasAdapter;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.example.mytripnotes.model.ActivitasModel;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activitas extends AppCompatActivity {

    private ArrayList<ActivitasModel> activitas = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitas);
        recyclerView = findViewById(R.id.recycle1);
        showActivitas();
    }

    private void showActivitas() {
        CustomActivitasDBHelper dbHelper = new CustomActivitasDBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from tb_activitas", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            ActivitasModel activitasModel = new ActivitasModel(cursor.getInt(0), cursor.getString(1));
            activitas.add(activitasModel);
        }
        RecyclerView.Adapter adapter = new ActivitasAdapter(activitas, Activitas.this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void kustom_activitas(View view) {
        Intent intent = new Intent(this, KustomActivitas.class);
        startActivity(intent);
    }
}

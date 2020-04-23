package android.example.mytripnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.mytripnotes.adapter.PackingAdapter;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.example.mytripnotes.dbhelper.StateDataHelper;
import android.example.mytripnotes.model.ActivitasModel;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DetailNotes extends AppCompatActivity {

    private ArrayList<ActivitasModel> activitas = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView tvKota, tvSuhuKota, tvKeadaan, tvTanggal, tvNotes, tvTipe;
    private String tujuan, tanggal, tipe, note, suhu, keadaan;
    private ImageView ivShare;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notes);
        recyclerView = findViewById(R.id.recycle3);
        tvKota = findViewById(R.id.kota);
        tvKeadaan = findViewById(R.id.keadaan_cuaca);
        tvSuhuKota = findViewById(R.id.suhukota);
        tvTanggal = findViewById(R.id.tanggal);
        tvNotes = findViewById(R.id.notes);
        tvTipe = findViewById(R.id.tipe);
        ivShare = findViewById(R.id.share);
        relativeLayout = findViewById(R.id.relative2);

        setData();
        showActivitas();
        shareData();
    }

    private void shareData() {
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setDrawingCacheEnabled(true);
                relativeLayout.buildDrawingCache();
                Bitmap bitmap = relativeLayout.getDrawingCache();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, "My Trip Notes");
                intent.putExtra(Intent.EXTRA_TEXT, "Get my trip notes here!");
                intent.putExtra(Intent.EXTRA_STREAM, saveImage(bitmap));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent, "Share with..."));
            }
        });
    }

    private Uri saveImage(Bitmap bitmap) {
        //TODO - Should be processed in another thread
        File imagesFolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagesFolder.mkdirs();
            File file = new File(imagesFolder, "shared_image.png");
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(this, "com.harsoft.fileprovider", file);

        } catch (IOException e) {
            Log.d("Error", "IOException while trying to write file for sharing: " + e.getMessage());
        }
        return uri;
    }

    private void setData() {
        StateDataHelper stateDataHelper = new StateDataHelper(this);
        SQLiteDatabase db = stateDataHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from tb_data", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            tujuan = cursor.getString(1);
            tanggal = cursor.getString(2);
            tipe = cursor.getString(3);
            note = cursor.getString(4);
            suhu = cursor.getString(5);
            keadaan = cursor.getString(6);

            tvTipe.setText(tipe);
            tvNotes.setText(note);
            tvTanggal.setText(tanggal);
            tvKota.setText(tujuan);
            tvSuhuKota.setText(suhu);
            tvKeadaan.setText(keadaan);
        } else {
            Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void showActivitas() {
        CustomActivitasDBHelper dbHelper = new CustomActivitasDBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from tb_activitas", null);
        cursor.moveToFirst();
        activitas.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            ActivitasModel activitasModel = new ActivitasModel(cursor.getInt(0), cursor.getString(1));
            activitas.add(activitasModel);
        }
        RecyclerView.Adapter adapter = new PackingAdapter(activitas, DetailNotes.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Activitas.class));
    }
}

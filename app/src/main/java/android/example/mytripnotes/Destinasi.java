package android.example.mytripnotes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Destinasi extends AppCompatActivity {
    private Button btnDestinasi;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    private EditText etTujuan;
    private RadioButton rdPekerjaan, rdLiburan;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private String date1 = "";
    private String date2 = "";
    private String tujuan = "";
    private  String tipe = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinasi);
        etTujuan = findViewById(R.id.destinasi);
        mDisplayDate1 = findViewById(R.id.tvDatePergi);
        mDisplayDate2 = findViewById(R.id.tvDateKembali);
        rdPekerjaan = findViewById(R.id.radioButton);
        rdLiburan = findViewById(R.id.radioButton2);
        btnDestinasi = findViewById(R.id.button);

        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Destinasi.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date1 = day + "/" + month + "/" + year;
                mDisplayDate1.setText(date1);
            }
        };

        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Destinasi.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date2 = day + "/" + month + "/" + year;
                mDisplayDate2.setText(date2);
            }
        };


        if (rdPekerjaan.isChecked()){
            tipe = "pekerjaan";
        }else if(rdLiburan.isChecked()){
            tipe = "liburan";
        }else{
            tipe = "sembarang";
        }

        btnDestinasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tujuan = etTujuan.getText().toString().trim();
                Intent intent = new Intent(Destinasi.this, TempatYangDikunjungi.class);
                intent.putExtra("tujuan", tujuan);
                intent.putExtra("tgl_keberangkatan", date1);
                intent.putExtra("tgl_kembali", date2);
                intent.putExtra("tipe_perjalanan", tipe);
                startActivity(intent);
            }
        });
    }
}

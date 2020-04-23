package android.example.mytripnotes.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.example.mytripnotes.R;
import android.example.mytripnotes.TempatYangDikunjungi;
import android.example.mytripnotes.api.ApiRajaOngkir;
import android.example.mytripnotes.model.CityModel;
import android.example.mytripnotes.model.ResultCityModel;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    private Button btnDestinasi;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    private Spinner spTujuan;
    private RadioGroup radioGroup;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private String date1 = "";
    private String date2 = "";
    private String tujuan = "";
    private String tipe = "";
    private ArrayList<CityModel> cityModels;
    private ResultCityModel resultCityModel;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spTujuan = view.findViewById(R.id.destinasi);
        mDisplayDate1 = view.findViewById(R.id.tvDatePergi);
        mDisplayDate2 = view.findViewById(R.id.tvDateKembali);
        btnDestinasi = view.findViewById(R.id.button);
        radioGroup = view.findViewById(R.id.rgTipe);
        getDataCity();
        inputData();
    }

    private void getDataCity() {
        Call<ResultCityModel> call = ApiRajaOngkir.getInstance()
                .baseAPI()
                .getCity("e047008e889ac6329aa2dd447480dbf0");

        call.enqueue(new Callback<ResultCityModel>() {
            @Override
            public void onResponse(@NonNull Call<ResultCityModel> call, @NonNull Response<ResultCityModel> response) {
                resultCityModel = response.body();
                cityModels = new ArrayList<>();
                cityModels.clear();
                cityModels.addAll(resultCityModel.getRajaongkir().getResults());

                final ArrayList<String> item = new ArrayList<>();
                item.clear();
                for (int i = 0; i < cityModels.size(); i++) {
                    item.add(cityModels.get(i).getCity_name());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spTujuan.setAdapter(adapter);
                spTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tujuan = item.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<ResultCityModel> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("failure : ", t.getMessage());
            }
        });
    }

    private void inputData() {
        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
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
                        getContext(),
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

        btnDestinasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radioButton:
                        tipe = "Lekerjaan";
                        break;
                    case R.id.radioButton2:
                        tipe = "Liburan";
                        break;
                }

                Intent intent = new Intent(getContext(), TempatYangDikunjungi.class);
                intent.putExtra("tujuan", tujuan);
                intent.putExtra("tgl_keberangkatan", date1);
                intent.putExtra("tgl_kembali", date2);
                intent.putExtra("tipe_perjalanan", tipe);
                startActivity(intent);
            }
        });
    }
}

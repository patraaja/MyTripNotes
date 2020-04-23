package android.example.mytripnotes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.mytripnotes.DetailNotes;
import android.example.mytripnotes.R;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.example.mytripnotes.model.ActivitasModel;
import android.example.mytripnotes.model.PackingModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PackingAdapter extends RecyclerView.Adapter<PackingAdapter.ViewHolder> {

    private ArrayList<ActivitasModel> activitas;
    private Context context;
    private CustomActivitasDBHelper dbHelper;

    public PackingAdapter(ArrayList<ActivitasModel> activitas, Context context) {
        this.activitas = activitas;
        this.context = context;
    }

    @NonNull
    @Override
    public PackingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_packing, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull final PackingAdapter.ViewHolder holder, final int position) {
        String nama_activitas = activitas.get(position).getActivitas();
        holder.tvActivitas.setText(nama_activitas);
        ArrayList<PackingModel> packing = new ArrayList<>();
        dbHelper = new CustomActivitasDBHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from tb_packing where id_activitas =" + activitas.get(position).getId(), null);
        cursor.moveToFirst();
        packing.clear();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            PackingModel packingModel = new PackingModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            packing.add(packingModel);
        }

        RecyclerView.Adapter adapter = new ItemAdapter(packing, context);
        holder.rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvItem.setLayoutManager(layoutManager);
        holder.rvItem.setHasFixedSize(true);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertPacking(holder.etAddItem.getText().toString().trim(), activitas.get(position).getId());
                Toast.makeText(context, "Item has been added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailNotes.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activitas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvActivitas;
        private RecyclerView rvItem;
        private EditText etAddItem;
        private Button btnAdd;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvActivitas = itemView.findViewById(R.id.activitas);
            rvItem = itemView.findViewById(R.id.recycle2);
            etAddItem = itemView.findViewById(R.id.add_item);
            btnAdd = itemView.findViewById(R.id.add);
        }
    }
}

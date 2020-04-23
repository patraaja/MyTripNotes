package android.example.mytripnotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.example.mytripnotes.Activitas;
import android.example.mytripnotes.R;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.example.mytripnotes.model.ActivitasModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivitasAdapter extends RecyclerView.Adapter<ActivitasAdapter.ViewHolder> {

    private ArrayList<ActivitasModel> activitas;
    private Context context;

    public ActivitasAdapter(ArrayList<ActivitasModel> activitas, Context context) {
        this.activitas = activitas;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivitasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activitas, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitasAdapter.ViewHolder holder, final int position) {
        String nama_activitas = activitas.get(position).getActivitas();
        holder.activitas.setText(nama_activitas);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = activitas.get(position).getId();
                CustomActivitasDBHelper customActivitasDBHelper = new CustomActivitasDBHelper(context);
                customActivitasDBHelper.delete(String.valueOf(id));
                Toast.makeText(context, "Activitas has been deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Activitas.class);
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
        private TextView activitas;
        private ImageView btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            activitas = itemView.findViewById(R.id.nama_activitas);
            btnDelete = itemView.findViewById(R.id.delete_activitas);
        }
    }
}

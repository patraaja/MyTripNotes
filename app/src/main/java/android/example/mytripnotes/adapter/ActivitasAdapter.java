package android.example.mytripnotes.adapter;

import android.content.Context;
import android.example.mytripnotes.R;
import android.example.mytripnotes.model.ActivitasModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ActivitasAdapter extends RecyclerView.Adapter<ActivitasAdapter.ViewHolder> {

    private ArrayList<ActivitasModel> activitas;
    private Context context;
    private FirebaseAuth auth;

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
    public void onBindViewHolder(@NonNull ActivitasAdapter.ViewHolder holder, int position) {
        String nama_activitas = activitas.get(position).getActivitas();
        holder.activitas.setText(nama_activitas);
    }

    @Override
    public int getItemCount() {
        return activitas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView activitas;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            activitas = itemView.findViewById(R.id.nama_activitas);
        }
    }
}

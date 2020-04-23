package android.example.mytripnotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.example.mytripnotes.DetailNotes;
import android.example.mytripnotes.R;
import android.example.mytripnotes.dbhelper.CustomActivitasDBHelper;
import android.example.mytripnotes.model.PackingModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<PackingModel> packing;
    private Context context;

    ItemAdapter(ArrayList<PackingModel> packing, Context context) {
        this.packing = packing;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemAdapter.ViewHolder holder, final int position) {
        String item = packing.get(position).getItem();
        holder.tvItem.setText(item);

        holder.ivDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = packing.get(position).getId();
                CustomActivitasDBHelper customActivitasDBHelper = new CustomActivitasDBHelper(context);
                customActivitasDBHelper.deletePacking(String.valueOf(id));
                Toast.makeText(context, "Item has been deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailNotes.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packing.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;
        private ImageView ivDeleteItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.item);
            ivDeleteItem = itemView.findViewById(R.id.delete_item);
        }
    }
}

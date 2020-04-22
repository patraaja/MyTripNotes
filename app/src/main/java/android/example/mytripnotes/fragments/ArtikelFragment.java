package android.example.mytripnotes.fragments;

import android.content.Intent;
import android.example.mytripnotes.KategoriRekomendasi;
import android.example.mytripnotes.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtikelFragment extends Fragment {

    private ImageView ivRekomendasi, ivTipsntrick;

    public ArtikelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artikel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivRekomendasi = view.findViewById(R.id.rekomendasi);
        ivTipsntrick = view.findViewById(R.id.tipsntrick);

        ivRekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), KategoriRekomendasi.class));
            }
        });

        ivTipsntrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

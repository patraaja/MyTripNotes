package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class KategoriTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_tips);
    }

    public void photography(View view) {
        startActivity(new Intent(this, IsiPhotography.class));
    }

    public void hiking(View view) {
        startActivity(new Intent(this, IsiHiking.class));
    }

    public void nyelam(View view) {
        startActivity(new Intent(this, IsiSnorkeling.class));
    }

    public void lowbudget(View view) {
        startActivity(new Intent(this, IsiLowBudget.class));
    }

    public void familytrip(View view) {
        startActivity(new Intent(this, IsiFamilyTrip.class));
    }
}

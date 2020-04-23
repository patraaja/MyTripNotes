package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    private EditText etNama, etEmail, etTelp;
    private Button btnSave;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etTelp = findViewById(R.id.etTelp);
        btnSave = findViewById(R.id.btnSave);
        auth = FirebaseAuth.getInstance();
        UID = auth.getCurrentUser().getUid();
        onState();
        getDataUser();
        saveDataUser();
    }

    private void saveDataUser() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String telp = etTelp.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child("users").child(UID).child("nama").setValue(nama);
                reference.child("users").child(UID).child("email").setValue(email);
                reference.child("users").child(UID).child("tlp").setValue(telp);
                startActivity(new Intent(EditProfile.this, EditProfile.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                Toast.makeText(EditProfile.this, "Data has been saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama_user = dataSnapshot.child("nama").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String telp = dataSnapshot.child("tlp").getValue(String.class);

                etNama.setText(nama_user);
                etEmail.setText(email);
                etTelp.setText(telp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfile.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onState() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(EditProfile.this, ActivityLogin.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

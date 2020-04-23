package android.example.mytripnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.example.mytripnotes.fragments.ArtikelFragment;
import android.example.mytripnotes.fragments.NotesFragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private BottomNavigationView bottomNavigationView;
    private String UID;
    private String nama_user;
    private TextView tvUser;
    private ImageView ivEditUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tvUser = findViewById(R.id.nama_user);
        ivEditUser = findViewById(R.id.edit_user);
        auth = FirebaseAuth.getInstance();
        UID = auth.getCurrentUser().getUid();
        onState();
        getDataUser();
        getFragmentPage(new NotesFragment());
        bottomNavigation();
        editUser();
    }

    private void editUser() {
        ivEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditProfile.class));
            }
        });
    }

    private void getDataUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_user = "Hai, " + dataSnapshot.child("nama").getValue(String.class);
                tvUser.setText(nama_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()) {
                    case R.id.note:
                        fragment = new NotesFragment();
                        break;

                    case R.id.artikel:
                        fragment = new ArtikelFragment();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }

    @SuppressLint("PrivateResource")
    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.design_bottom_sheet_slide_in,
                            R.anim.design_bottom_sheet_slide_out)
                    .addToBackStack(null)
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private void onState() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, ActivityLogin.class));
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
        if (authStateListener == null) {
            startActivity(new Intent(this, ActivityLogin.class));
        }
    }

    public void logout(View view) {
        auth.signOut();
    }
}


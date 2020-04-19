package android.example.mytripnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registrasi extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        firebase();
    }

    private void firebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }


    public void register(View v){
        String email = ((EditText) findViewById(R.id.editText2)).getText().toString();
        @SuppressLint("CutPasteId") String pass = ((EditText) findViewById(R.id.editText6)).getText().toString();
        @SuppressLint("CutPasteId") String repass = ((EditText) findViewById(R.id.editText6)).getText().toString();
        if(pass.equals(repass))
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                saveData(user.getUid());
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("register", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registrasi.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        else
            Toast.makeText(Registrasi.this, "Password not match",
                    Toast.LENGTH_SHORT).show();
    }

    private void saveData(String uId){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        UserModel user = new UserModel(
                uId,
                ((EditText) findViewById(R.id.editText)).getText().toString(),
                ((EditText) findViewById(R.id.editText2)).getText().toString(),
                ((EditText) findViewById(R.id.editText5)).getText().toString()
        );

        myRef.child(uId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Registrasi.this, "Register Berhasil",
                        Toast.LENGTH_SHORT).show();
                Intent in =  new Intent(Registrasi.this, ActivityLogin.class);
                startActivity(in);
            }
        });
    }
}

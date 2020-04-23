package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        onState();
    }

    private void onState() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                    finish();
                } else {

                }
            }
        };
    }

    public void daftar(View view) {
        Intent intent = new Intent(this, Registrasi.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String pass = ((EditText) findViewById(R.id.editText4)).getText().toString();
        if (!(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)))
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            String user_name = authResult.getUser().getUid();
                            startActivity(new Intent(ActivityLogin.this, MainActivity.class).putExtra("user", user_name));
                            Toast.makeText(ActivityLogin.this, "Login success!", Toast.LENGTH_SHORT).show();
                        }
                    });
        else {
            Toast.makeText(ActivityLogin.this, "Password not match",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void skip(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

}

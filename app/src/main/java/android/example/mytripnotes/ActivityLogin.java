package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
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
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent in = new Intent(ActivityLogin.this, MainActivity.class);
                                startActivity(in);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("register", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        else
            Toast.makeText(ActivityLogin.this, "Password not match",
                    Toast.LENGTH_SHORT).show();
    }

    public void skip(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

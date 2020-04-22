package android.example.mytripnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Thread() {
            public void run() {
                Intent mainMenu = new Intent(SplashScreen.this, ActivityLogin.class);
                SplashScreen.this.startActivity(mainMenu);
                SplashScreen.this.finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 3000);
    }
}

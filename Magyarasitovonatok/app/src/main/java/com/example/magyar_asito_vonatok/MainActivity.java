package com.example.magyar_asito_vonatok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 333;

    private FirebaseAuth mAuth;

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Log.i(TAG, "onCreate");
        mAuth = FirebaseAuth.getInstance();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view) {
        email = findViewById(R.id.emailcimEditText);
        password = findViewById(R.id.jelszoEditText);
        if (email == null || password == null) {
            Log.e(TAG, "Az email vagy jelszó mező nem található! Ellenőrizd az id-kat az XML-ben.");
            return;
        }

        String jelszo = password.getText().toString();
        String emailcim = email.getText().toString();
        Log.i(TAG, "Bejelentkezett: " + email + " Jelszó: " + jelszo);
        if (emailcim.isEmpty() || jelszo.isEmpty()) {
            Toast.makeText(this, "Kérlek add meg az email címet és a jelszót!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailcim, jelszo).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "felhasználó létrehozva");
                    startApp();
                }else {
                    Log.d(TAG, "Nem sikeres a login");
                    Toast.makeText(MainActivity.this, "Nem sikerült a bejelentkezés " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void startApp() {
        Intent intent = new Intent(this, Fooldal.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
}
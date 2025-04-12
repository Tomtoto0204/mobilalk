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

public class Register extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 333;
    private FirebaseAuth mAuth;
    EditText fullnameET;
    EditText emailET;
    EditText passwordET;
    EditText password2ET;
    EditText dateET;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        Log.i(TAG, "onCreate");
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key != 333){
            finish();
        }
        fullnameET = findViewById(R.id.fullnameEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.jelszoEditText);
        password2ET = findViewById(R.id.jelszoEditText2);
        dateET = findViewById(R.id.szuletesiDateText);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void vissza(View view) {
        finish();
    }

    public void reg(View view) {
        String fullname =  fullnameET.getText().toString();
        String email =  emailET.getText().toString();
        String password =  passwordET.getText().toString();
        String password2 =  password2ET.getText().toString();
        String date =  dateET.getText().toString();

        if (email.isEmpty() || password.isEmpty() || password2.isEmpty() || fullname.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Kérlek tölts ki minden mezőt!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(password2)){
            Log.i(TAG, "Nem egyezik a jelszó");
        }else {
            Log.i(TAG, "email: "+ email + " date: "+ date);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "felhasználó létrehozva");
                        startApp();
                    }else {
                        Log.d(TAG, "Nem sikeres a reg");
                        Toast.makeText(Register.this, "Nem sikerült a regisztráció " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
     void startApp(){
        Intent intent = new Intent(this, Fooldal.class);
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
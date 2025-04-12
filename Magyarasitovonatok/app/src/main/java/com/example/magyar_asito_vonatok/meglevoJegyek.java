package com.example.magyar_asito_vonatok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class meglevoJegyek extends AppCompatActivity {
    private static final String TAG = Fooldal.class.getName();
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meglevo_jegyek);

        Button visszaButton = findViewById(R.id.visszaButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(TAG, "Van ilyen felhasználó móni");
        }else{
            Log.d(TAG, "Nincs ilyen felhasználó móni");
            finish();
        }
        visszaButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Fooldal.class);
            startActivity(intent);
        });
    }
}
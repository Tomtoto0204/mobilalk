package com.example.magyar_asito_vonatok;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fooldal extends AppCompatActivity {
    private static final String TAG = Fooldal.class.getName();
    private FirebaseUser user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_fooldal);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(TAG, "Van ilyen felhasználó móni");
        }else{
            Log.d(TAG, "Van ilyen felhasználó móni");
            finish();
        }
    }
}

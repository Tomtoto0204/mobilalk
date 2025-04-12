package com.example.magyar_asito_vonatok;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fooldal extends AppCompatActivity {
    private static final String TAG = Fooldal.class.getName();
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooldal);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(TAG, "Van ilyen felhasználó móni");
        }else{
            Log.d(TAG, "Nincs ilyen felhasználó móni");
            finish();
        }
        ImageView vonatImage = findViewById(R.id.vonatImage);
        Button csucsuButton = findViewById(R.id.csucsuButton);


        csucsuButton.setOnClickListener(new View.OnClickListener() {
            @Override               ///TODO NEM MŰKÖDIK
            public void onClick(View v) {
                vonatImage.setVisibility(View.VISIBLE);
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -1.0f,  // -100% szülő szélesség
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.ABSOLUTE, 0f,      // Kezdő pozíció: 0
                        Animation.ABSOLUTE, 0f);     // Befejező pozíció: nincs elmozdulás vertikálisan

                animation.setDuration(2000); // Az animáció hossza 2 másodperc
                animation.setFillAfter(true); // Az animáció után maradjon a végső pozícióban

                vonatImage.startAnimation(animation);
            }
        });


    }

}

package com.example.magyar_asito_vonatok;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

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
        Button meglevoButton = findViewById(R.id.meglevojegyekButton);
        Button datumGomb = findViewById(R.id.datumGomb);

        csucsuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vonatImage.setVisibility(View.VISIBLE);
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -1.0f,
                        Animation.RELATIVE_TO_PARENT, 1.0f,
                        Animation.ABSOLUTE, 0f,
                        Animation.ABSOLUTE, 0f);
                animation.setDuration(3500);
                animation.setFillAfter(true);

                vonatImage.startAnimation(animation);
            }
        });
        meglevoButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, meglevoJegyek.class);
            startActivity(intent);
        });
        datumGomb.setOnClickListener(v -> {
            Calendar naptar = Calendar.getInstance();
            int ev = naptar.get(Calendar.YEAR);
            int honap = naptar.get(Calendar.MONTH);
            int nap = naptar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, valasztottev, valasztotthonap, valasztottnap) -> {
                        String datum = String.format("%04d.%02d.%02d", valasztottev, valasztotthonap + 1, valasztottnap);
                        datumGomb.setText(datum);
                    },
                    ev, honap, nap
            );

            datePickerDialog.getDatePicker().setMinDate(naptar.getTimeInMillis());
            datePickerDialog.show();
        });


    }

}

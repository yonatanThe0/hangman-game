package com.example.hangman7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInput ,btnGuess, btnCollection , btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        btnInput =findViewById(R.id.btnInput);
        btnGuess = findViewById(R.id.btnGuess);
        btnCollection =findViewById(R.id.btnCollection);
        btnRandom =findViewById(R.id.btnRandom);

        btnInput.setOnClickListener((this));
        btnGuess.setOnClickListener(this);
        btnCollection.setOnClickListener((this));
        btnRandom.setOnClickListener((this));
    }

    @Override
    public void onClick(View v) {
        if (v == btnInput)
        {
            Intent i = new Intent(this, InputActivity.class);
            startActivity(i);
        }
        if (v == btnGuess)
        {
            Intent i = new Intent(this, WaitingActivity.class);
            startActivity(i);

        }
        if (v == btnCollection)
        {
            Intent i = new Intent(this, CollectionActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }
        if (v == btnRandom)
        {

            Intent i = new Intent(this, GameActivity.class);
            String word;
            try {
                word = RandomWord.GetRandomWord();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            i.putExtra("EXTRA_WORD", word);
            i.putExtra("SAVE_MODE", "random");
            startActivity(i);
        }

    }
}

package com.example.hangman7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle;
    Button btnClick1,btnClick2;
    String mode,word,check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        init();
    }

    private void init() {
        word = getIntent().getStringExtra("EXTRA_WORD");
        mode = getIntent().getStringExtra("SAVE_MODE");
        check = getIntent().getStringExtra("WIN_OR_LOSS");

        btnClick1 = findViewById(R.id.btnClick1);
        btnClick2 = findViewById(R.id.btnClick2);
        tvTitle = findViewById(R.id.tvTitle);

        btnClick1.setOnClickListener(this);
        btnClick2.setOnClickListener(this);

        tvTitle.setText("Default Text");

        if ("win".equals(check)) {
            tvTitle.setText("Nice! You win!");
        } else if ("loss".equals(check)) {
            tvTitle.setText("You lost! Try again. The word was " + word);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == btnClick1){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (v == btnClick2){
            if (mode.equals("guess")){
                Intent i = new Intent(this, WaitingActivity.class);
                startActivity(i);
            }
            if (mode.equals("collection")){
                Intent i = new Intent(this, CollectionActivity.class);
                startActivity(i);
            }
            if (mode.equals("random")){
                Intent i = new Intent(this, GameActivity.class);
                String word = null;
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
}
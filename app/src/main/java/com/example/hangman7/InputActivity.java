package com.example.hangman7;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText tvInput;
    private TextView tvText;
    private Button btnEnter;
    FbModule fbModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        btnEnter = findViewById(R.id.btnEnter);
        tvInput = findViewById(R.id.tvInput);
        tvText = findViewById(R.id.tvText);
        fbModule = new FbModule();

        btnEnter.setBackgroundColor(Color.BLACK);
        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnEnter) {
            String newWord = tvInput.getText().toString().trim();
            if (check_word(newWord)) {
                fbModule.saveNewW(newWord);
            }
        }
    }

    private boolean check_word(String word) {
        if (word.isEmpty()) {
            tvText.setText("Word is empty");
            btnEnter.setBackgroundColor(Color.RED);
            return false;
        }
        if (!word.matches("[a-zA-Z ]+")) {
            tvText.setText("Use only alphabet");
            btnEnter.setBackgroundColor(Color.RED);
            return false;
        }
        if (word.length()>13){  //more will be outside the box
            tvText.setText("word must be less then 13 letter");
            btnEnter.setBackgroundColor(Color.RED);
            return false;
        }
        tvText.setText("Word sent!");
        btnEnter.setBackgroundColor(Color.GREEN);
        return true;
    }
}
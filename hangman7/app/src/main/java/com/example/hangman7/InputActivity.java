package com.example.hangman7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tvInput;
    Button btnEnter;
    FbModule FbModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        btnEnter = findViewById(R.id.btnEnter);
        tvInput = findViewById(R.id.tvInput);
        FbModule = new FbModule();

        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnEnter) {
            String newW = tvInput.getText().toString().trim();
            if (!newW.isEmpty()) {
                FbModule.saveNewW(newW);
            }
        }
    }
}

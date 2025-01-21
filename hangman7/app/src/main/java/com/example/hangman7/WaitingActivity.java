package com.example.hangman7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class WaitingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCheck;
    TextView tvResult;
    FbModule FbMoudle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);
        FbMoudle = new FbModule();
        FbMoudle.saveNewW("");

        btnCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnCheck) {
            FbMoudle.getNewWReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    String newW = snapshot.getValue(String.class);
                    if (newW != null && !newW.isEmpty()) { // Check if newW is not null and not empty
                        tvResult.setText("the game loading");
                        Intent i = new Intent(WaitingActivity.this, GameActivity.class);
                        i.putExtra("EXTRA_WORD", newW);
                        i.putExtra("SAVE_MODE", "guess");
                        startActivity(i);
                    } else {
                        tvResult.setText("wait to player to enter");
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    tvResult.setText("Error: " + error.getMessage());
                }
            });
        }
    }
}

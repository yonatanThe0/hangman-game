package com.example.hangman7;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class WaitingActivity extends AppCompatActivity {

    private FbModule fbModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        fbModule = new FbModule();
        fbModule.saveNewW("");

        // Add continuous listener for changes in newW
        fbModule.getNewWReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String newWord = snapshot.getValue(String.class);
                if (newWord != null && !newWord.isEmpty()) {
                    Intent intent = new Intent(WaitingActivity.this, GameActivity.class);
                    intent.putExtra("EXTRA_WORD", newWord);
                    intent.putExtra("SAVE_MODE", "guess");
                    startActivity(intent);

                }
            }

            @Override //catch error for new ValueEventListener() line29 end
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Database error: " + error.getMessage());
                Toast.makeText(WaitingActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
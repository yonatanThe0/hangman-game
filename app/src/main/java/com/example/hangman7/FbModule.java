package com.example.hangman7;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FbModule {
    private DatabaseReference databaseReference;

    public FbModule() {
        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("path");
    }

    public void saveNewW(String newW) {
        databaseReference.setValue(newW);
    }

    public DatabaseReference getNewWReference() {
        return databaseReference;
    }
}
